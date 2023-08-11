package com.airway.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.airway.demo.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

}
