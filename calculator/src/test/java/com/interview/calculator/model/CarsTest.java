package com.interview.calculator.model;


import java.util.ArrayList;
import java.util.List;

public class CarsTest {

    private int carId;
    private String name;
    private int price;
    private int qty;
    private int yearBuild;

    public CarsTest(int carId, String name, int price, int qty, int yearBuild){
        this.carId = carId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.yearBuild = yearBuild;
        this.carsList = new ArrayList<>();
    }


    public static String getEmpNameWithHighestSalary(){
        return "Nattu";
    }
    private List<CarsTest> carsList;


    public static List<CarsTest> getCars(){
        List<CarsTest> carsTestList = new ArrayList<>();
        carsTestList.add(new CarsTest(1, "Mobil A", 15000,15,2009));
        carsTestList.add(new CarsTest(2, "Mobil B", 45000,15,2007));
        carsTestList.add(new CarsTest(3, "Mobil C", 50000,15,2012));
        return carsTestList;
    }
    public List<CarsTest> getCarsList(){ return carsList;}
    public void setCarsList(List<CarsTest> carsList){ this.carsList = carsList ;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getYearBuild() {
        return yearBuild;
    }
    public void setYearBuild(int yearBuild) {
        this.yearBuild = yearBuild;
    }
}