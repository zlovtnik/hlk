package com.climate.controller;

import com.climate.model.ClimateData;
import com.climate.service.ClimateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/climate")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ClimateDataController {

    private final ClimateDataService climateDataService;

    @Autowired
    public ClimateDataController(ClimateDataService climateDataService) {
        this.climateDataService = climateDataService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllClimateData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<ClimateData> climateDataPage = climateDataService.getAllClimateData(PageRequest.of(page, size));
        
        Map<String, Object> response = Map.of(
            "content", climateDataPage.getContent(),
            "totalPages", climateDataPage.getTotalPages(),
            "totalElements", climateDataPage.getTotalElements(),
            "currentPage", climateDataPage.getNumber()
        );
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClimateData> getClimateDataById(@PathVariable String id) {
        Optional<ClimateData> climateData = climateDataService.getClimateDataById(id);
        return climateData.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<Map<String, Object>> getClimateDataByYear(
            @PathVariable int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<ClimateData> climateDataPage = climateDataService.getClimateDataByYear(year, PageRequest.of(page, size));
        
        Map<String, Object> response = Map.of(
            "content", climateDataPage.getContent(),
            "totalPages", climateDataPage.getTotalPages(),
            "totalElements", climateDataPage.getTotalElements(),
            "currentPage", climateDataPage.getNumber()
        );
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<Map<String, Object>> getClimateDataByMonth(
            @PathVariable String month,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<ClimateData> climateDataPage = climateDataService.getClimateDataByMonth(month, PageRequest.of(page, size));
        
        Map<String, Object> response = Map.of(
            "content", climateDataPage.getContent(),
            "totalPages", climateDataPage.getTotalPages(),
            "totalElements", climateDataPage.getTotalElements(),
            "currentPage", climateDataPage.getNumber()
        );
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/year/{year}/month/{month}")
    public ResponseEntity<List<ClimateData>> getClimateDataByYearAndMonth(
            @PathVariable int year,
            @PathVariable String month) {
        
        List<ClimateData> climateData = climateDataService.getClimateDataByYearAndMonth(year, month);
        return ResponseEntity.ok(climateData);
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<ClimateData>> getClimateDataByTemperatureRange(
            @RequestParam double min,
            @RequestParam double max) {
        
        List<ClimateData> climateData = climateDataService.getClimateDataByTemperatureRange(min, max);
        return ResponseEntity.ok(climateData);
    }

    @GetMapping("/co2")
    public ResponseEntity<List<ClimateData>> getClimateDataByCo2Range(
            @RequestParam double min,
            @RequestParam double max) {
        
        List<ClimateData> climateData = climateDataService.getClimateDataByCo2Range(min, max);
        return ResponseEntity.ok(climateData);
    }

    @GetMapping("/location")
    public ResponseEntity<List<ClimateData>> getClimateDataByLocation(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double distance) {
        
        List<ClimateData> climateData = climateDataService.getClimateDataByLocation(lat, lon, distance);
        return ResponseEntity.ok(climateData);
    }
}
