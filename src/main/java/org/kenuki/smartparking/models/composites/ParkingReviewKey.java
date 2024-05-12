package org.kenuki.smartparking.models.composites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ParkingReviewKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "parking_id")
    private Long parkingId;
}
