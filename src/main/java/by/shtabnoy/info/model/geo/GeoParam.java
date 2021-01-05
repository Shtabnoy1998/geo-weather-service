package by.shtabnoy.info.model.geo;

public class GeoParam {

  private String zipCode;

  public GeoParam() {
  }

  public GeoParam(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
