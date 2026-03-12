import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;

public class Graph {

	  //ATTRIBUT ?
	  //TODO

    public Graph(String localisations, String roads)  {
        //TODO
        String line = "";
        String delimiter = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(localisations))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(delimiter);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
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
