import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

public class Graph {

        //ATTRIBUT ?
        Map<Localisation, Long> correspondanceLocalisationIndice;
    Map<Long,Localisation> correspondanceIndiceLocalisation;
    private Chemin[][] matrice;
    //TODO


    public Graph(String localisations, String roads) {
        try (Scanner scanner = new Scanner(new File(localisations))) {
            scanner.useDelimiter("[,\\n]");
            while (scanner.hasNext()) {
                String line = scanner.next();
                System.out.print(line + " ");
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Localisation[] determinerZoneInondee(long[] idsOrigin,double epsilon) {
        //TODO
		return null ;
    }

    public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(long idOrigin, long idDestination, Localisation[] floodedZone) {
		//TODO
        return null ;
    }

    public Map<Localisation,Double> determinerChronologieDeLaCrue(long[] idsOrigin, double vWaterInit, double k) {
        //TODO
        return null ;
    }

    public Deque<Localisation> trouverCheminDEvacuationLePlusCourt(long idOrigin, long idEvacuation, double vVehicule, Map<Localisation,Double> tFlood) {
        //TODO
		return null ;
    }


}
