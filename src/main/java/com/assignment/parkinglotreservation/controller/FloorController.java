package com.assignment.parkinglotreservation.controller;

import com.assignment.parkinglotreservation.dto.ApiResponse;
import com.assignment.parkinglotreservation.dto.FloorRequest;
import com.assignment.parkinglotreservation.entity.Floor;
import com.assignment.parkinglotreservation.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/floors")
public class FloorController {

    @Autowired
    private FloorService floorService;

   @PostMapping
public ResponseEntity<ApiResponse> createFloor(@Valid @RequestBody FloorRequest request) {
    try {
        Floor savedFloor = floorService.createFloor(request.getFloorNumber());
        return ResponseEntity.ok(ApiResponse.success("Floor created successfully", savedFloor));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }
}

    @GetMapping
    public ResponseEntity<ApiResponse> getAllFloors() {
        List<Floor> floors = floorService.getAllFloors();
        return ResponseEntity.ok(ApiResponse.success("Floors retrieved successfully", floors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getFloorById(@PathVariable Long id) {
        return floorService.getFloorById(id)
                .map(floor -> ResponseEntity.ok(ApiResponse.success("Floor retrieved successfully", floor)))
                .orElse(ResponseEntity.badRequest().body(ApiResponse.error("Floor not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFloor(@PathVariable Long id) {
        try {
            floorService.deleteFloor(id);
            return ResponseEntity.ok(ApiResponse.success("Floor deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting floor: " + e.getMessage()));
        }
    }
}
