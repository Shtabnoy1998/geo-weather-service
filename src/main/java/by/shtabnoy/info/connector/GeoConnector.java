package by.shtabnoy.info.connector;

import static by.shtabnoy.info.util.WebUtil.getObjectMapper;

import by.shtabnoy.info.model.geo.TimeZoneInfo;
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
public interface GeoConnector {

  @RequestLine("GET /rest/{key}/info.json/{zipCode}/degrees")
  TimeZoneInfo findTimeZoneInformationByZipCode(@Param("key") String key, @Param("zipCode") String zipCode);

  String GEO_SERVICE_URL = PropertyTools.getProperty("application", "geo.service.api.url");
  GeoConnector INSTANCE =
      Feign.builder()
          .options(new Request.Options(700000, 600000))
          .encoder(new JacksonEncoder(getObjectMapper()))
          .decoder(new JacksonDecoder(getObjectMapper()))
          .logLevel(Logger.Level.FULL)
          .target(GeoConnector.class, GEO_SERVICE_URL);


  static GeoConnector getInstance() {
    return INSTANCE;
  }
}
