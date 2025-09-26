package com.assignment.parkinglotreservation.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @GetMapping("/check/{slotId}")
    public String checkAvailability(@PathVariable Long slotId) {
        return "Available";
    }

    @GetMapping("/{id}")
    public String getReservation(@PathVariable Long id) {
        return "Reservation 1";
    }
}
