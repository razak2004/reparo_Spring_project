package com.reparo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booking_id")
  private int bookingId;

  @ManyToOne
  @JoinColumn(name = "vehicle_id",nullable = false)
  private Vehicle vehicle;

  @ManyToOne
  @JoinColumn(name = "workshop_id")
  private Workshop workshop;

  @Column(name = "latitude", nullable = false)
  private double latitude;

  @Column(name = "longitude", nullable = false)
  private double longitude;

  @Column(name = "booking_city", nullable = false)
  private String bookingCity;

  @Column(name = "booking_state",  nullable = false)
  private String bookingState;

  @Column(name = "booking_country",  nullable = false)
  private String bookingCountry;

  @Column(name = "booking_address", nullable = false)
  private String bookingAddress;

  @Column(name = "request_status")
  private boolean requestStatus;

  @Column(name = "accept_status")
  private boolean acceptStatus;

  @Column(name = "problem",  nullable = false)
  private String problem;

  @Column(name = "booking_date")
  private Date bookingDate;

  @Column(name = "booking_time")
  private String bookingTime;

  @Column(name = "is_live")
  private boolean isLive;

  @Column(name = "is_read")
  private Boolean isRead;

  // Constructors, getters, and setters (omitted for brevity)

  // You can generate constructors, getters, and setters using your IDE or manually.

}

