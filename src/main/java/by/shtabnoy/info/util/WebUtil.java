package by.shtabnoy.info.util;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebUtil {

  private static final Logger logger = LogManager.getLogger(WebUtil.class);

  private WebUtil() {}

  public static ObjectMapper getObjectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL)
        .setTimeZone(TimeZone.getDefault());
  }

  public static void sendResponse(HttpServletResponse response, String json) throws IOException {
    PrintWriter out;
    try {
      out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.print(json);
      out.flush();
    } catch (IOException e) {
      logger.error("Error during send response", e);
      errorResponse(response);
    }
  }

  public static void errorResponse(HttpServletResponse response) throws IOException {
    response.sendError(SC_INTERNAL_SERVER_ERROR);
  }

  public static  <T> T definePostRequestParam(HttpServletRequest req, Class<T> tClass) {
    StringBuilder json = new StringBuilder();
    String line;
    try {
      BufferedReader reader = req.getReader();
      while ((line = reader.readLine()) != null) {
        json.append(line);
      }
      return WebUtil.getObjectMapper().readValue(json.toString(), tClass);
    } catch (Exception ex) {
      logger.error("Error during define post request param", ex);
    }
    return null;
  }
}
