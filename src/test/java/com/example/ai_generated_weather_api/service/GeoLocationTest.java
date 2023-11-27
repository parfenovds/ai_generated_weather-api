package com.example.ai_generated_weather_api.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeoLocationTest {

  @Test
  public void createGeoLocationObject() {
    GeoLocation geoLocation = new GeoLocation();
    assertNotNull(geoLocation);
  }

  @Test
  public void getAndSetLatitude() {
    double latitude = 40.7128;
    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setLat(latitude);
    assertEquals(latitude, geoLocation.getLat(), 0.01);
  }

  @Test
  public void getAndSetLongitude() {
    double longitude = -74.006;
    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setLon(longitude);
    assertEquals(longitude, geoLocation.getLon(), 0.01);
  }
}