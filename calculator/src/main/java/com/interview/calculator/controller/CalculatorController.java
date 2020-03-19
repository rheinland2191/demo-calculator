package com.interview.calculator.controller;

import com.interview.calculator.exception.ResourceNotFoundException;
import com.interview.calculator.model.Cars;
import com.interview.calculator.repository.CarsRepository;
import com.interview.calculator.vo.CalcResultVO;
import com.interview.calculator.vo.CalcVO;
import com.interview.calculator.vo.CarsVO;
import com.interview.calculator.vo.TotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/calc-price")
public class CalculatorController {
    @Autowired
    CarsRepository carsRepository;

    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    @PostMapping
    ResponseEntity<TotalVO> calcPrice(@Valid @RequestBody List<CalcVO> order) throws ResourceNotFoundException {
        TotalVO totalVO = new TotalVO();
        List<CalcResultVO> calcResultVOS = new ArrayList<>();
        Integer totalPrice = 0;
        for(CalcVO data : order){
            CalcResultVO calcResultVO = new CalcResultVO();
            Optional<Cars> cars = carsRepository.findById(data.getCarId());
            if(cars.isPresent()){
                if(cars.get().getQty()<data.getQty()){
                    throw new ResourceNotFoundException("Qty in database is less that ordered for car id : " + data.getCarId());
                }
                if(!(data.getQty() <= 0)&& !(data.getDay() <= 0)){

                    calcResultVO.setCarId(cars.get().getCarId());
                    calcResultVO.setQty(data.getQty());
                    calcResultVO.setDay(data.getDay());
                    calcResultVO.setPrice(cars.get().getPrice());
                    calcResultVO.setDefaultPrice(cars.get().getPrice()*data.getQty()*data.getDay());
                    calcResultVO.setTotal(cars.get().getPrice()*data.getQty()*data.getDay());

                    if(calcResultVO.getDay()>=3){
                        calcResultVO.setIsRule1(true);
                        calcResultVO.setTotal(calcResultVO.getTotal()*95/100);
                    }else{
                        calcResultVO.setIsRule1(false);
                        calcResultVO.setTotal(calcResultVO.getTotal());
                    }
                    if(calcResultVO.getQty()>=2){
                        calcResultVO.setIsRule2(true);
                        calcResultVO.setTotal(calcResultVO.getTotal()*90/100);
                    }else{
                        calcResultVO.setIsRule2(false);
                        calcResultVO.setTotal(calcResultVO.getTotal());
                    }
                    if(cars.get().getYearBuild()<2010){
                        calcResultVO.setIsRule3(true);
                        calcResultVO.setTotal(calcResultVO.getTotal()*93/100);
                    }else {
                        calcResultVO.setIsRule3(false);
                        calcResultVO.setTotal(calcResultVO.getTotal());
                    }
                    totalPrice = totalPrice + calcResultVO.getTotal();
                    calcResultVOS.add(calcResultVO);
                }else {

                    throw new ResourceNotFoundException("Qty / Day must be in positive number and minimal 1");
                }
            }
        }
        totalVO.setDetail(calcResultVOS);
        totalVO.setTotal(totalPrice);
        return ResponseEntity.ok().body(totalVO);
    }

}

