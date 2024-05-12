package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.composites.ParkingReviewKey;
import org.kenuki.smartparking.models.enities.ParkingReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingReviewRepository extends JpaRepository<ParkingReview, ParkingReviewKey> {
}
