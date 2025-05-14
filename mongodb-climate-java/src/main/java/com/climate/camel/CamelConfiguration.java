package com.climate.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfiguration extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(",");
        csv.setSkipHeaderRecord("true");

        // CSV Import Route
        from("direct:importCsv")
            .log("Importing CSV from ${body}")
            .to("file:data?fileName=${body}")
            .unmarshal(csv)
            .to("direct:processClimateData")
            .log("CSV import completed");

        // Analysis Routes
        from("direct:temperatureTrends")
            .log("Analyzing temperature trends")
            .to("bean:climateAnalyzer?method=analyzeTemperature");

        from("direct:co2Analysis")
            .log("Analyzing CO2 trends")
            .to("bean:climateAnalyzer?method=analyzeCo2");

        from("direct:correlation")
            .log("Analyzing correlation")
            .to("bean:climateAnalyzer?method=analyzeCorrelation");

        from("direct:projection")
            .log("Calculating projections")
            .to("bean:climateAnalyzer?method=calculateProjections");
    }
}
