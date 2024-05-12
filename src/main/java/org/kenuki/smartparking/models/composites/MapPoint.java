package org.kenuki.smartparking.models.composites;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class MapPoint implements Serializable {
    private Double latitude;
    private Double longitude;


}
