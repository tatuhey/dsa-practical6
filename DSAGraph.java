public class DSAGraph{
    private DSALinkedList m_vertices;

    public DSAGraph() {
        m_vertices = new DSALinkedList();
    }

    // accessor
    public boolean hasVertex(){
        boolean result = false;
        return result;
    }

    public int getVertexCount() {
        int count = 0;
        for (Object elem : m_vertices) {
            
        }
        return count;
    }
    
    // mutator
    public void addVertex(Object value, String label) {
        DSAGraphVertex newVertex = new DSAGraphVertex(value, label);
    }

    public void addEdge(String label1, String label2) {
        
    }


}