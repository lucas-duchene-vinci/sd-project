public class Localisation {

  private long id;
  private double longitude;
  private double latitude;
  private String nom;
  private double altitude;


  public Localisation(long id, double longitude, double latitude, String nom, double altitude) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
    this.nom = nom;
    this.altitude = altitude;
  }

  public long getId() {
    return id;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public String getNom() {
    return nom;
  }

  public double getAltitude() {
    return altitude;
  }


}
