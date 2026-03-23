public class Arc {

  private Long pointOrigine;
  private Long pointArrive;
  private double distance;
  private String nom;

  public Arc(Long pointOrigine, Long pointArrive, double distance, String nom) {
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
