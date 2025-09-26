package com.assignment.parkinglotreservation.dto;


import jakarta.validation.constraints.NotNull;

public class FloorRequest {
    @NotNull(message = "Floor number is required")
    private Integer floorNumber;
    
    // Getters and Setters
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
}
