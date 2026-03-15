public class Localisation {

  private long id;
  private String nom;
  private double latitude;
  private double longitude;
  private double altitude;


  public Localisation(long id, String nom, double latitude, double longitude, double altitude) {
    this.id = id;
    this.nom = nom;
    this.latitude = latitude;
    this.longitude = longitude;
    this.altitude = altitude;
  }

  public long getId() {
    return id;
  }

    public String getNom() {
        return nom;
    }

    public double getLatitude() {
        return latitude;
    }

  public double getLongitude() {
    return longitude;
  }


  public double getAltitude() {
    return altitude;
  }
  
}
