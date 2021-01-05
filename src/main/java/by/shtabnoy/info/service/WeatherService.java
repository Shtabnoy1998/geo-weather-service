package by.shtabnoy.info.service;

import by.shtabnoy.info.model.weather.WeatherInfo;
import feign.Param;

public interface WeatherService {

  WeatherInfo findWeatherInfoByCityCode(@Param("cityCode") String cityCode);
}
