import java.util.*;

public class DSAGraph{
    private DSALinkedList m_vertices;

    public DSAGraph() {
        m_vertices = new DSALinkedList();
    }

    // accessor
    public boolean hasVertex(String label){
        boolean result = false;

        if(getVertex(label) != null)
            result = true;
        return result;
    }

    public int getVertexCount(String label) {
        int count = 0;

        for(Object vert : m_vertices) // iterating each item in vertices linkedlist
            count++;                  // up the count
        return count;
    }

    public int getEdgeCount(String label) {
        int count = 0;
        DSAGraphVertex temp = null;

        for(Object vert : m_vertices) {
            temp = (DSAGraphVertex) vert; // type cast as graphvertex because we got to access its variable via getAdjacent();
            for (Object adjacent : temp.getAdjacent())
                count++;
        }
        return count;
    }

    public DSAGraphVertex getVertex(String label) {
        DSAGraphVertex targetVertex = null;
        DSAGraphVertex temp = null;

        for (Object elem : m_vertices) {
            temp = (DSAGraphVertex) elem; // casting elem inside m_vertices to be DSAGraphVertex to allow to set value to temp;
            if(temp.getLabel().equals(label)) { // if temp has label looked for
                targetVertex = temp;
            } 
        }
        return targetVertex;
    }

    public DSALinkedList getAdjacent(String label) {
        DSAGraphVertex vertex = getVertex(label);

        if(vertex == null)
            throw new NoSuchElementException("Vertex " + label + " does not exist");
        return vertex.getAdjacent();
    }
    
    public boolean isAdjacent(String label1, String label2) {
        boolean result = false;
        DSAGraphVertex vertex = null;

        for(Object adjacent : getAdjacent(label1)) {
            vertex = (DSAGraphVertex) adjacent;
            if(vertex.getLabel().equals(label2))
                result = true;
        }
        return result;
    }

    // mutator
    public void addVertex(Object value, String label) {
        DSAGraphVertex newVertex = new DSAGraphVertex(value, label); // create new vertex
        m_vertices.insertLast(newVertex); // put it in the linkedlist
    }

    public void deleteVertex(String label) {
        DSAGraphVertex target = getVertex(label);

        checkVertex(target, label);
        m_vertices.remove(target); // remove from vertices

        for(Object vert : m_vertices) {
            DSAGraphVertex temp = (DSAGraphVertex) vert;
            temp.getAdjacent().remove(target); // remove from adjacency list
        }       
    }

    public void addEdge(String label1, String label2) {
        DSAGraphVertex vertexOne = getVertex(label1);
        DSAGraphVertex vertexTwo = getVertex(label2);

        checkVertex(vertexOne, label1);
        checkVertex(vertexTwo, label2);

        vertexOne.addEdge(vertexTwo); // undirected graph = add each other
        vertexTwo.addEdge(vertexOne);       
    }

    public void deleteEdge(String label1, String label2) {
        DSAGraphVertex vertexOne = getVertex(label1);
        DSAGraphVertex vertexTwo = getVertex(label2);

        checkVertex(vertexOne, label1);
        checkVertex(vertexTwo, label2);

        vertexOne.removeEdge(vertexTwo);
        vertexTwo.removeEdge(vertexOne);
    }

    public void checkVertex(DSAGraphVertex vert, String label) {
        if(vert == null) 
            throw new NoSuchElementException("Vertex " + label + " does not exist");
    }

    public void displayAsList() {
        sort(); // sort first then display
        DSAGraphVertex vert = null;
        DSAGraphVertex adjVert = null;


        for(Object listVert : m_vertices) { // columns at top
            vert = (DSAGraphVertex) listVert;

            System.out.print(vert.getLabel() + ": ");

            for(Object listAdj : vert.getAdjacent()) {
                adjVert = (DSAGraphVertex) listAdj;
                System.out.print(adjVert.getLabel() + " ");
            }
            System.out.println("");
        }
    }

