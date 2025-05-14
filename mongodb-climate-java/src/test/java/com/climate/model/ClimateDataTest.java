package com.climate.model;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ClimateDataTest {

    @Test
    public void testClimateDataBuilder() {
        ClimateData data = ClimateData.builder()
                .id("test123")
                .year(2023)
                .month("January")
                .avgTemp(25.5)
                .maxTemp(32.1)
                .minTemp(18.9)
                .precipitation(45.6)
                .humidity(68.2)
                .windSpeed(12.3)
                .solarIrradiance(890.5)
                .cloudCover(45.0)
                .co2Concentration(416.5)
                .latitude(40.7128)
                .longitude(-74.0060)
                .altitude(10.0)
                .proximityToWater(5.2)
                .urbanizationIndex(85.3)
                .vegetationIndex(42.1)
                .ensoIndex(1.2)
                .particulateMatter(25.4)
                .seaSurfaceTemp(22.3)
                .embedding(Arrays.asList(0.1, 0.2, 0.3))
                .build();
        
        // Verify all properties are set correctly
        assertEquals("test123", data.getId());
        assertEquals(Integer.valueOf(2023), data.getYear());
        assertEquals("January", data.getMonth());
        assertEquals(25.5, data.getAvgTemp(), 0.001);
        assertEquals(32.1, data.getMaxTemp(), 0.001);
        assertEquals(18.9, data.getMinTemp(), 0.001);
        assertEquals(45.6, data.getPrecipitation(), 0.001);
        assertEquals(68.2, data.getHumidity(), 0.001);
        assertEquals(12.3, data.getWindSpeed(), 0.001);
        assertEquals(890.5, data.getSolarIrradiance(), 0.001);
        assertEquals(45.0, data.getCloudCover(), 0.001);
        assertEquals(416.5, data.getCo2Concentration(), 0.001);
        assertEquals(40.7128, data.getLatitude(), 0.001);
        assertEquals(-74.0060, data.getLongitude(), 0.001);
        assertEquals(10.0, data.getAltitude(), 0.001);
        assertEquals(5.2, data.getProximityToWater(), 0.001);
        assertEquals(85.3, data.getUrbanizationIndex(), 0.001);
        assertEquals(42.1, data.getVegetationIndex(), 0.001);
        assertEquals(1.2, data.getEnsoIndex(), 0.001);
        assertEquals(25.4, data.getParticulateMatter(), 0.001);
        assertEquals(22.3, data.getSeaSurfaceTemp(), 0.001);
        assertEquals(3, data.getEmbedding().size());
    }
    
    @Test
    public void testMonthAsNumberConversion() {
        ClimateData data = new ClimateData();
        
        // Test all months
        data.setMonth("January");
        assertEquals(1, data.getMonthAsNumber());
        
        data.setMonth("February");
        assertEquals(2, data.getMonthAsNumber());
        
        data.setMonth("March");
        assertEquals(3, data.getMonthAsNumber());
        
        data.setMonth("April");
        assertEquals(4, data.getMonthAsNumber());
        
        data.setMonth("May");
        assertEquals(5, data.getMonthAsNumber());
        
        data.setMonth("June");
        assertEquals(6, data.getMonthAsNumber());
        
        data.setMonth("July");
        assertEquals(7, data.getMonthAsNumber());
        
        data.setMonth("August");
        assertEquals(8, data.getMonthAsNumber());
        
        data.setMonth("September");
        assertEquals(9, data.getMonthAsNumber());
        
        data.setMonth("October");
        assertEquals(10, data.getMonthAsNumber());
        
        data.setMonth("November");
        assertEquals(11, data.getMonthAsNumber());
        
        data.setMonth("December");
        assertEquals(12, data.getMonthAsNumber());
        
        // Test case insensitivity
        data.setMonth("january");
        assertEquals(1, data.getMonthAsNumber());
        
        // Test invalid month
        data.setMonth("NotAMonth");
        assertEquals(0, data.getMonthAsNumber());
    }
    
    @Test
    public void testSetCoordinates() {
        ClimateData data = new ClimateData();
        data.setCoordinates(-74.0060, 40.7128);
        
        // Verify GeoJsonPoint is created correctly
        assertNotNull(data.getLocation());
        assertEquals(-74.0060, data.getLocation().getX(), 0.001);
        assertEquals(40.7128, data.getLocation().getY(), 0.001);
    }
    
    @Test
    public void testEqualsAndHashCode() {
        ClimateData data1 = ClimateData.builder()
                .id("test123")
                .year(2023)
                .month("January")
                .avgTemp(25.5)
                .build();
                
        ClimateData data2 = ClimateData.builder()
                .id("test123")
                .year(2023)
                .month("January")
                .avgTemp(25.5)
                .build();
                
        ClimateData data3 = ClimateData.builder()
                .id("differentId")
                .year(2023)
                .month("January")
                .avgTemp(25.5)
                .build();
        
        // Test equals
        assertEquals(data1, data2);
        assertNotEquals(data1, data3);
        
        // Test hashCode
        assertEquals(data1.hashCode(), data2.hashCode());
    }
    
    @Test
    public void testToString() {
        ClimateData data = ClimateData.builder()
                .id("test123")
                .year(2023)
                .month("January")
                .avgTemp(25.5)
                .build();
                
        String toString = data.toString();
        
        // Verify toString contains key fields
        assertTrue(toString.contains("test123"));
        assertTrue(toString.contains("2023"));
        assertTrue(toString.contains("January"));
        assertTrue(toString.contains("25.5"));
    }
}
