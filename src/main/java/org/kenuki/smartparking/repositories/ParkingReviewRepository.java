package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.composites.ParkingReviewKey;
import org.kenuki.smartparking.models.enities.Parking;
import org.kenuki.smartparking.models.enities.ParkingReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingReviewRepository extends JpaRepository<ParkingReview, ParkingReviewKey> {
    List<ParkingReview> findAllByParking(Parking parking);
}