    public void displayAsMatrix() {
        sort();
        DSAGraphVertex vert = null;
        DSAGraphVertex adjVert = null;

        // for the column start
        System.out.print("  ");
        for(Object topRowVert : m_vertices) {
            vert = (DSAGraphVertex) topRowVert;
            System.out.print(vert.getLabel() + " ");
        }
        System.out.println("");
        for(Object rowItem : m_vertices) {
            DSAGraphVertex rowVert = (DSAGraphVertex) rowItem;
            
            // Print the row label
            System.out.print(rowVert.getLabel() + " "); 

            // Check adjacency for each column
            for(Object colItem : m_vertices) {
                DSAGraphVertex colVert = (DSAGraphVertex) colItem;
                
                // If the column vertex is adjacent to the row vertex, print 1
                if (isAdjacent(rowVert.getLabel(), colVert.getLabel())) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println(); // Move to the next row
        }

    }

    public void sort() {
        m_vertices = sortVertexList(m_vertices); // sort main vertex list
        DSAGraphVertex temp = null;

        for(Object vert : m_vertices) {
            temp = (DSAGraphVertex) vert;
            DSALinkedList sortedAdjList = sortVertexList(temp.getAdjacent());
            temp.setLinks(sortedAdjList);
        }
    }

    private DSALinkedList sortVertexList(DSALinkedList vertList) {
        int count = 0;
        int i = 0;

        for(Object vert : vertList)
            count++;

        DSAGraphVertex[] vertArr = new DSAGraphVertex[count];

        for(Object vert : vertList) {
            vertArr[i] = (DSAGraphVertex) vert;
            i++;
        }

        vertexInsertSort(vertArr, count);
        DSALinkedList sortedLinkedList = new DSALinkedList();

        for(int j = 0; j < count; j++)
            sortedLinkedList.insertLast(vertArr[j]);

        return sortedLinkedList;
    }

    private static void vertexInsertSort(DSAGraphVertex[] arr, int count) {
        for(int i = 1; i < count; i++) {
            int j = i;

            while(j > 0 && arr[j-1].getLabel().compareTo(arr[j].getLabel()) > 0) {
                DSAGraphVertex temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j = j-1;
            }
        }
    }

    public DSAQueue breadthFirstSearch() {
        DSAQueue t = new DSAQueue();
        DSAQueue q = new DSAQueue();

        if(m_vertices.isEmpty())
            return t; 

        for(Object vert : m_vertices) {
            DSAGraphVertex temp = (DSAGraphVertex) vert;
            temp.clearVisited();
        }
        DSAGraphVertex v = (DSAGraphVertex) m_vertices.peekFirst();
        v.setVisited();
        q.enqueue(v);
        while(!q.isEmpty()) {
            v = (DSAGraphVertex) q.dequeue();
            for(Object adj : v.getAdjacent()) {
                DSAGraphVertex w = (DSAGraphVertex) adj;
                if(!w.getVisited()) {
                    t.enqueue(v);
                    t.enqueue(w);
                    w.setVisited();
                    q.enqueue(w);
                }
            }
        }
        return t;
    }

    public static void dispBfs(DSAGraph vert) {
        DSAQueue bfs = vert.breadthFirstSearch();

        if(bfs.isEmpty()) {
            System.out.println("The graph is empty");
            return;
        }

        System.out.print("BFS tree: {");

        while(!bfs.isEmpty()) {
            DSAGraphVertex v = (DSAGraphVertex) bfs.dequeue();
            DSAGraphVertex w = (DSAGraphVertex) bfs.dequeue();

            System.out.print("(" + v.getLabel() + ", " + w.getLabel() + ")");

        }
        System.out.print("}");
        System.out.println("");
            
    }

    private DSAGraphVertex unvisitedAdjacent(DSAGraphVertex v) {
        for (Object adj : v.getAdjacent()) {
            DSAGraphVertex w = (DSAGraphVertex) adj;
            if (!w.getVisited()) {
                return w;
            }
        }
        return null;
    }

    public DSAQueue depthFirstSearch() {
        DSAQueue t = new DSAQueue();
        DSAStack s = new DSAStack();

        if(m_vertices.isEmpty())
            return t;

        for(Object vert : m_vertices) {
            DSAGraphVertex temp = (DSAGraphVertex) vert;
            temp.clearVisited();
        }
        DSAGraphVertex v =(DSAGraphVertex) m_vertices.peekFirst();
        v.setVisited();
        s.push(v);
        while(!s.isEmpty()) {
            DSAGraphVertex w = unvisitedAdjacent(v);
            while(w != null) {
                t.enqueue(v);
                t.enqueue(w);
                w.setVisited();
                s.push(w);
                v = w;
                w = unvisitedAdjacent(v);
            }
            v = (DSAGraphVertex) s.pop();
        }
        return t;
    }

    public static void dispDfs(DSAGraph vert) {
        DSAQueue dfs = vert.depthFirstSearch();

        if(dfs.isEmpty()) {
            System.out.println("The graph is empty");
            return;
        }

        System.out.print("DFS tree: {");

        while(!dfs.isEmpty()) {
            DSAGraphVertex v = (DSAGraphVertex) dfs.dequeue();
            DSAGraphVertex w = (DSAGraphVertex) dfs.dequeue();

            System.out.print("(" + v.getLabel() + ", " + w.getLabel() + ")");

        }
        System.out.print("}");
        System.out.println("");
            
    }

}