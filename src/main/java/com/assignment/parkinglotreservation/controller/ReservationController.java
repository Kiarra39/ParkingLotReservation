package com.assignment.parkinglotreservation.controller;

import com.assignment.parkinglotreservation.dto.ApiResponse;
import com.assignment.parkinglotreservation.dto.ReservationRequest;
import com.assignment.parkinglotreservation.entity.ParkingSlot;
import com.assignment.parkinglotreservation.entity.Reservation;
import com.assignment.parkinglotreservation.entity.VehicleType;
import com.assignment.parkinglotreservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @PostMapping("/reserve")
    public ResponseEntity<ApiResponse> reserveSlot(@Valid @RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationService.createReservation(
                request.getSlotId(), request.getVehicleNumber(), request.getVehicleType(),
                request.getStartTime(), request.getEndTime());
            return ResponseEntity.ok(ApiResponse.success("Reservation created successfully", reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/availability")
    public ResponseEntity<ApiResponse> checkAvailability(
            @RequestParam VehicleType vehicleType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        try {
            List<ParkingSlot> availableSlots = reservationService.checkAvailability(vehicleType, startTime, endTime);
            return ResponseEntity.ok(ApiResponse.success("Available slots retrieved successfully", availableSlots));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/reservations/{id}")
    public ResponseEntity<ApiResponse> getReservation(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(reservation -> ResponseEntity.ok(ApiResponse.success("Reservation retrieved successfully", reservation)))
                .orElse(ResponseEntity.badRequest().body(ApiResponse.error("Reservation not found")));
    }
    
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<ApiResponse> cancelReservation(@PathVariable Long id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok(ApiResponse.success("Reservation cancelled successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error cancelling reservation: " + e.getMessage()));
        }
    }
}
