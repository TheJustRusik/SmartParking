package org.kenuki.smartparking.models.composites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MapPoint implements Serializable {
    private Double latitude;
    private Double longitude;
}
