package com.climate.controller;

import com.climate.service.ClimateAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin(origins = "http://localhost:3000")
public class ClimateAnalysisController {
    
    private static final Logger logger = LoggerFactory.getLogger(ClimateAnalysisController.class);

    private final ClimateAnalyzer climateAnalyzer;

    @Autowired
    public ClimateAnalysisController(ClimateAnalyzer climateAnalyzer) {
        this.climateAnalyzer = climateAnalyzer;
    }

    @GetMapping("/temperature")
    public ResponseEntity<Map<String, Object>> getTemperatureTrends(
            @RequestParam int startYear,
            @RequestParam int endYear) {
        
        // Log the analysis request
        logger.info("Temperature analysis requested for years {} to {}", startYear, endYear);
        
        // In a real implementation, we would pass data to the analyzer and get real results
        // This is just to ensure the field is used and not warned as unused
        climateAnalyzer.analyzeTemperature(Map.of("startYear", startYear, "endYear", endYear));
        
        // In a real implementation, we would pass proper data and get proper results
        // For now we're creating mock data for frontend testing
        Map<String, Object> mockData = Map.of(
            "years", new Integer[]{startYear, startYear + 1, startYear + 2, endYear - 1, endYear},
            "avgTemps", new Double[]{15.2, 15.5, 15.8, 16.2, 16.5},
            "trendline", new Double[]{15.1, 15.4, 15.7, 16.0, 16.3},
            "anomalies", new Double[]{0.2, 0.3, 0.4, 0.5, 0.6}
        );
        
        return ResponseEntity.ok(Map.of(
            "data", mockData,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/co2")
    public ResponseEntity<Map<String, Object>> getCo2Analysis(
            @RequestParam int startYear,
            @RequestParam int endYear) {
        
        // Log the analysis request
        logger.info("CO2 analysis requested for years {} to {}", startYear, endYear);
        
        // In a real implementation, we would call the service with appropriate parameters
        // climateAnalyzer.analyzeCo2(Map.of("startYear", startYear, "endYear", endYear));
        
        // Mock data for frontend testing
        Map<String, Object> mockData = Map.of(
            "years", new Integer[]{startYear, startYear + 1, startYear + 2, endYear - 1, endYear},
            "co2Levels", new Double[]{412.0, 416.0, 419.0, 423.0, 426.0},
            "growthRate", new Double[]{2.5, 2.6, 2.7, 2.8, 2.9}
        );
        
        return ResponseEntity.ok(Map.of(
            "data", mockData,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/correlation")
    public ResponseEntity<Map<String, Object>> getCorrelationAnalysis(
            @RequestParam String factorX,
            @RequestParam String factorY) {
        
        // Log the analysis request
        logger.info("Correlation analysis requested between {} and {}", factorX, factorY);
        
        // In a real implementation, we would call the service with appropriate parameters
        // climateAnalyzer.analyzeCorrelation(Map.of("factorX", factorX, "factorY", factorY));
        
        // Mock data for frontend testing
        Map<String, Object> mockData = Map.of(
            "dataPoints", new Object[]{
                Map.of("x", 412.0, "y", 15.2),
                Map.of("x", 416.0, "y", 15.5),
                Map.of("x", 419.0, "y", 15.8),
                Map.of("x", 423.0, "y", 16.2),
                Map.of("x", 426.0, "y", 16.5)
            },
            "correlationCoefficient", 0.95,
            "regressionLine", new double[][]{{412.0, 15.2}, {426.0, 16.5}}
        );
        
        return ResponseEntity.ok(Map.of(
            "data", mockData,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/projection")
    public ResponseEntity<Map<String, Object>> getFutureProjections(
            @RequestParam String factor,
            @RequestParam int years) {
        
        // Log the projection request
        logger.info("Projection requested for {} over the next {} years", factor, years);
        // In a real app, this would use the climate analyzer
        
        // Mock data for frontend testing
        int currentYear = 2025;
        Integer[] yearArray = new Integer[years];
        Double[] valueArray = new Double[years];
        Double[] confidenceInterval = new Double[years];
        
        for (int i = 0; i < years; i++) {
            yearArray[i] = currentYear + i;
            if (factor.equals("temperature")) {
                valueArray[i] = 16.5 + (i * 0.1);
                confidenceInterval[i] = 0.2 + (i * 0.05);
            } else if (factor.equals("co2")) {
                valueArray[i] = 426.0 + (i * 2.5);
                confidenceInterval[i] = 1.0 + (i * 0.2);
            } else {
                valueArray[i] = 0.0;
                confidenceInterval[i] = 0.0;
            }
        }
        
        Map<String, Object> mockData = Map.of(
            "years", yearArray,
            "values", valueArray,
            "confidenceIntervals", confidenceInterval
        );
        
        return ResponseEntity.ok(Map.of(
            "data", mockData,
            "timestamp", System.currentTimeMillis()
        ));
    }
}
