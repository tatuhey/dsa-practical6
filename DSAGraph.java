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
        DSAGraphVertex newVertex = new DSAGraphVertex(value, label);
        m_vertices.insertLast(newVertex);
    }

    public void addEdge(String label1, String label2) {
        DSAGraphVertex vertex = 
    }


}