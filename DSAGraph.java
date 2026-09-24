import java.util.*;

public class DSAGraph{
    private DSALinkedList m_vertices;

    public DSAGraph() {
        m_vertices = new DSALinkedList();
    }

    //region accessor
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

    //endregion

    //region mutator
    public void addVertex(Object value, String label) { // add node
        DSAGraphVertex newVertex = new DSAGraphVertex(value, label); // create new vertex
        m_vertices.insertLast(newVertex); // put vertex in the linkedlist
    }

    public void deleteVertex(String label) {
        DSAGraphVertex target = getVertex(label);

        checkVertex(target, label);
        m_vertices.remove(target); // remove from vertices

        for(Object vert : m_vertices) { // for all vertex in the linked list
            DSAGraphVertex temp = (DSAGraphVertex) vert;
            temp.getAdjacent().remove(target); // remove all adjacent from vertex
        }       
    }

    public void addEdge(String label1, String label2) { 
        DSAGraphVertex vertexOne = getVertex(label1);
        DSAGraphVertex vertexTwo = getVertex(label2);

        checkVertex(vertexOne, label1); // check if each vert exists
        checkVertex(vertexTwo, label2);

        vertexOne.addEdge(vertexTwo); // undirected graph = add each other
        vertexTwo.addEdge(vertexOne);       
    }

    public void deleteEdge(String label1, String label2) {
        DSAGraphVertex vertexOne = getVertex(label1);
        DSAGraphVertex vertexTwo = getVertex(label2);

        checkVertex(vertexOne, label1);
        checkVertex(vertexTwo, label2);

        vertexOne.removeEdge(vertexTwo); // undirected graph, delete each other
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


        for(Object listVert : m_vertices) { // for all vertices
            vert = (DSAGraphVertex) listVert;

            System.out.print(vert.getLabel() + ": "); // print the labels

            for(Object listAdj : vert.getAdjacent()) { // for all adjacent vertices
                adjVert = (DSAGraphVertex) listAdj; 
                System.out.print(adjVert.getLabel() + " "); // print all the labels
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
        for(Object topRowVert : m_vertices) { // for all vertices
            vert = (DSAGraphVertex) topRowVert;
            System.out.print(vert.getLabel() + " "); // print all label as top row, vertex
        }
        System.out.println("");
        for(Object rowItem : m_vertices) { // for each row afterwards
            DSAGraphVertex rowVert = (DSAGraphVertex) rowItem;
            
            System.out.print(rowVert.getLabel() + " ");  // print first column, vertex

            for(Object colItem : m_vertices) { // for each row items, after the first column
                DSAGraphVertex colVert = (DSAGraphVertex) colItem;
                
                if (isAdjacent(rowVert.getLabel(), colVert.getLabel())) {
                    System.out.print("1 "); // if the column match with row, print 1
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println(); // Move to the next row
        }

    }

    private void sort() {
        m_vertices = sortVertexList(m_vertices); // sort main vertex list
        DSAGraphVertex temp = null;

        for(Object vert : m_vertices) { // for all vertices already sorted
            temp = (DSAGraphVertex) vert;
            DSALinkedList sortedAdjList = sortVertexList(temp.getAdjacent()); // sort its adjacent vertex list
            temp.setLinks(sortedAdjList);
        }
    }

    private DSALinkedList sortVertexList(DSALinkedList vertList) {
        int count = 0;
        int i = 0;

        for(Object vert : vertList) // loop through all vertices to get its count number for insertion sort
            count++;

        DSAGraphVertex[] vertArr = new DSAGraphVertex[count]; // create array size count vfor insertion sort

        for(Object vert : vertList) { // loop through all vertices to put them into the array
            vertArr[i] = (DSAGraphVertex) vert;
            i++;
        }

        vertexInsertSort(vertArr, count); // call insertion sort
        DSALinkedList sortedLinkedList = new DSALinkedList();

        for(int j = 0; j < count; j++) // add the newly sorted array to a sorted linked list
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

    private DSAQueue breadthFirstSearch() {
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
        DSAQueue bfs = vert.breadthFirstSearch(); // do bfs

        if(bfs.isEmpty()) {
            System.out.println("The graph is empty");
            return;
        }

        System.out.print("BFS : {");

        while(!bfs.isEmpty()) { // while looping through the queue of bfs'ed vertcies
            DSAGraphVertex v = (DSAGraphVertex) bfs.dequeue(); // dequeue 1
            DSAGraphVertex w = (DSAGraphVertex) bfs.dequeue(); // dequeue 2

            System.out.print("(" + v.getLabel() + ", " + w.getLabel() + ")"); // print 1 then 2

        }
        System.out.print("}");
        System.out.println("");
            
    }

    private DSAGraphVertex unvisitedAdjacent(DSAGraphVertex v) { // helper method to return w as the unvisidted vertex
        for (Object adj : v.getAdjacent()) {
            DSAGraphVertex w = (DSAGraphVertex) adj;
            if (!w.getVisited()) {
                return w;
            }
        }
        return null;
    }

    private DSAQueue depthFirstSearch() {
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

        System.out.print("DFS : {");

        while(!dfs.isEmpty()) {
            DSAGraphVertex v = (DSAGraphVertex) dfs.dequeue();
            DSAGraphVertex w = (DSAGraphVertex) dfs.dequeue();

            System.out.print("(" + v.getLabel() + ", " + w.getLabel() + ")");

        }
        System.out.print("}");
        System.out.println("");
            
    }

    //endregion

}