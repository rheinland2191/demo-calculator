package com.interview.calculator.vo;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "calc")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcVO extends ResourceSupport implements Serializable
{

    public CalcVO(Integer id, Integer qty, Integer day) {
        super();
        this.carId = id;
        this.qty = qty;
        this.day = day;
    }

    public CalcVO() {
    }

    @NotNull
    @NotEmpty(message = "car id must not be empty / null")
    private Integer carId;


    @Pattern(regexp = "[\\s]*[1-9]*[0-9]+",message="must be positive")
    @NotNull(message = "car qty must not be null or minimun 1 qty")
    private Integer qty;

    @Pattern(regexp = "[\\s]*[1-9]*[0-9]+",message="must be positive")
    @NotNull(message = "day rental must not be null or minimun 1 qty")
    private Integer day;



    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    public Integer getQty() { return qty; }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Integer getDay() { return day; }

    public void setDay(Integer day) {
        this.day = day;
    }
}