public class Chemin {

  private long pointOrigine;
  private long pointArrive;
  private double distance;
  private String nom;

  public Chemin(long pointOrigine, long pointArrive, double distance, String nom) {
    this.pointOrigine = pointOrigine;
    this.pointArrive = pointArrive;
    this.distance = distance;
    this.nom = nom;
  }

  public long getPointOrigine() {
    return pointOrigine;
  }

  public long getPointArrive() {
    return pointArrive;
  }

  public double getDistance() {
    return distance;
  }

  public String getNom() {
    return nom;
  }
}
