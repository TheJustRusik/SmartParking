package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.composites.ParkingReviewKey;
import org.kenuki.smartparking.models.dtos.ReviewRequestDTO;
import org.kenuki.smartparking.models.dtos.ReviewResponseDTO;
import org.kenuki.smartparking.models.enities.Parking;
import org.kenuki.smartparking.models.enities.ParkingReview;
import org.kenuki.smartparking.repositories.ParkingRepository;
import org.kenuki.smartparking.repositories.ParkingReviewRepository;
import org.kenuki.smartparking.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ParkingReviewRepository parkingReviewRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;
    public ResponseEntity<?> getReviewForParking(Long id) {
        try {
            List<ReviewResponseDTO> response = new ArrayList<>();
            var request = parkingReviewRepository.findAllByParking(parkingRepository.findById(id).orElseThrow(() -> new Exception("parking not found!")));
            request.forEach(parking -> {
                ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
                reviewResponseDTO.setUser(parking.getUser().getNickname());
                reviewResponseDTO.setComment(parking.getComment());
                reviewResponseDTO.setGrade(parking.getGrade());
                response.add(reviewResponseDTO);
            });
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> addReviewForParking(ReviewRequestDTO reviewResponseDTO, String name) {
        ParkingReview parkingReview = new ParkingReview();
        parkingReview.setParking(parkingRepository.findById(reviewResponseDTO.getParking_id()).orElseThrow());
        parkingReview.setUser(userRepository.findByNicknameOrEmail(name, name).orElseThrow());
        parkingReview.setGrade(reviewResponseDTO.getGrade());
        if (reviewResponseDTO.getComment() != null)
            parkingReview.setComment(reviewResponseDTO.getComment());
        Parking parking = parkingRepository.findById(reviewResponseDTO.getParking_id()).orElseThrow();
        parking.getParkingReviews().add(parkingReview);


        parkingRepository.save(parking);
        parkingReview.setParking(parking);
        ParkingReviewKey parkingReviewKey = new ParkingReviewKey(userRepository.findByNicknameOrEmail(name, name).get().getId(), reviewResponseDTO.getParking_id());
        parkingReview.setId(parkingReviewKey);
        parkingReviewRepository.save(parkingReview);
        return ResponseEntity.ok("Saved");

    }
}
