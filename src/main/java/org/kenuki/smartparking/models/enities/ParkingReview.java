package org.kenuki.smartparking.models.enities;

import jakarta.persistence.*;
import lombok.*;
import org.kenuki.smartparking.models.composites.ParkingReviewKey;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking_review")
public class ParkingReview {
    @EmbeddedId
    private ParkingReviewKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("parkingId")
    @JoinColumn(name = "parking_id")
    private Parking parking;

    private Short grade;
    private String comment;
    @Column(name = "created_at")
    private Date createdAt;
}
