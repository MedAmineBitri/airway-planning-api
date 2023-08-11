package com.airway.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.airway.demo.dto.FlightDTO;
import com.airway.demo.entity.Flight;
import com.airway.demo.repository.FlightRepository;
import com.airway.demo.tools.CustomResponseBody;
import com.github.dozermapper.core.Mapper;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

  @InjectMocks
  private FlightService    flightService;

  @Mock
  private FlightRepository flightRepository;

  @Mock
  private AirlineService   airlineService;

  @Mock
  private AirportService   airportService;

  @Mock
  private Mapper           mapper;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void testGetFlights_Success() {
    // Mock input parameters
    String airline = "Airline1";
    String sourceAirport = "SourceAirport";
    String destinationAirport = "DestinationAirport";
    LocalDateTime dateTime = LocalDateTime.now();

    // Mock Flight entities and DTOs
    List<Flight> flightEntities = new ArrayList<>();
    flightEntities.add(new Flight());
    List<FlightDTO> flightDTOs = new ArrayList<>();
    flightDTOs.add(new FlightDTO());

    // Mock flightRepository behavior
    when(flightRepository.findAll(any(Specification.class))).thenReturn(flightEntities);
    when(mapper.map(any(Flight.class), eq(FlightDTO.class))).thenReturn(new FlightDTO());

    // Call the method to test
    ResponseEntity<CustomResponseBody<List<FlightDTO>>> response = flightService.getFlights(airline, sourceAirport, destinationAirport, dateTime);

    // Verify the behavior
    verify(flightRepository).findAll(any(Specification.class));
    verify(mapper, times(flightEntities.size())).map(any(Flight.class), eq(FlightDTO.class));

    // Assert the response
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(HttpStatus.OK.toString(), response.getBody().getStatus());

  }

  @Test
  void testGetFlights_Exception() {
    when(flightRepository.findAll(any(Specification.class))).thenThrow(new RuntimeException());

    ResponseEntity<CustomResponseBody<List<FlightDTO>>> response = flightService.getFlights(null, null, null, LocalDateTime.now());

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

}
