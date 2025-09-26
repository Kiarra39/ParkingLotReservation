package com.assignment.parkinglotreservation.controller;


import com.assignment.parkinglotreservation.dto.ApiResponse;
import com.assignment.parkinglotreservation.dto.SlotRequest;
import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.service.ParkingSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {
    
    @Autowired
    private ParkingSlotService parkingSlotService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createSlot(@Valid @RequestBody SlotRequest request) {
        try {
            ParkingSlot slot = parkingSlotService.createSlot(
                request.getFloorId(), request.getSlotNumber(), request.getVehicleType());
            return ResponseEntity.ok(ApiResponse.success("Parking slot created successfully", slot));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<ApiResponse> getSlotsByFloor(@PathVariable Long floorId) {
        try {
            List<ParkingSlot> slots = parkingSlotService.getSlotsByFloor(floorId);
            return ResponseEntity.ok(ApiResponse.success("Slots retrieved successfully", slots));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    @GetMapping
public ResponseEntity<ApiResponse> getAllSlots() {
    try {
        List<ParkingSlot> slots = parkingSlotService.getAllSlots();
        return ResponseEntity.ok(ApiResponse.success("All slots retrieved successfully", slots));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }
}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSlot(@PathVariable Long id) {
        try {
            parkingSlotService.deleteSlot(id);
            return ResponseEntity.ok(ApiResponse.success("Slot deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting slot: " + e.getMessage()));
        }
    }
}