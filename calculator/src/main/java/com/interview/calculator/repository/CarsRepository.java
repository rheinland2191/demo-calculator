package com.interview.calculator.repository;

import com.interview.calculator.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Cars, Integer> {

}
