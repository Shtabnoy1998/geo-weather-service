package by.shtabnoy.info.service.impl;

import by.shtabnoy.info.connector.WeatherConnector;
import by.shtabnoy.info.model.weather.WeatherInfo;
import by.shtabnoy.info.service.WeatherService;
import by.shtabnoy.info.util.PropertyTools;

public class WeatherServiceImpl implements WeatherService {

  private static final String PROPERTY_SOURCE = "application";
  private static final String WEATHER_API_KEY_PROPERTY_NAME = "weather.service.api.key";

  @Override
  public WeatherInfo findWeatherInfoByCityCode(String cityCode) {
    String weatherApiKey = PropertyTools.getProperty(PROPERTY_SOURCE, WEATHER_API_KEY_PROPERTY_NAME);
    return WeatherConnector.getInstance()
        .findWeatherInfoByCityCode(weatherApiKey, cityCode);
  }
}
