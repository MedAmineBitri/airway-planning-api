package com.airway.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long          id;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "airline", referencedColumnName = "code")
  private Airline airline;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "source_airport", referencedColumnName = "code")
  private Airport sourceAirport;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "destination_airport", referencedColumnName = "code")
  private Airport destinationAirport;

  private LocalDateTime dateTime;

}
