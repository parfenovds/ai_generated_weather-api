package com.example.ai_generated_weather_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

public class WeatherDataResponseTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testSerialization() throws Exception {
    WeatherMainData mainData = new WeatherMainData();
    mainData.setTemp(25.5); // Устанавливаем значение температуры

    WeatherDataResponse response = new WeatherDataResponse();
    response.setName("TestCity");
    response.setMain(mainData);

    String expectedJson = "{\"name\":\"TestCity\",\"main\":{\"temp\":25.5}}";

    String actualJson = objectMapper.writeValueAsString(response);

    assertThatJson(actualJson).isEqualTo(expectedJson);
  }

  @Test
  public void testDeserialization() throws Exception {
    String json = "{\"name\":\"TestCity\",\"main\":{\"temp\":25.5}}";

    WeatherDataResponse expectedResponse = new WeatherDataResponse();
    expectedResponse.setName("TestCity");

    WeatherMainData expectedMainData = new WeatherMainData();
    expectedMainData.setTemp(25.5); // Устанавливаем ожидаемое значение температуры

    expectedResponse.setMain(expectedMainData);

    WeatherDataResponse actualResponse = objectMapper.readValue(json, WeatherDataResponse.class);

    assertThatJson(actualResponse).isEqualTo(expectedResponse);
  }
}