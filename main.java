import java.util.*;

public class main{

    public static void main(String[] args) {
        int sel = 100000;
        Scanner sc = new Scanner(System.in);

        DSAGraph graph = new DSAGraph();

        while(sel != 0) {
            System.out.println("Select menu:");
            System.out.println("1. Add vertex\n2. Delete vertex\3. Add edge\n4. Delete edge\n5. displayAsList()\n6. displayAsMatrix()\n7. Breadth first search\n8. Depth first search\n0. Exit");
            
            try {
                sel = sc.nextInt();
                switch(sel) {
                    case 1:
                        addVert(sc, graph);
                        break;
                    case 2:
                        delVert(sc, graph);
                        break;
                    case 3:
                        addEdge(sc, graph);
                        break;
                    case 4:
                        delEdge(sc, graph);
                        break;
                    case 5:
                        dispAsList(sc, graph);
                        break;
                    case 6:
                        dispAsMatrix(sc, graph);
                        break;
                    case 7:
                        bfs(sc, graph);
                        break;
                    case 8:
                        dfs(sc, graph);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Wrong selection");
                }

            } catch (InputMismatchException e) {
                sel = 100000;
                sc.nextLine();
                System.out.println(e + ". Please input selection properly.");
                
            } catch (NoSuchElementException e2) {
                System.out.println(e2);
            }
        
        }

        sc.close();
    }

    public static void addVert(Scanner sc, DSAGraph graph) {
        sc.nextLine(); // to clear the leftover newline from the previous menu choice

        System.out.println("Add a vertex below. Format is <vertex>");
        String input = sc.nextLine();
        
        try {
            graph.addVertex(input, input);
        } catch (IllegalArgumentException e) {   
            System.out.println(e + ". Try again");
        }
        
    }

    public static void delVert(Scanner sc, DSAGraph graph) {
        sc.nextLine(); // to clear the leftover newline from the previous menu choice

        System.out.println("Remove a vertex below. Format is <vertex>");
        String input = sc.nextLine();
    }

    public static void addEdge(Scanner sc, DSAGraph graph) {
        
    }
    
    public static void delEdge(Scanner sc, DSAGraph graph) {
        
    }
    
    public static void dispAsList(Scanner sc, DSAGraph graph) {
        
    }
    
    public static void dispAsMatrix(Scanner sc, DSAGraph graph) {
        
    }
    
    public static void bfs(Scanner sc, DSAGraph graph) {
        
    }
    
    public static void dfs(Scanner sc, DSAGraph graph) {
        
    }

}