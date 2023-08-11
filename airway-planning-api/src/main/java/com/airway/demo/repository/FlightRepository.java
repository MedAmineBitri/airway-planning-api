package com.airway.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.airway.demo.entity.Airline;
import com.airway.demo.entity.Airport;
import com.airway.demo.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

  List<Flight> findByAirlineCodeAndSourceAirportCodeAndDestinationAirportCodeAndDateTime(
  String airline,
  String sourceAirport,
  String destinationAirport,
  LocalDateTime dateTime);

  long countFlightsByAirlineAndSourceAirportAndDestinationAirport(Airline airline, Airport sourceAirport, Airport destinationAirport);

}
