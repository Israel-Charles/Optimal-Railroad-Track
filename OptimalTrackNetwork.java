// Imported libraries used for the program
import java.io.*;
import java.util.*;

public class OptimalTrackNetwork {
    private int numTracks;                  // Number of tracks in the test file - given from driver function
    private String fileName;                // Name of text file - given from driver function
    private List<Track> trackLists;         // A list of the edges/tracks given from the text file

    // Class constructor that gets data for the class from caller method
    public OptimalTrackNetwork(int numTracks, String fileName) {
        this.numTracks = numTracks;
        this.fileName = fileName;
        this.trackLists = new ArrayList<>();
    }

    // Main function of class that finds the cost-efficient railroad system
    public String buildRailroad() throws IOException {
        readTrackListFile();                // Read all tracks and store them in array List
        
        // Sort the array list based on the cost of each track - lowest to highest
        Collections.sort(trackLists, Comparator.comparingInt(track -> track.cost));

        // Disjoint Set data structure with hash map instead of array for faster lookup
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();

        // Creating the vertices/sets of the map
        for (Track track : trackLists) {
            parent.putIfAbsent(track.city1, track.city1);
            parent.putIfAbsent(track.city2, track.city2);
            rank.putIfAbsent(track.city1, 0);
            rank.putIfAbsent(track.city2, 0);
        }

        // Creating another list that stores the most cost-efficient tracks while making sure each city is included
        List<Track> minimumTrackList = new ArrayList<>();
        int totalCost = 0;
        for (Track track : trackLists) {
            if (find(parent, track.city1) != find(parent, track.city2)) {
                union(parent, rank, track.city1, track.city2);
                minimumTrackList.add(track);
                totalCost += track.cost;
            }
        }

        // Creating and formatting the return string
        StringBuilder stringToReturn = new StringBuilder();
        
        for (Track track : minimumTrackList) {
            stringToReturn.append(formatOutput(track.city1, track.city2, track.cost));
        }
        stringToReturn.append("\nThe cost of the railroad is $").append(totalCost).append(".");
        return stringToReturn.toString();
    }

    // Function that read each line of file that has all of the tracks and store them in a list
    private void readTrackListFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            // Saving each entry of a line in its respective variable
            String[] entries = line.split(" ");
            String city1 = entries[0];
            String city2 = entries[1];
            int cost = Integer.parseInt(entries[2]);
            
            // With each set of entries, add to the trackLists Array list
            trackLists.add(new Track(city1, city2, cost));
        }
        reader.close();
    }

    // Function that recursively finds its root while updating the parent of each visited node to the root
    private String find(Map<String, String> parent, String city) {
        if (!parent.get(city).equals(city)) {
            parent.put(city, find(parent, parent.get(city)));
        }
        return parent.get(city);
    }

    // Function that merges two sets based on tree rank
    private void union(Map<String, String> parent, Map<String, Integer> rank, String city1, String city2) {
        String rootCity1 = find(parent, city1);
        String rootCity2 = find(parent, city2);
        
        if (!rootCity1.equals(rootCity2)) {
            if (rank.get(rootCity1) < rank.get(rootCity2)) {
                parent.put(rootCity1, rootCity2);
            } else if (rank.get(rootCity1) > rank.get(rootCity2)) {
                parent.put(rootCity2, rootCity1);
            } else {
                parent.put(rootCity2, rootCity1);
                rank.put(rootCity1, rank.get(rootCity1) + 1);
            }
        }
    }

    // Function that format the string output lexicographically
    private String formatOutput(String city1, String city2, int cost) {
        if (city1.compareTo(city2) > 0) {
            return city2 + "---" + city1 + "\t$" + cost + "\n";
        } else {
            return city1 + "---" + city2 + "\t$" + cost + "\n";
        }
    }

    // Helper class that has the apropriate variable for each track
    private static class Track {
        String city1;                       // Source/origin of track
        String city2;                       // Destination of track
        int cost;                           // Weight of track

        Track(String city1, String city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }
public static void main(String[] args) throws Exception
    {
		System.out.println("*************** TEST CASE 1 ***********************\n");
        OptimalTrackNetwork test = new OptimalTrackNetwork(4, "railroad1.txt");
		
        System.out.println(test.buildRailroad());
        
		System.out.println("\n*************** TEST CASE 2 ***********************\n");
        OptimalTrackNetwork test2 = new OptimalTrackNetwork(10, "railroad2.txt");
        
        System.out.println(test2.buildRailroad());
		
		System.out.println("\n*************** TEST CASE 3 ***********************\n");
		OptimalTrackNetwork test3 = new OptimalTrackNetwork(6, "railroad3.txt");
        
        System.out.println(test3.buildRailroad());
    }   

}
