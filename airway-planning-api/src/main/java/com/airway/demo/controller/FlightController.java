package com.airway.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airway.demo.dto.FlightDTO;
import com.airway.demo.service.FlightService;
import com.airway.demo.tools.CustomResponseBody;

@RestController
@RequestMapping("/flights")
public class FlightController {

  @Autowired
  FlightService flightService;

  @PostMapping
  public ResponseEntity<CustomResponseBody<FlightDTO>> addFlight(@RequestBody FlightDTO flightDTO) {

    return flightService.addFlight(flightDTO);
  }

  @GetMapping
  public ResponseEntity<CustomResponseBody<List<FlightDTO>>> getFlights(
  @RequestParam(required = false) String airline,
  @RequestParam(required = false) String sourceAirport,
  @RequestParam(required = false) String destinationAirport,
  @RequestParam(required = false) LocalDateTime dateTime) {

    return flightService.getFlights(airline, sourceAirport, destinationAirport, dateTime);
  }

}