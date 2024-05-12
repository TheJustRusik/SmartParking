package org.kenuki.smartparking.controller;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.FindParkingDTO;
import org.kenuki.smartparking.services.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking")
@AllArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;
    @GetMapping("/find")
    ResponseEntity<?> findParking(@RequestBody FindParkingDTO findParkingDTO) {
        return parkingService.findParking(findParkingDTO);
    }
}
