package org.kenuki.smartparking.models.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.collection.spi.PersistentSet;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;
    @Column(name = "max_places")
    private Integer maxPlaces;
    @Column(name = "free_places")
    private Integer freePlaces;
    @Column(name = "rent_per_hour")
    private Double rentPerHour;
    private Double rating;

    @OneToMany(mappedBy = "parking")
    private Set<ParkingReview> parkingReviews = new HashSet<>();
    @OneToMany(mappedBy = "parking")
    private Set<ParkingPoint> parkingPoints = new HashSet<>();
}
