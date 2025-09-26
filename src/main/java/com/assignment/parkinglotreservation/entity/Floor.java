package com.assignment.parkinglotreservation.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Integer floorNumber;
    
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Added fetch = EAGER
    private List<ParkingSlot> slots = new ArrayList<>();
    
    // Constructors, getters, and setters remain the same
    public Floor() {}
    
    public Floor(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
    
    public List<ParkingSlot> getSlots() { return slots; }
    public void setSlots(List<ParkingSlot> slots) { this.slots = slots; }
}