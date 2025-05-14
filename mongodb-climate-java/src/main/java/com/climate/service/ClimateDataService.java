package com.climate.service;

import com.climate.model.ClimateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClimateDataService {

    // Basic CRUD operations
    List<ClimateData> getAllClimateData();
    Page<ClimateData> getAllClimateData(Pageable pageable);
    Optional<ClimateData> getClimateDataById(String id);
    ClimateData saveClimateData(ClimateData climateData);
    List<ClimateData> saveAllClimateData(List<ClimateData> climateDataList);
    void deleteClimateData(String id);
    
    // Query operations
    List<ClimateData> getClimateDataByYear(Integer year);
    Page<ClimateData> getClimateDataByYear(Integer year, Pageable pageable);
    List<ClimateData> getClimateDataByMonth(String month);
    Page<ClimateData> getClimateDataByMonth(String month, Pageable pageable);
    List<ClimateData> getClimateDataByYearAndMonth(Integer year, String month);
    List<ClimateData> getClimateDataByTemperatureRange(Double minTemp, Double maxTemp);
    List<ClimateData> getClimateDataByCo2Range(Double minCo2, Double maxCo2);
    List<ClimateData> getClimateDataByLocation(Double latitude, Double longitude, Double distanceKm);
    
    List<ClimateData> getClimateDataWithinBox(Double lowerLeftLat, Double lowerLeftLon, 
                                         Double upperRightLat, Double upperRightLon);
    
    List<ClimateData> getClimateDataWithinPolygon(List<double[]> coordinates);
    
    // Analysis operations
    Map<String, Object> getTemperatureTrends(Integer startYear, Integer endYear);
    Map<String, Object> getCo2Analysis(Integer startYear, Integer endYear);
    Map<String, Object> getCorrelationAnalysis(String factorX, String factorY);
    Map<String, Object> getFutureProjections(String factor, Integer years);
    
    // Data import
    Map<String, Object> importDataFromCsv(String filePath);
}
