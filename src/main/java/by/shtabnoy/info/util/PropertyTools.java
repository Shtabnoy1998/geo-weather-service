package by.shtabnoy.info.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyTools {

  private static final Logger logger = LogManager.getLogger(PropertyTools.class);

  private static final Map<String, Map<String, String>> propertiesMaps = new ConcurrentHashMap<>();

  private PropertyTools() {}

  public static String getProperty(String source, String property) {
    Map<String, String> temp = propertiesMaps.get(source);
    String result = null;
    try {
      if (temp == null) {
        loadProps(source);
        temp = propertiesMaps.get(source);
      }
      result = temp.get(property);
    } catch (Exception e) {
      logger.error("Error during get property: {}", property, e);
    }
    return result;
  }

  private static void loadProps(String filename) {
    try {
      ResourceBundle rb = ResourceBundle.getBundle(filename);
      Map<String, String> rbMap = new HashMap<>();
      for (Enumeration<String> en = rb.getKeys(); en.hasMoreElements(); ) {
        String propName = en.nextElement();
        rbMap.put(propName, rb.getString(propName));
      }
      propertiesMaps.put(filename, rbMap);
    } catch (Exception e) {
      logger.error("Error during load props from: {}", filename, e);
    }
  }
}
