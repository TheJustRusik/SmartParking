package org.kenuki.smartparking.controller;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.CreateParkingDTO;
import org.kenuki.smartparking.models.dtos.FindParkingDTO;
import org.kenuki.smartparking.services.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking")
@AllArgsConstructor
@CrossOrigin
public class ParkingController {
    private final ParkingService parkingService;
    @GetMapping("/find")
    ResponseEntity<?> findParking(@RequestBody FindParkingDTO findParkingDTO) {
        return parkingService.findParking(findParkingDTO);
    }
    @PostMapping("/create")
    ResponseEntity<?> createParking(@RequestBody CreateParkingDTO createParkingDTO) {
        return parkingService.createParking(createParkingDTO);
    }
}
