package com.airway.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airway.demo.entity.Airport;
import com.airway.demo.repository.AirportRepository;

@Service
public class AirportService {

  @Autowired
  private AirportRepository airportRepository;

  public Airport getExistingAirportByCode(String airportCode) {
    return airportRepository.findByCode(airportCode);
  }
}
