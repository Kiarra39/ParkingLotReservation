package com.assignment.parkinglotreservation.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floors")
public class FloorController {

    @GetMapping
    public List<String> getAllFloors() {
        return List.of("Floor 1", "Floor 2");
    }

    @DeleteMapping("/{id}")
    public String deleteFloor(@PathVariable Long id) {
        return "Deleted";
    }
}
