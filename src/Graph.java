import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graph {
    private Map<Long, Localisation> localisationIdMap;
    private Map<Long, List<Chemin>> cheminsLocalisation;

    public Graph(String localisations, String roads) {
        this.localisationIdMap = new HashMap<>();
        this.cheminsLocalisation = new HashMap<>();

        // Fetch localisations
        try (Scanner scanner = new Scanner(new File(localisations))) {
            // skip the header row
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip empty lines if they exist
                if (line.trim().isEmpty()) continue;

                // Split the line by commas
                String[] values = line.split(",");

                if (values.length >= 5) {
                    long id = Long.parseLong(values[0].trim());
                    String nom = values[1];
                    double latitude = Double.parseDouble(values[2].trim());
                    double longitude = Double.parseDouble(values[3].trim());
                    double altitude = Double.parseDouble(values[4].trim());

                    Localisation newLocalistation = new Localisation(id, nom, latitude, longitude, altitude);

                    localisationIdMap.put(newLocalistation.getId(), newLocalistation);
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        //Fetch chemins
        try (Scanner scanner = new Scanner(new File(roads))) {
            // skip the header row
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip empty lines if they exist
                if (line.trim().isEmpty()) continue;

                // Split the line by commas
                String[] values = line.split(",");

                if (values.length >= 4) {
                    long source = Long.parseLong(values[0].trim());
                    long target = Long.parseLong(values[1].trim());
                    double dist = Double.parseDouble(values[2].trim());
                    String name = values[3].trim();

                    Chemin newChemin = new Chemin(source, target, dist, name);

                    cheminsLocalisation.computeIfAbsent(newChemin.getPointOrigine(), k -> new ArrayList<Chemin>());
                    cheminsLocalisation.get(newChemin.getPointOrigine()).add(newChemin);
                }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Graph Overview ---\n");
        sb.append("Total Nodes: ").append(localisationIdMap.size()).append("\n");
        sb.append("----------------------\n");

        for (Long id : localisationIdMap.keySet()) {
            Localisation loc = localisationIdMap.get(id);
            sb.append(String.format("Node [%d] %s (Lat: %.2f, Lon: %.2f)\n",
                    id, loc.getNom(), loc.getLatitude(), loc.getLongitude()));

            List<Chemin> edges = cheminsLocalisation.get(id);
            if (edges != null && !edges.isEmpty()) {
                for (Chemin edge : edges) {
                    sb.append(String.format("  --> Destination: %d | Distance: %.2f km | Street: %s\n",
                            edge.getPointArrive(), edge.getDistance(), edge.getNom()));
                }
            } else {
                sb.append("  (No outgoing roads)\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
