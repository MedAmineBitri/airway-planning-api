package com.airway.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airway.demo.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, String> {
  Airline findByCode(String code);
}
