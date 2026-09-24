# Graph Data Structure and Traversals

This project features a custom Java implementation of an undirected Graph data structure using an Adjacency List representation. It is built entirely from scratch, utilizing custom doubly linked lists, stacks, and queues to manage the graph's vertices, edges, and traversal algorithms.

## Features

* **Core Graph Operations (`DSAGraph` & `DSAGraphVertex`)**:
  * **Dynamic Vertices & Edges:** Add or remove vertices and undirected edges dynamically.
  * **Sorting:** Vertices and adjacency lists are automatically sorted alphabetically upon display to ensure consistent and readable outputs.
  * **Adjacency Displays:** 
    * `displayAsList()`: Prints the graph in a standard adjacency list format.
    * `displayAsMatrix()`: Generates and prints an adjacency matrix (0s and 1s) representing the connections between sorted vertices.

* **Graph Traversals**:
  * **Breadth-First Search (BFS):** Explores the graph level by level starting from the first vertex, utilizing a custom Linked-List-backed `DSAQueue`.
  * **Depth-First Search (DFS):** Explores graph branches as deeply as possible before backtracking, utilizing a custom Linked-List-backed `DSAStack`.

* **Interactive CLI (`main.java`)**:
  * A user-friendly menu system for building and testing the graph in real-time.
  * Includes robust error handling to catch formatting issues, non-existent vertices, and duplicate entries gracefully.

## File Overview

* `DSAGraph.java`: The main graph class managing the collection of vertices and handling high-level operations (insert, delete, display, search).
* `DSAGraphVertex.java`: Represents an individual node in the graph, storing its label, visited state, and a list of adjacent edges.
* `DSALinkedList.java` *(provided as DSALinkedList_2.java)*: A custom doubly linked list implementation (with an Iterator) used as the backbone for storing vertices and adjacency lists.
* `DSAQueue.java` & `DSAStack.java`: Custom data structures wrapping the linked list, used specifically for the BFS and DFS algorithms.
* `main.java` *(provided as main_3.java)*: The interactive test harness and menu CLI.
* `.gitignore`: Ignores compiled `.class` files.

## How to Run

1. Ensure you have the Java Development Kit (JDK) installed.
2. Open a terminal and navigate to your project directory.
3. **Compile all Java files:**
   ```bash
   javac *.java
   ```
4. **Run the interactive menu:**
   ```bash
   java main
   ```
5. Follow the on-screen menu (options 1-8) to construct your graph and test the traversals, or enter `0` to exit. Note: When adding edges, use the format `<vertex1,vertex2>` (e.g., `A,B`).
