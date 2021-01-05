package by.shtabnoy.info.controller;

import by.shtabnoy.info.model.geo.GeoParam;
import by.shtabnoy.info.model.geo.TimeZoneInfo;
import by.shtabnoy.info.service.GeoService;
import by.shtabnoy.info.service.impl.GeoServiceImpl;
import by.shtabnoy.info.util.WebUtil;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeoServlet extends HttpServlet {

  private static final Logger logger = LogManager.getLogger(GeoServlet.class);
  private final GeoService geoService = new GeoServiceImpl();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      GeoParam geoParam = WebUtil.definePostRequestParam(req, GeoParam.class);
      if (geoParam != null) {
        TimeZoneInfo timeZoneInfo = geoService.findTimeZoneInformationByZipCode(geoParam.getZipCode());
        WebUtil.sendResponse(resp, WebUtil.getObjectMapper().writeValueAsString(timeZoneInfo));
      }
    } catch (Exception ex) {
      logger.error("Error during get method", ex);
      WebUtil.errorResponse(resp);
    }
  }
}
