package com.climate.service.impl;

import com.climate.model.ClimateData;
import com.climate.repository.ClimateDataRepository;
import com.climate.service.ClimateDataService;
import org.apache.camel.ProducerTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClimateDataServiceImpl implements ClimateDataService {

    private final ClimateDataRepository repository;
    private final ProducerTemplate producerTemplate;

    public ClimateDataServiceImpl(ClimateDataRepository repository, ProducerTemplate producerTemplate) {
        this.repository = repository;
        this.producerTemplate = producerTemplate;
    }

    @Override
    public List<ClimateData> getAllClimateData() {
        return repository.findAll();
    }

    @Override
    public Page<ClimateData> getAllClimateData(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<ClimateData> getClimateDataById(String id) {
        return repository.findById(id);
    }

    @Override
    public ClimateData saveClimateData(ClimateData climateData) {
        return repository.save(climateData);
    }

    @Override
    public List<ClimateData> saveAllClimateData(List<ClimateData> climateDataList) {
        return repository.saveAll(climateDataList);
    }

    @Override
    public void deleteClimateData(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<ClimateData> getClimateDataByYear(Integer year) {
        return repository.findByYear(year);
    }

    @Override
    public Page<ClimateData> getClimateDataByYear(Integer year, Pageable pageable) {
        return repository.findByYear(year, pageable);
    }

    @Override
    public List<ClimateData> getClimateDataByMonth(String month) {
        return repository.findByMonth(month);
    }

    @Override
    public Page<ClimateData> getClimateDataByMonth(String month, Pageable pageable) {
        return repository.findByMonth(month, pageable);
    }

    @Override
    public List<ClimateData> getClimateDataByYearAndMonth(Integer year, String month) {
        return repository.findByYearAndMonth(year, month);
    }

    @Override
    public List<ClimateData> getClimateDataByTemperatureRange(Double minTemp, Double maxTemp) {
        return repository.findByAvgTempBetween(minTemp, maxTemp);
    }

    @Override
    public List<ClimateData> getClimateDataByCo2Range(Double minCo2, Double maxCo2) {
        return repository.findByCo2ConcentrationBetween(minCo2, maxCo2);
    }

    @Override
    public List<ClimateData> getClimateDataByLocation(Double latitude, Double longitude, Double distanceKm) {
        // Convert km to radians (approximate conversion: 1km ≈ 0.001 radians)
        double radiusInRadians = distanceKm * 0.001;
        return repository.findNearLocation(longitude, latitude, radiusInRadians);
    }
    
    public List<ClimateData> getClimateDataWithinBox(Double lowerLeftLat, Double lowerLeftLon, 
                                                 Double upperRightLat, Double upperRightLon) {
        return repository.findWithinBox(lowerLeftLon, lowerLeftLat, upperRightLon, upperRightLat);
    }
    
    public List<ClimateData> getClimateDataWithinPolygon(List<double[]> coordinates) {
        return repository.findWithinPolygon(coordinates);
    }

    @Override
    public Map<String, Object> getTemperatureTrends(Integer startYear, Integer endYear) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", producerTemplate.requestBody("direct:temperatureTrends", Map.of("startYear", startYear, "endYear", endYear)));
        return result;
    }

    @Override
    public Map<String, Object> getCo2Analysis(Integer startYear, Integer endYear) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", producerTemplate.requestBody("direct:co2Analysis", Map.of("startYear", startYear, "endYear", endYear)));
        return result;
    }

    @Override
    public Map<String, Object> getCorrelationAnalysis(String factorX, String factorY) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", producerTemplate.requestBody("direct:correlation", Map.of("factorX", factorX, "factorY", factorY)));
        return result;
    }

    @Override
    public Map<String, Object> getFutureProjections(String factor, Integer years) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", producerTemplate.requestBody("direct:projection", Map.of("factor", factor, "years", years)));
        return result;
    }

    @Override
    public Map<String, Object> importDataFromCsv(String filePath) {
        return producerTemplate.requestBody("direct:importCsv", filePath, Map.class);
    }
}
