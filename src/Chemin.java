public class Chemin {

  private Localisation pointOrigine;
  private Localisation pointArrive;
  private double distance;
  private String nom;

  public Chemin(Localisation pointOrigine, Localisation pointArrive, double distance, String nom) {
    this.pointOrigine = pointOrigine;
    this.pointArrive = pointArrive;
    this.distance = distance;
    this.nom = nom;
  }

  public Localisation getPointOrigine() {
    return pointOrigine;
  }

  public Localisation getPointArrive() {
    return pointArrive;
  }

  public double getDistance() {
    return distance;
  }

  public String getNom() {
    return nom;
  }
}
