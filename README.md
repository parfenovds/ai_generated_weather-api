# AI Generated Weather Application

This project is a weather API that provides real-time weather data for various cities around the world, leveraging data from OpenWeather. It allows users to access current weather information for different locations.

## Setup Instructions

### Prerequisites

Before running the application, make sure you have:

- Java Development Kit (JDK)
- Apache Maven
- Docker (for running PostgreSQL as a container)

### Configuration

1. **Clone the repository:**

   ```bash
   git clone https://github.com/parfenovds/ai_generated_weather-api
   ```

    Open the application.properties file in src/main/resources:
        Set your database credentials (spring.datasource.username and spring.datasource.password).
        For the first run, set spring.jpa.hibernate.ddl-auto=create to generate tables. After the first run, revert it to validate.

2. **Running the Application**

   2.1. Start PostgreSQL Docker Container:
  
     ```bash
      docker run --name weather-postgres -e POSTGRES_PASSWORD=mysecretpassword POSTGRES_DB=weather-db -p 5432:5432 -d postgres
     ```
  
    2.2. Navigate to the project directory:
  
     ```bash
      cd ai_generated_weather-api
     ```
  
    2.3. Run the application:
  
     ```bash
      mvn spring-boot:run
     ```

3. **The API endpoints available are:**
    Get Weather by City Name

    Endpoint: /temperature
    Method: GET
    Parameters: cityName (String)
    Response: Current temperature for the specified city.
    GET http://localhost:8083/temperature?cityName=NewYork


    Get All Cities

    Endpoint: /cities
    Method: GET
    Response: List of all available cities.
    GET http://localhost:8083/cities
4. **Data Source**

This API retrieves weather data from OpenWeather to provide accurate and up-to-date information.

5. **Answers:**
   
- Was it easy to complete the task using AI? - It was not hard.
- How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics) - Around 4 hours.
- Was the code ready to run after generation? What did you have to change to make it usable? - I've had to make some improvements and ask a lot of additional questions to make it to work.
- Which specific prompts you learned as a good practice to complete the task? - There were no specific prompts, mostly simple questions in my native language with a lot of context.
