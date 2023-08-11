package com.airway.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airway.demo.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
  Airport findByCode(String code);
}
