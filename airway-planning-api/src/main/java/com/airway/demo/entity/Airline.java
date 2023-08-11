package com.airway.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Airline {
  @Id
  private String code;
}
