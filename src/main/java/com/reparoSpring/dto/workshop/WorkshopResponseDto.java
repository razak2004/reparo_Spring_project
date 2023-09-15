package com.reparoSpring.dto.workshop;

public class WorkshopResponseDto {

    private int userId;
    private int workshopId;
    private String workshopName;
        private String image;
    private String city;
    private String state;
    private String country;
    private String address;

    private int type;
    private String openTime;
    private String closeTime;
    private int generalPrice;
    private int electricalPrice;
    private int suspensionPrice;
    private int enginePrice;

    // Constructor
    public WorkshopResponseDto() {
        // Default constructor
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(int generalPrice) {
        this.generalPrice = generalPrice;
    }

    public int getElectricalPrice() {
        return electricalPrice;
    }

    public void setElectricalPrice(int electricalPrice) {
        this.electricalPrice = electricalPrice;
    }

    public int getSuspensionPrice() {
        return suspensionPrice;
    }

    public void setSuspensionPrice(int suspensionPrice) {
        this.suspensionPrice = suspensionPrice;
    }

    public int getEnginePrice() {
        return enginePrice;
    }

    public void setEnginePrice(int enginePrice) {
        this.enginePrice = enginePrice;
    }
}
