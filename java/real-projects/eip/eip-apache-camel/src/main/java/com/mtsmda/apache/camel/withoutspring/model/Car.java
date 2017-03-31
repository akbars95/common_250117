package com.mtsmda.apache.camel.withoutspring.model;

/**
 * Created by dminzat on 3/31/2017.
 */
public class Car {

    private Integer carId;
    private String carMark;
    private String carModel;

    public Car() {

    }

    public Car(Integer carId, String carMark, String carModel) {
        this.carId = carId;
        this.carMark = carMark;
        this.carModel = carModel;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carMark='" + carMark + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}