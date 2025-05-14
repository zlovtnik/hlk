package com.climate.repository;

import com.climate.model.ClimateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class ClimateDataRepositoryTest {

    @Autowired
    private ClimateDataRepository repository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @BeforeEach
    public void setup() {
        // Clear the collection before each test
        mongoTemplate.dropCollection(ClimateData.class);
        
        // Create test data
        ClimateData data1 = createClimateData("1", 2022, "January", 25.5, 30.2, 20.1, 415.0, 40.7128, -74.0060);
        ClimateData data2 = createClimateData("2", 2022, "February", 26.5, 31.2, 21.1, 416.0, 40.7130, -74.0062);
        ClimateData data3 = createClimateData("3", 2023, "January", 24.5, 29.2, 19.1, 417.0, 34.0522, -118.2437);
        
        // Save the test data
        repository.saveAll(Arrays.asList(data1, data2, data3));
    }
    
    private ClimateData createClimateData(String id, Integer year, String month, Double avgTemp, 
                                         Double maxTemp, Double minTemp, Double co2, 
                                         Double latitude, Double longitude) {
        ClimateData data = ClimateData.builder()
                .id(id)
                .year(year)
                .month(month)
                .avgTemp(avgTemp)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .precipitation(45.6)
                .humidity(68.2)
                .windSpeed(12.3)
                .solarIrradiance(890.5)
                .cloudCover(45.0)
                .co2Concentration(co2)
                .latitude(latitude)
                .longitude(longitude)
                .altitude(10.0)
                .proximityToWater(5.2)
                .urbanizationIndex(85.3)
                .vegetationIndex(42.1)
                .ensoIndex(1.2)
                .particulateMatter(25.4)
                .seaSurfaceTemp(22.3)
                .build();
                
        // Set GeoJSON point for MongoDB geospatial queries
        data.setLocation(new GeoJsonPoint(longitude, latitude));
        
        return data;
    }
    
    @Test
    public void testFindAll() {
        List<ClimateData> data = repository.findAll();
        assertEquals(3, data.size());
    }
    
    @Test
    public void testFindById() {
        ClimateData data = repository.findById("1").orElse(null);
        assertNotNull(data);
        assertEquals(2022, data.getYear());
        assertEquals("January", data.getMonth());
    }
    
    @Test
    public void testFindByYear() {
        List<ClimateData> data2022 = repository.findByYear(2022);
        assertEquals(2, data2022.size());
        
        List<ClimateData> data2023 = repository.findByYear(2023);
        assertEquals(1, data2023.size());
        
        List<ClimateData> data2024 = repository.findByYear(2024);
        assertEquals(0, data2024.size());
    }
    
    @Test
    public void testFindByYearPaginated() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<ClimateData> data2022 = repository.findByYear(2022, pageable);
        
        assertEquals(1, data2022.getContent().size());
        assertEquals(2, data2022.getTotalElements());
        assertEquals(2, data2022.getTotalPages());
    }
    
    @Test
    public void testFindByMonth() {
        List<ClimateData> dataJan = repository.findByMonth("January");
        assertEquals(2, dataJan.size());
        
        List<ClimateData> dataFeb = repository.findByMonth("February");
        assertEquals(1, dataFeb.size());
        
        List<ClimateData> dataMar = repository.findByMonth("March");
        assertEquals(0, dataMar.size());
    }
    
    @Test
    public void testFindByMonthPaginated() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<ClimateData> dataJan = repository.findByMonth("January", pageable);
        
        assertEquals(1, dataJan.getContent().size());
        assertEquals(2, dataJan.getTotalElements());
        assertEquals(2, dataJan.getTotalPages());
    }
    
    @Test
    public void testFindByYearAndMonth() {
        List<ClimateData> data = repository.findByYearAndMonth(2022, "January");
        assertEquals(1, data.size());
        assertEquals("1", data.get(0).getId());
    }
    
    @Test
    public void testFindByAvgTempBetween() {
        List<ClimateData> data = repository.findByAvgTempBetween(25.0, 27.0);
        assertEquals(2, data.size());
    }
    
    @Test
    public void testFindByAvgTempBetweenPaginated() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<ClimateData> data = repository.findByAvgTempBetween(24.0, 27.0, pageable);
        
        assertEquals(1, data.getContent().size());
        assertEquals(3, data.getTotalElements());
        assertEquals(3, data.getTotalPages());
    }
    
    @Test
    public void testFindByCo2ConcentrationBetween() {
        List<ClimateData> data = repository.findByCo2ConcentrationBetween(415.5, 417.5);
        assertEquals(2, data.size());
    }
    
    @Test
    public void testFindByCo2ConcentrationBetweenPaginated() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<ClimateData> data = repository.findByCo2ConcentrationBetween(415.0, 417.0, pageable);
        
        assertEquals(1, data.getContent().size());
        assertEquals(3, data.getTotalElements());
        assertEquals(3, data.getTotalPages());
    }
    
    @Test
    public void testFindByLocationNear() {
        // NYC coordinates
        double longitude = -74.006;
        double latitude = 40.7128;
        
        // Convert 10km to radians (approx 0.001 radians per km)
        double radiusInRadians = 10 * 0.001;
        
        List<ClimateData> nearbyData = repository.findNearLocation(longitude, latitude, radiusInRadians);
        assertEquals(2, nearbyData.size());
    }
    
    @Test
    public void testFindByLocationNearPaginated() {
        // NYC coordinates
        double longitude = -74.006;
        double latitude = 40.7128;
        
        // Convert 10km to radians (approx 0.001 radians per km)
        double radiusInRadians = 10 * 0.001;
        
        Pageable pageable = PageRequest.of(0, 1);
        
        Page<ClimateData> nearbyData = repository.findNearLocationPaged(longitude, latitude, radiusInRadians, pageable);
        
        assertEquals(1, nearbyData.getContent().size());
        assertEquals(2, nearbyData.getTotalElements());
        assertEquals(2, nearbyData.getTotalPages());
    }
    
    @Test
    public void testFindByYearBetweenAndAvgTempGreaterThan() {
        List<ClimateData> data = repository.findByYearBetweenAndAvgTempGreaterThan(2022, 2023, 25.0);
        assertEquals(2, data.size());
    }
    
    @Test
    public void testFindDataForCorrelation() {
        List<ClimateData> data = repository.findDataForCorrelation(2022, 2023, "avgTemp", "co2Concentration");
        assertEquals(3, data.size());
    }
    
    @Test
    public void testFindYearlyData() {
        List<ClimateData> data = repository.findYearlyData();
        assertEquals(3, data.size());
    }
}
