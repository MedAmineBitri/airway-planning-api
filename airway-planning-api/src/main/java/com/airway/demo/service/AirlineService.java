package com.airway.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airway.demo.entity.Airline;
import com.airway.demo.repository.AirlineRepository;

@Service
public class AirlineService {

  @Autowired
  AirlineRepository airlineRepository;

  public Airline getExistingAirline(String airlineCode) {
    return airlineRepository.findByCode(airlineCode);
  }
}
