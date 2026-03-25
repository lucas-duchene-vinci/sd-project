import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graph {

    Map<Long, Localisation> idToLocalisation;
    private Map<Long, List<Chemin>> adj;

    public Graph(String localisations, String roads) {
        idToLocalisation = new HashMap<>();
        adj = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(localisations))) {


            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] v = line.split(",");

                long id = Long.parseLong(v[0]);
                String nom = v[1];
                double lat = Double.parseDouble(v[2]);
                double lon = Double.parseDouble(v[3]);
                double altitude = Double.parseDouble(v[4]);

                Localisation loc = new Localisation(id, lat, lon, nom, altitude);

                idToLocalisation.put(id, loc);
                adj.put(id, new ArrayList<>());

                System.out.println("Constructed object with: " + Arrays.toString(v));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(new File(roads))) {

            // Ignorer l’en-tête
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] v = line.split(",");

                long sourceId = Long.parseLong(v[0]);
                long targetId = Long.parseLong(v[1]);
                double dist = Double.parseDouble(v[2]);
                String nom = v[3];

                Localisation source = idToLocalisation.get(sourceId);
                Localisation target = idToLocalisation.get(targetId);

                Chemin che = new Chemin(source, target, dist, nom);
                adj.get(sourceId).add(che);




                System.out.println("Constructed object with: " + Arrays.toString(v));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public Localisation[] determinerZoneInondee(long[] idsOrigin,double epsilon) {
        ArrayDeque<Localisation> explorer = new ArrayDeque<>();
        LinkedHashSet<Localisation> inonde = new LinkedHashSet<>();

        for (long l : idsOrigin) {
            explorer.add(idToLocalisation.get(l));
        }
        while(!explorer.isEmpty()){
            Localisation l = explorer.pollFirst();
            inonde.add(l);

            for (Chemin chemin : adj.get(l.getId())) {
                if(!inonde.contains(chemin.getPointArrive())){
                    if(chemin.getPointArrive().getAltitude()<=chemin.getPointOrigine().getAltitude()+epsilon){
                        explorer.addLast(chemin.getPointArrive());
                    }
                }


            }

        }
        Localisation[] zoneInonde = new Localisation[inonde.size()];

		return  inonde.toArray(zoneInonde);
    }

    public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(long idOrigin, long idDestination, Localisation[] floodedZone) {
		//TODO
        Localisation origin = idToLocalisation.get(idOrigin);
        Localisation destination = idToLocalisation.get(idDestination);
        ArrayDeque<Localisation> explorer = new ArrayDeque<>();
        HashSet<Localisation> visited = new HashSet<>();
        HashSet<Localisation> inonde = new HashSet<>(Arrays.asList(floodedZone));
        Map<Long,Long> parent = new HashMap<>();
        ArrayDeque<Localisation> fin = new ArrayDeque<>();
        boolean trouver = false;
        explorer.addLast(origin);
        visited.add(origin);
        while(!trouver && !explorer.isEmpty()){

            Localisation loc = explorer.pollFirst();
            visited.add(loc);

            List<Chemin> c = adj.get(loc.getId());
            for (Chemin chemin : c) {
                Localisation voisin = chemin.getPointArrive();
                if(!inonde.contains(voisin) && !visited.contains(voisin) ){
                    parent.put(voisin.getId(),chemin.getPointOrigine().getId());
                    if(destination.equals(voisin)){
                        trouver = true;
                    }else{
                        explorer.addLast(voisin);
                        visited.add(voisin);
                    }

                }
            }
        }
        long id = idDestination;
        fin.addFirst(idToLocalisation.get(id));
        while (id != idOrigin){
           Localisation localisation = idToLocalisation.get(parent.get(id));
           fin.addFirst(localisation);
           id = localisation.getId();
        }



        return fin;
    }

    public Map<Localisation, Double> determinerChronologieDeLaCrue(long[] idsOrigin, double vWaterInit, double k) {

        Map<Localisation, Double> tInonde = new HashMap<>();
        Map<Localisation, Double> vitesseNoeud = new HashMap<>();
        Set<Localisation> visiter = new HashSet<>();

        PriorityQueue<Localisation> fileAttente = new PriorityQueue<>(
                Comparator.comparingDouble(loc -> tInonde.getOrDefault(loc, Double.MAX_VALUE))
        );

        for (long id : idsOrigin) {
            Localisation loc = idToLocalisation.get(id);
            if (loc != null) {
                tInonde.put(loc, 0.0);
                vitesseNoeud.put(loc, vWaterInit);
                fileAttente.add(loc);
            }
        }

        while (!fileAttente.isEmpty()) {
            Localisation locActuel = fileAttente.poll();
            if (visiter.contains(locActuel)) continue;
            visiter.add(locActuel);

            double tempsActuel = tInonde.get(locActuel);
            double velocite = vitesseNoeud.get(locActuel);

            for (Chemin chemin : adj.get(locActuel.getId())) {
                Localisation locAdj = chemin.getPointArrive();
                if (visiter.contains(locAdj)) continue;

                double distance = chemin.getDistance();
                double slope = (locActuel.getAltitude() - locAdj.getAltitude()) / distance; // pente
                double nVolocite = velocite + (k * slope);


                if (nVolocite <= 0) continue;

                double tempsVoyage = distance / nVolocite;
                double nTemps = tempsActuel + tempsVoyage;

                if (!tInonde.containsKey(locAdj) || nTemps < tInonde.get(locAdj)) {
                    tInonde.put(locAdj, nTemps);
                    vitesseNoeud.put(locAdj, nVolocite);
                    fileAttente.add(locAdj);
                }
            }
        }

        LinkedHashMap<Localisation, Double> tInondeTrier = new LinkedHashMap<>();
        tInonde.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> tInondeTrier.put(e.getKey(), e.getValue()));
        return tInondeTrier;
    }

    public Deque<Localisation> trouverCheminDEvacuationLePlusCourt(long idOrigin, long idEvacuation, double vVehicule, Map<Localisation,Double> tFlood) {

        Map<Localisation, Double> tTrajet = new HashMap<>();
        Map<Localisation, Localisation> parent = new HashMap<>();
        Set<Localisation> visiter = new HashSet<>();
        PriorityQueue<Localisation> fileAttente = new PriorityQueue<>(
                Comparator.comparingDouble(loc -> tTrajet.getOrDefault(loc, Double.MAX_VALUE))
        );

        Localisation origin = idToLocalisation.get(idOrigin);
        if(origin != null){
            tTrajet.put(origin, 0.0);
            parent.put(origin, null);
            fileAttente.add(origin);
        }

        while(!fileAttente.isEmpty()) {
            Localisation locActuel = fileAttente.poll();
            if(visiter.contains(locActuel)) continue;
            visiter.add(locActuel);

            if(locActuel.getId() == idEvacuation) {
                Deque<Localisation> cheminEvacuation = new ArrayDeque<>();
                Localisation etape = locActuel;
                while (etape != null) {
                    cheminEvacuation.addFirst(etape);
                    etape = parent.get(etape);
                }
                return cheminEvacuation;
            }

            for (Chemin chemin : adj.get(locActuel.getId())) {
                Localisation locAdj = chemin.getPointArrive();
                if(visiter.contains(locAdj)) continue;
                double distance = chemin.getDistance();
                double tArrivee = tTrajet.get(locActuel) + (distance / vVehicule);

                double tInonde = tFlood.getOrDefault(locAdj, Double.MAX_VALUE);

                if(tArrivee > tInonde) continue;

                if(!tTrajet.containsKey(locAdj) || tArrivee < tTrajet.get(locAdj)) {
                    tTrajet.put(locAdj, tArrivee);
                    parent.put(locAdj, locActuel);
                    fileAttente.add(locAdj);
                }

            }
        }
		    return null ;
    }


}
