package com.assignment.parkinglotreservation.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {

    @GetMapping
    public List<String> getAllSlots() {
        return List.of("Slot 1", "Slot 2");
    }

    @GetMapping("/floor/{floorId}")
    public List<String> getSlotsByFloor(@PathVariable Long floorId) {
        return List.of("Slot 1");
    }
}
