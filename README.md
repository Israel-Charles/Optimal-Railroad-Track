# Optimal Railroad-Track Network

This Java project focuses on creating a cost-efficient railroad network by implementing Kruskal's algorithm to solve the Minimum Spanning Tree (MST) problem. The program reads track data from a specified file, where each track connects two cities and has an associated cost. The goal is to determine the subset of tracks that connects all cities with the minimum total cost without forming cycles. The program returns the optimal set of tracks to minimize construction costs

### Demo > https://codepad.site/pad/454atn00

## Features

- **Efficient MST Calculation**: Utilizes Kruskal's algorithm to find the minimum spanning tree, ensuring all cities are connected with the lowest total cost.
- **Union-Find Data Structure**: Implements a union-find data structure with path compression and union by rank for efficient cycle detection and merging of connected components.
- **Customizable Input**: Allows users to specify different track data files to test various scenarios.
- **Formatted Output**: Provides a clear, formatted output showing the selected tracks and the total cost of the railroad network.

## How It Works

1. **Track Data Input**: The program reads a file containing tracks, each defined by two cities and a cost. 
2. **Sorting**: Tracks are sorted by cost in ascending order to facilitate Kruskal's algorithm.
3. **Union-Find Initialization**: A disjoint-set structure is initialized for each city to manage connected components.
4. **MST Construction**: The algorithm iterates over the sorted tracks, adding them to the MST if they do not form a cycle, updating the total cost.
5. **Result Output**: The program outputs a formatted string listing the included tracks and the total cost of the railroad network.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later

### Running the Program

1. **Compile the Project:** Use the following command to compile the Java files.

   ```bash
   javac OptimalTrackNetwork.java
   ```

2. **Run the Project:** Execute the compiled program with the test file as input.

   ```bash
   java OptimalTrackNetwork
   ```

3. **Input Files:** Ensure your track data files (e.g., railroad1.txt) are in the same directory as the compiled classes.

### Example Track Data File Format

The input file should contain lines formatted as follows, where each line represents a track connecting two cities with a specified construction cost: `City1 City2 Cost`

For example:

```text
A B 5
A C 10
B C 4
```

In this example:
- `A` and `B` are connected by a track that costs 5.
- `A` and `C` are connected by a track that costs 10.
- `B` and `C` are connected by a track that costs 4.

### Example Output

After executing the program, the output will list the tracks included in the minimum spanning tree (MST) along with the total cost of constructing the railroad network:

```
B---C   $4
A---B   $5
The cost of the railroad is $9.
```

This output indicates that the tracks between `B` and `C` and between `A` and `B` form the most cost-effective network, with a total cost of 9.


