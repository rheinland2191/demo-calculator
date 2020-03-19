package com.interview.calculator.controller;

import com.interview.calculator.exception.ResourceNotFoundException;
import com.interview.calculator.model.Cars;
import com.interview.calculator.repository.CarsRepository;
import com.interview.calculator.vo.CarsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    CarsRepository carsRepository;


    @GetMapping
    ResponseEntity<List<Cars>> getCars() {
        return new ResponseEntity<List<Cars>>((List<Cars>) carsRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Cars> addCars(@Valid @RequestBody CarsVO cars) {
        Cars model = new Cars();
        model.setYearBuild(cars.getYearBuild());
        model.setQty(cars.getQty());
        model.setPrice(cars.getPrice());
        model.setName(cars.getName());
        Cars save = carsRepository.save(model);
        return ResponseEntity.ok().body(save);
    }

    @PutMapping("/{id}")
    ResponseEntity<Cars> editCars(@Valid @RequestBody CarsVO cars, @PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Cars currentCar = carsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found for this ID : " + id));
        currentCar.setName(cars.getName());
        currentCar.setPrice(cars.getPrice());
        currentCar.setQty(cars.getQty());
        currentCar.setYearBuild(cars.getYearBuild());
        carsRepository.save(currentCar);

        return ResponseEntity.ok().body(currentCar);
    }

    @GetMapping("/{id}")
    ResponseEntity<Cars> getCarsId(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Cars cars = carsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found for this ID : " + id));
        return ResponseEntity.ok().body(cars);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCarId(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Cars cars = carsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found for this ID : " + id));
        carsRepository.delete(cars);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }
}
   