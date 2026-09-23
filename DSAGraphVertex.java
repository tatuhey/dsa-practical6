public class DSAGraphVertex {
    private Object m_value;
    private String m_label;
    private int m_edgeCount;
    private boolean m_visited;
    private DSALinkedList m_edges; // edges aka links between vertices
    
    public DSAGraphVertex(Object inValue, String inLabel) {
        m_value = inValue;
        m_label = inLabel;
        m_edgeCount = 0;
        m_visited = false;
        m_edges = new DSALinkedList();
    }
    
    // accessor
    public Object getValue() {
        return m_value;
    }

    public String getLabel() {
        return m_label;
    }

    public int getEdges() {
        return m_edgeCount;
    }

    public boolean getVisited() {
        return m_visited;
    }

    public DSALinkedList getAdjacent() {
        return m_edges;
    }

    @Override 
    public String toString() {
        return "Vertex: " + m_label + " | Visited: " + m_visited;
    }

    // mutator
    public void setVisited() {
        m_visited = true;
    }

    public void setLinks(DSALinkedList newList) {
        m_edges = newList;
    }

    public void clearVisited() {
        m_visited = false;
    }

    public void addEdge(DSAGraphVertex vertex) {
        m_edges.insertLast(vertex);
        m_edgeCount++;
    }

    public void removeEdge(DSAGraphVertex vertex) {
        m_edges.remove(vertex);
        m_edgeCount--;
    }
}