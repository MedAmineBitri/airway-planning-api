package com.airway.demo.dto;

import java.time.LocalDateTime;

import com.airway.demo.entity.Airline;
import com.airway.demo.entity.Airport;

public class FlightDTO {
  private Airline       airline;
  private Airport       sourceAirport;
  private Airport       destinationAirport;
  private LocalDateTime dateTime;

  public Airline getAirline() {
    return airline;
  }

  public void setAirline(Airline airline) {
    this.airline = airline;
  }

  public Airport getSourceAirport() {
    return sourceAirport;
  }

  public void setSourceAirport(Airport sourceAirport) {
    this.sourceAirport = sourceAirport;
  }

  public Airport getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(Airport destinationAirport) {
    this.destinationAirport = destinationAirport;
  }
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

}

