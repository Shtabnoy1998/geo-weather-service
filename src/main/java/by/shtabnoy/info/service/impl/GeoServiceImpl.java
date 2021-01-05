package by.shtabnoy.info.service.impl;

import by.shtabnoy.info.connector.GeoConnector;
import by.shtabnoy.info.model.geo.TimeZoneInfo;
import by.shtabnoy.info.service.GeoService;
import by.shtabnoy.info.util.PropertyTools;

public class GeoServiceImpl implements GeoService {

  private static final String PROPERTY_SOURCE = "application";
  private static final String GEO_API_KEY_PROPERTY_NAME = "geo.service.api.key";

  @Override
  public TimeZoneInfo findTimeZoneInformationByZipCode(String zipCode) {
    String geoApiKey = PropertyTools.getProperty(PROPERTY_SOURCE, GEO_API_KEY_PROPERTY_NAME);
    return GeoConnector.getInstance()
        .findTimeZoneInformationByZipCode(geoApiKey, zipCode);
  }
}
