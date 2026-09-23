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

    public void checkVertex(DSAGraphVertex vert, String label) {
        if(vert == null) 
            throw new NoSuchElementException("Vertex " + label + " does not exist");
    }

    public void displayAsList() {

    }

    public void displayAsMatrix() {

    }

    // mutator
    public void addVertex(Object value, String label) {
        DSAGraphVertex newVertex = new DSAGraphVertex(value, label); // create new vertex
        m_vertices.insertLast(newVertex); // put it in the linkedlist
    }

    public void addEdge(String label1, String label2) {
        DSAGraphVertex vertexOne = getVertex(label1);
        DSAGraphVertex vertexTwo = getVertex(label2);
        checkVertex(vertexOne, label1);
        checkVertex(vertexTwo, label2);
        vertexOne.addEdge(vertexTwo); // undirected graph = add each other
        vertexTwo.addEdge(vertexOne);       
    }


}