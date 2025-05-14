package com.climate.repository;

import com.climate.model.ClimateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimateDataRepository extends MongoRepository<ClimateData, String> {

    // Basic CRUD operations are automatically provided by MongoRepository
    
    // Find by year
    List<ClimateData> findByYear(Integer year);
    Page<ClimateData> findByYear(Integer year, Pageable pageable);
    
    // Find by month
    List<ClimateData> findByMonth(String month);
    Page<ClimateData> findByMonth(String month, Pageable pageable);
    
    // Find by year and month
    List<ClimateData> findByYearAndMonth(Integer year, String month);
    
    // Find by temperature range
    @Query("{'avgTemp': {$gte: ?0, $lte: ?1}}")
    List<ClimateData> findByAvgTempBetween(Double minTemp, Double maxTemp);
    Page<ClimateData> findByAvgTempBetween(Double minTemp, Double maxTemp, Pageable pageable);
    
    // Find by CO2 concentration range
    @Query("{'co2Concentration': {$gte: ?0, $lte: ?1}}")
    List<ClimateData> findByCo2ConcentrationBetween(Double minCo2, Double maxCo2);
    @Query("{'co2Concentration': {$gte: ?0, $lte: ?1}}")
    Page<ClimateData> findByCo2ConcentrationBetween(Double minCo2, Double maxCo2, Pageable pageable);
    
    // Find by location within a certain distance using explicit coordinates and distance
    @Query(value = "{location: {$geoWithin: {$centerSphere: [[?0, ?1], ?2]}}}")
    List<ClimateData> findNearLocation(double longitude, double latitude, double radiusInRadians);
    
    @Query(value = "{location: {$geoWithin: {$centerSphere: [[?0, ?1], ?2]}}}")
    Page<ClimateData> findNearLocationPaged(double longitude, double latitude, double radiusInRadians, Pageable pageable);
    
    // Find by location within a box/rectangle
    @Query(value = "{location: {$geoWithin: {$box: [[?0, ?1], [?2, ?3]]}}}")
    List<ClimateData> findWithinBox(double lowerLeftLon, double lowerLeftLat, double upperRightLon, double upperRightLat);
    
    // Find by location within a polygon
    @Query(value = "{location: {$geoWithin: {$polygon: [?0]}}}")
    List<ClimateData> findWithinPolygon(List<double[]> points);
    
    // Complex query with multiple criteria
    @Query("{'year': {$gte: ?0, $lte: ?1}, 'avgTemp': {$gte: ?2}}")
    List<ClimateData> findByYearBetweenAndAvgTempGreaterThan(
            Integer startYear, Integer endYear, Double minTemp);
    
    // Find data for correlation analysis
    @Query(value = "{'year': {$gte: ?0, $lte: ?1}}", 
           fields = "{'year': 1, 'month': 1, ?2: 1, ?3: 1}")
    List<ClimateData> findDataForCorrelation(
            Integer startYear, Integer endYear, String factor1, String factor2);
    
    // Get yearly aggregated data
    @Query(value = "{}", 
           fields = "{'year': 1, 'avgTemp': 1, 'co2Concentration': 1}")
    List<ClimateData> findYearlyData();
}
