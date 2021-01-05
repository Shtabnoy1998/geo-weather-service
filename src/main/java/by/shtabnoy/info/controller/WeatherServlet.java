package by.shtabnoy.info.controller;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import by.shtabnoy.info.model.weather.WeatherInfo;
import by.shtabnoy.info.service.WeatherService;
import by.shtabnoy.info.service.impl.WeatherServiceImpl;
import by.shtabnoy.info.util.WebUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeatherServlet extends HttpServlet {

  private static final String CITY_CODE_PARAM_NAME = "cityCode";
  private static final Logger logger = LogManager.getLogger(WeatherServlet.class);
  private final WeatherService weatherService = new WeatherServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      String cityCode = req.getParameter(CITY_CODE_PARAM_NAME);
      WeatherInfo weatherInfo = weatherService.findWeatherInfoByCityCode(cityCode);
      WebUtil.sendResponse(resp, WebUtil.getObjectMapper().writeValueAsString(weatherInfo));
    } catch (Exception ex) {
      logger.error("Error during get method", ex);
      WebUtil.errorResponse(resp);
    }
  }
}
