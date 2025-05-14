# 🌍 Climate Data Analysis Platform

![License](https://img.shields.io/badge/License-MIT-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.9-6DB33F.svg)
![MongoDB](https://img.shields.io/badge/MongoDB-Latest-47A248.svg)
![Apache Camel](https://img.shields.io/badge/Apache_Camel-3.20.1-red.svg)

A sophisticated Java-based platform for climate data analysis, leveraging Spring Boot, MongoDB, and Apache Camel to provide robust functionality for importing, storing, analyzing, and visualizing climate data with advanced geospatial capabilities.

## ✨ Features

- **🗄️ Comprehensive Data Model**: Store detailed climate metrics including temperature, precipitation, CO2 concentrations, and more
- **🔍 Advanced Geospatial Queries**: Search for climate data based on coordinates, proximity, and geographic boundaries
- **📊 Climate Trend Analysis**: Analyze temperature trends, CO2 concentration changes, and correlations between different climate factors
- **🔮 Future Projections**: Generate climate projections based on historical data
- **📥 Data Import System**: Import climate data from CSV files via Apache Camel
- **🚀 High Performance**: Optimized MongoDB indexing for fast data retrieval
- **⚙️ Flexible API**: RESTful API for easy integration with other systems

## 🏗️ Architecture

This application follows a clean, modular architecture:

```
mongodb-climate-java/
├── src/main/java/com/climate/
│   ├── model/            # Data models
│   ├── repository/       # MongoDB repositories
│   ├── service/          # Business logic layer
│   ├── camel/            # Apache Camel routes and processors
│   ├── controller/       # REST endpoints
│   ├── config/           # Application configuration
│   └── util/             # Utility classes
```

### Components

- **Spring Boot**: Application framework and dependency injection
- **Spring Data MongoDB**: Database access and repository patterns
- **Apache Camel**: Enterprise integration and data processing pipelines
- **Lombok**: Reduces boilerplate code for model classes
- **Bean Validation**: Ensures data integrity

## 🚀 Getting Started

### Prerequisites

- Java 17+
- MongoDB 4.4+
- Maven 3.6+

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/mongodb-climate-java.git
   cd mongodb-climate-java
   ```

2. Configure MongoDB connection in `application.properties`
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/climate_db
   ```

3. Build the application
   ```bash
   mvn clean install
   ```

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

## 📊 Data Model

The central `ClimateData` entity captures comprehensive climate information:

- Basic metrics: temperature, precipitation, humidity
- Atmospheric measurements: CO2 concentration, particulate matter
- Geographic data: latitude, longitude, altitude, proximity to water
- Environmental indices: urbanization index, vegetation index, ENSO index
- GeoJSON location for spatial queries
- Vector embeddings for AI analysis

## 🔍 Query Capabilities

### Standard Queries
- Filter by year, month
- Filter by temperature ranges
- Filter by CO2 concentration

### Geospatial Queries
- Find data near a specific location
- Find data within a geographic rectangle
- Find data within a polygon boundary

## 📊 Data Sources

The application includes sample climate data from reputable sources:

### NOAA Climate Data

We've included processed climate data from the NOAA (National Oceanic and Atmospheric Administration), which includes:
- Global temperature anomalies from 2020-2023
- Monthly climate metrics for major cities
- Data transformed to match our ClimateData model

### Sample Data Files

The following data files are available in the application:

1. `/src/main/resources/data/climate_data_2020_2024.csv`
   - Comprehensive climate dataset with monthly records from 2020-2023
   - Includes temperature, precipitation, CO2 levels, and geographic indices
   - Data points for New York, San Francisco, Los Angeles, and Chicago

2. `/src/main/resources/templates/climate_data_template.csv`
   - Template file for importing your own climate data
   - Contains all required fields in the ClimateData model

3. `/src/main/resources/templates/disease_data_template.csv`
   - Template for disease prevalence data that can be correlated with climate factors
   - Includes sample data for diseases like Dengue, Malaria, and Zika

### Data Import Instructions

To import climate data into the application:

1. Start the application
2. Navigate to the Import Data page in the frontend
3. Upload one of the CSV files
4. The data will be automatically processed and stored in MongoDB

### Data Sources for Further Research

For additional climate data, consider these reliable sources:

- [NOAA Climate Data Online](https://www.ncdc.noaa.gov/cdo-web/) - Historical weather and climate data
- [NASA GISS Surface Temperature Analysis](https://data.giss.nasa.gov/gistemp/) - Global temperature data
- [Global Carbon Project](https://www.globalcarbonproject.org/carbonbudget/) - CO2 emissions data
- [WHO Global Health Observatory](https://www.who.int/data/gho) - Disease statistics worldwide

## 🔄 Data Processing

Climate data processing leverages Apache Camel with routes defined for:

- CSV data import and transformation
- Temperature trend analysis
- CO2 pattern analysis
- Climate factor correlation analysis
- Future climate projection calculations

## 🧪 Testing

Run the test suite with:

```bash
mvn test
```

The application includes comprehensive tests for repository methods and service-layer functionality.

## 📖 Documentation

API documentation is available at `/swagger-ui.html` when running the application.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🔮 Future Enhancements

- Machine learning integration for advanced climate predictions
- Real-time data ingestion from climate sensors
- Interactive data visualization dashboard
- Support for additional data formats (NetCDF, HDF5)
- Integration with external climate data APIs

---

*Built with ❤️ for climate science and data analysis*
