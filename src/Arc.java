public class Arc {

  private String pointOrigine;
  private String pointArrive;
  private double distance;
  private String nom;

  public Arc(String pointOrigine, String pointArrive, double distance, String nom) {
    this.pointOrigine = pointOrigine;
    this.pointArrive = pointArrive;
    this.distance = distance;
    this.nom = nom;
  }

  public String getPointOrigine() {
    return pointOrigine;
  }

  public String getPointArrive() {
    return pointArrive;
  }

  public double getDistance() {
    return distance;
  }

  public String getNom() {
    return nom;
  }
}
