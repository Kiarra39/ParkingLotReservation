package com.assignment.parkinglotreservation.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String slotNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    @JsonIgnore
    private Floor floor;
    
    @OneToMany(mappedBy = "parkingSlot", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
    
    // Constructors
    public ParkingSlot() {}
    
    public ParkingSlot(String slotNumber, VehicleType vehicleType, Floor floor) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.floor = floor;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
    
    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }
    
    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}