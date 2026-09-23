import java.util.*;

public class DSAGraph{
    private DSALinkedList m_vertices;

    public DSAGraph() {
        m_vertices = new DSALinkedList();
    }

    // accessor
    public boolean hasVertex(String label){
        boolean result = false;

    }

    public int getVertexCount(String label) {
        int count = 0;
        return count;
    }

    public int getEdgeCount(String label) {
        int count = 0;
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

    }
    
    public boolean isAdjacent(String label1, String label2) {
        boolean result = false;
        return result;
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

        if(vertexOne == null || vertexTwo == null) {
            throw new NoSuchElementException
        }
        
    }


}