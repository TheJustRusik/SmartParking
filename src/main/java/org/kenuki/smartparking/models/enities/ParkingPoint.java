package org.kenuki.smartparking.models.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kenuki.smartparking.models.composites.MapPoint;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking_points")
@IdClass(MapPoint.class)
public class ParkingPoint {
    @Id
    private Double latitude;
    @Id
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;
}
