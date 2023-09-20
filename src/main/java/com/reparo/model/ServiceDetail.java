package com.reparo.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "service_details")
public class ServiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_list_id")
    private int serviceListId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "accept_status")
    private boolean acceptStatus;

    @Column(name = "cancel_status")
    private boolean cancelStatus;

    @Column(name = "service_amount")
    private int serviceAmount;

    @Column(name = "cancel_reason")
    private String cancelReason;

    // Getter and Setter methods

    public int getServiceListId() {
        return serviceListId;
    }

    public void setServiceListId(int serviceListId) {
        this.serviceListId = serviceListId;
    }

    public Booking getBooking() {
        return booking;
    }

    public ServiceDetail(Booking booking) {
        this.booking = booking;
    }

    public ServiceDetail() {
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public boolean isAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public boolean isCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(List<ServiceList> services) {
        int sum = 0 ;
        for (ServiceList service: services) {
            sum += service.getServicePrice();
        }
        this.serviceAmount = sum;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
