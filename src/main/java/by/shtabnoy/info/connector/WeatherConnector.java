package by.shtabnoy.info.connector;

import static by.shtabnoy.info.util.WebUtil.getObjectMapper;

import by.shtabnoy.info.model.weather.WeatherInfo;
import by.shtabnoy.info.util.PropertyTools;
import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.Request;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Headers(value = {
    "Content-Type: application/json",
    "Accept: application/json"
})
public interface WeatherConnector {

  @RequestLine("GET /v1/current.json?key={key}&q={cityCode}}")
  WeatherInfo findWeatherInfoByCityCode(@Param("key") String key, @Param("cityCode")String cityCode);

  String WEATHER_SERVICE_URL = PropertyTools.getProperty("application", "weather.service.api.url");
  WeatherConnector INSTANCE =
      Feign.builder()
          .options(new Request.Options(700000, 600000))
          .encoder(new JacksonEncoder(getObjectMapper()))
          .decoder(new JacksonDecoder(getObjectMapper()))
          .logLevel(Logger.Level.FULL)
          .target(WeatherConnector.class, WEATHER_SERVICE_URL);


  static WeatherConnector getInstance() {
    return INSTANCE;
  }
}
