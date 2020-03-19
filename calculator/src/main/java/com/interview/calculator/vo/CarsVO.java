package com.interview.calculator.vo;

import io.swagger.models.auth.In;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsVO extends ResourceSupport implements Serializable
{

    public CarsVO(Integer id, String name, Integer price, Integer qty, Integer yearBuild) {
        super();
        this.carId = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.yearBuild = yearBuild;
    }

    public CarsVO() {
    }

    private Integer carId;

    @NotNull
    @NotEmpty(message = "car name must not be empty / null")
    private String name;

    @Min(value = 10000,message = "car price must be greater than 10000")
    @NotNull(message = "car price must not be null or minimum 10000")
    private Integer price;

    @Min(value = 1,message = "car qty must be greater than 0")
    @NotNull(message = "car qty must not be null or minimun 1 qty")
    private Integer qty;

    @Min(value = 1900,message = "car year must be greater than 1900")
    @Max(value = 2020,message = "car year cannot be greater than 2020")
    @NotNull(message = "car year must not be empty / null")
    private Integer yearBuild;


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(Integer yearBuild) {
        this.yearBuild = yearBuild;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() { return qty; }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}