package org.kenuki.smartparking.controller;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.ReviewRequestDTO;
import org.kenuki.smartparking.models.dtos.ReviewResponseDTO;
import org.kenuki.smartparking.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("/{id}")
    ResponseEntity<?> getReviewForParking(@PathVariable Long id) {
        return reviewService.getReviewForParking(id);
    }
    @PostMapping("/")
    ResponseEntity<?> sendReviewForParking(@RequestBody ReviewRequestDTO reviewResponseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return reviewService.addReviewForParking(reviewResponseDTO, authentication.getName());
    }

}
