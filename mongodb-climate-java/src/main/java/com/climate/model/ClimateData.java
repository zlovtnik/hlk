package com.climate.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "climate_data")
@CompoundIndexes({
    @CompoundIndex(name = "year_month_idx", def = "{'year': 1, 'month': 1}")
})
public class ClimateData {
    
    @Id
    private String id;
    
    @NotNull
    @Min(2020)
    @Max(2024)
    @Indexed
    private Integer year;
    
    @NotNull
    private String month;
    
    @NotNull
    @Indexed
    private Double avgTemp;
    
    @NotNull
    private Double maxTemp;
    
    @NotNull
    private Double minTemp;
    
    @NotNull
    private Double precipitation;
    
    @NotNull
    @Min(0)
    @Max(100)
    private Double humidity;
    
    @NotNull
    @Min(0)
    private Double windSpeed;
    
    @NotNull
    @Min(0)
    private Double solarIrradiance;
    
    @NotNull
    @Min(0)
    @Max(100)
    private Double cloudCover;
    
    @NotNull
    @Min(0)
    @Indexed
    private Double co2Concentration;
    
    @NotNull
    @Min(-90)
    @Max(90)
    private Double latitude;
    
    @NotNull
    @Min(-180)
    @Max(180)
    private Double longitude;
    
    @NotNull
    private Double altitude;
    
    @NotNull
    @Min(0)
    private Double proximityToWater;
    
    @NotNull
    @Min(0)
    private Double urbanizationIndex;
    
    @NotNull
    @Min(0)
    private Double vegetationIndex;
    
    @NotNull
    private Double ensoIndex;
    
    @NotNull
    @Min(0)
    private Double particulateMatter;
    
    @NotNull
    private Double seaSurfaceTemp;
    
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    
    // Vector embedding for AI analysis
    private List<Double> embedding;
    
    // Helper method to get month as number (1-12)
    public int getMonthAsNumber() {
        return switch(month.toLowerCase()) {
            case "january" -> 1;
            case "february" -> 2;
            case "march" -> 3;
            case "april" -> 4;
            case "may" -> 5;
            case "june" -> 6;
            case "july" -> 7;
            case "august" -> 8;
            case "september" -> 9;
            case "october" -> 10;
            case "november" -> 11;
            case "december" -> 12;
            default -> 0;
        };
    }
    
    // Method to create GeoJSON point from latitude and longitude
    public void setCoordinates(Double longitude, Double latitude) {
        this.location = new GeoJsonPoint(longitude, latitude);
    }
    
    // Method to directly set the location GeoJsonPoint
    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
