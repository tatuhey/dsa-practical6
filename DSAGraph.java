
public class DSAGraph{
    private DSALinkedList m_vertices;

    public DSAGraph() {
        m_vertices = new DSALinkedList();
    }

    public void addVertex(String label, Object value) {
        m_vertices = new DSAGraphVertex(value, label);
    }


    private class DSAGraphVertex {
        private Object m_value;
        private String m_label;
        private int m_edges;
        private boolean m_visited;
        private DSALinkedList m_links;
        
        public DSAGraphVertex(Object inValue, String inLabel) {
            m_value = inValue;
            m_label = inLabel;
            m_edges = 0;
            m_visited = false;
            m_links = new DSALinkedList();

        }
        
        // accessor
        public Object getValue() {
            return m_value;
        }

        public String getLabel() {
            return m_label;
        }

        public int getEdges() {
            return m_edges;
        }

        public boolean getVisited() {
            return m_visited;
        }

        public DSALinkedList getAdjacent() {
            return m_links;
        }

        @Override 
        public String toString() {
            return "Vertex: " + m_label + " | Visited: " + m_visited;
        }

        // mutator
        public void setVisited() {
            m_visited = true;
        }

        public void clearVisited() {
            m_visited = false;
        }
    }

}