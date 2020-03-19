package com.interview.calculator.vo;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "calcResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcResultVO extends ResourceSupport implements Serializable
{

    public CalcResultVO(Integer day, Integer qty, Integer total, Integer carId, Integer price,
                        Boolean isRule1, Boolean isRule2, Boolean isRule3, Integer defaultPrice) {
        super();
        this.day = day;
        this.carId = carId;
        this.qty = qty;
        this.total = total;
        this.price = price;
        this.defaultPrice = defaultPrice;
        this.isRule1 = isRule1;
        this.isRule2 = isRule2;
        this.isRule3 = isRule3;
    }

    public CalcResultVO() {
    }

    private Integer carId;
    private Integer day;

    private Integer qty;
    private Integer total;
    private Integer price;
    private Integer defaultPrice;
    private Boolean isRule1;
    private Boolean isRule2;
    private Boolean isRule3;


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public Integer getQty() { return qty; }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Integer getTotal() { return total; }

    public void setDefaultPrice(Integer defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    public Integer getDefaultPrice() { return defaultPrice; }

    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getPrice() { return price; }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getIsRule1() { return isRule1; }

    public void setIsRule1(Boolean isRule1) {
        this.isRule1 = isRule1;
    }

    public Boolean getIsRule2() { return isRule2; }

    public void setIsRule2(Boolean isRule2) {
        this.isRule2 = isRule2;
    }

    public Boolean getIsRule3() { return isRule3; }

    public void setIsRule3(Boolean isRule3) {
        this.isRule3 = isRule3;
    }
}