package by.shtabnoy.info.service;

import by.shtabnoy.info.model.geo.TimeZoneInfo;
import feign.Param;

public interface GeoService {

  TimeZoneInfo findTimeZoneInformationByZipCode(@Param("zipCode") String zipCode);
}
