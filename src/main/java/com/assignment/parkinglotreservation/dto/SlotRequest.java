package com.assignment.parkinglotreservation.dto;


import com.assignment.parkinglotreservation.entity.VehicleType;
import jakarta.validation.constraints.NotNull;

public class SlotRequest {
    @NotNull(message = "Floor ID is required")
    private Long floorId;
    
    @NotNull(message = "Slot number is required")
    private String slotNumber;
    
    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;
    
    // Getters and Setters
    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }
    
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
}
