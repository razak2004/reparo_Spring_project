package com.reparoSpring.dto.vehicle;



public class VehicleRequestDto {

    private int userId;
    private int type;
    private String vehicleNumber;
    private int year;
    private String company;
    private String model;
    public VehicleRequestDto() {
    }

    // Constructor with parameters
    public VehicleRequestDto(int userId, int type, String vehicleNumber, int year, String company, String model) {
        this.userId = userId;
        this.type = type;
        this.vehicleNumber = vehicleNumber;
        this.year = year;
        this.company = company;
        this.model = model;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
