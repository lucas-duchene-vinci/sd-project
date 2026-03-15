import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

public class Graph {

    Map<Localisation, Long> correspondanceLocalisationIndice;
    Map<Long,Localisation> correspondanceIndiceLocalisation;
    private Chemin[][] matrice;

    public Graph(String localisations, String roads) {
        try (Scanner scanner = new Scanner(new File(localisations))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip empty lines if they exist
                if (line.trim().isEmpty()) continue;

                // Split the line by commas
                String[] values = line.split(",");

                System.out.println("Constructed object with: " + Arrays.toString(values));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
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
