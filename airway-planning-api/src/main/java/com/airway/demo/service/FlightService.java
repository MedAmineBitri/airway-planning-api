package com.airway.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airway.demo.dto.FlightDTO;
import com.airway.demo.entity.Airline;
import com.airway.demo.entity.Airport;
import com.airway.demo.entity.Flight;
import com.airway.demo.repository.FlightRepository;
import com.airway.demo.tools.CustomResponseBody;
import com.github.dozermapper.core.Mapper;

@Service
public class FlightService {

  @Autowired
  FlightRepository       flightRepository;

  @Autowired
  AirlineService         airlineService;

  @Autowired
  private AirportService airportService;

  @Autowired
  private Mapper         mapper;

  public ResponseEntity<CustomResponseBody<FlightDTO>> addFlight(FlightDTO flightDTO) {
    try {
      Flight flight = mapper.map(flightDTO, Flight.class);

      // Check if there are already 3 flights for the same airline between same source and destination
      checkMaxFlightsPerAirline(flight);

      // Check if sourceAirport and destinationAirport are not equal
      checkSourceAndDestinationAirports(flight);

      // Check if the airline/airport already exists in the database
      handleExistingEntities(flight);

      flightRepository.save(flight);
      return new ResponseEntity<>(
      new CustomResponseBody<>(flightDTO, HttpStatus.CREATED.toString()),
      HttpStatus.CREATED);
    } catch (IllegalArgumentException | NullPointerException e) {
      return new ResponseEntity<>(
      new CustomResponseBody<>(null, HttpStatus.BAD_REQUEST.toString(), e.getMessage()),
      HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(
      new CustomResponseBody<>(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e
      .getMessage()),
      HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void checkMaxFlightsPerAirline(Flight flight) {
    long existingFlightsCount = flightRepository.countFlightsByAirlineAndSourceAirportAndDestinationAirport(
    flight.getAirline(), flight.getSourceAirport(), flight.getDestinationAirport());

    if (existingFlightsCount >= 3) {
      throw new IllegalArgumentException("There are already 3 flights for this airline between the same source and destination");
    }
  }

  private void checkSourceAndDestinationAirports(Flight flight) {
    if (flight.getDestinationAirport() == null || flight.getSourceAirport() == null) {
      throw new NullPointerException("Source / destination airports are null");
    }
    if (flight.getSourceAirport().equals(flight.getDestinationAirport())) {
      throw new IllegalArgumentException("Source and destination airports cannot be the same");
    }
  }

  private void handleExistingEntities(Flight flight) {
    Airline existingAirline = airlineService.getExistingAirline(flight.getAirline().getCode());
    if (existingAirline != null) {
      flight.setAirline(existingAirline); // Use the existing airline entity
    }

    Airport existingSourceAirport = airportService.getExistingAirportByCode(flight.getSourceAirport().getCode());
    if (existingSourceAirport != null) {
      flight.setSourceAirport(existingSourceAirport); // Use the existing airport entity
    }

    Airport existingDestinationAirport = airportService.getExistingAirportByCode(flight.getDestinationAirport().getCode());
    if (existingDestinationAirport != null) {
      flight.setDestinationAirport(existingDestinationAirport); // Use the existing airport entity
    }
  }

  public ResponseEntity<CustomResponseBody<List<FlightDTO>>> getFlights(
  String airline,
  String sourceAirport,
  String destinationAirport,
  LocalDateTime dateTime) {
    try {
      Specification<Flight> spec = (root, query, cb) -> {
        List<Predicate> predicates = new ArrayList<>();

        if (airline != null) {
          predicates.add(cb.equal(root.get("airline").get("code"), airline));
        }

        if (sourceAirport != null) {
          predicates.add(cb.equal(root.get("sourceAirport").get("code"), sourceAirport));
        }

        if (destinationAirport != null) {
          predicates.add(cb.equal(root.get("destinationAirport").get("code"), destinationAirport));
        }

        if (dateTime != null) {
          predicates.add(cb.equal(root.get("dateTime"), dateTime));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
      };

      List<Flight> flights = flightRepository.findAll(spec);

      List<FlightDTO> flightsDto = flights.stream().map(filght -> mapper.map(filght, FlightDTO.class)).collect(Collectors.toList());

      return new ResponseEntity<>(
      new CustomResponseBody<>(flightsDto, HttpStatus.OK.toString()),
      HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
      new CustomResponseBody<>(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e
      .getMessage()),
      HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}