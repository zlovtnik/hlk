package com.climate.camel;

import com.climate.model.ClimateData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClimateDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<List<String>> csvData = exchange.getIn().getBody(List.class);
        List<ClimateData> climateDataList = new ArrayList<>();

        for (List<String> row : csvData) {
            ClimateData data = new ClimateData();
            // Map CSV data to ClimateData object
            // Implement mapping logic based on your CSV structure
            climateDataList.add(data);
        }

        exchange.getMessage().setBody(climateDataList);
    }
}
