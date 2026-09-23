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

    public void displayAsList() { // sort first then display
        DSAGraphVertex vert = null;
        DSAGraphVertex adjVert = null;

        for(Object rowVert : m_vertices) { // columns at top
            vert = (DSAGraphVertex) rowVert;

            System.out.print(vert.getLabel() + ": ");
        }
        System.out.println("");
        for(Object listVert : m_vertices) {
            vert = (DSAGraphVertex) listVert;
            
            for(Object listAdj : vert.getAdjacent()) {
                adjVert = (DSAGraphVertex) listAdj;
                System.out.print(adjVert.getLabel() + " ");
            }
        }
    }

    public void displayAsMatrix() {

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
}