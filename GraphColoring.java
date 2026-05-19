import java.util.Scanner;

public class GraphColoring {

    int V;                  // Number of vertices
    int[] color;            // Store colors of vertices

    // Function to check if current color can be assigned
    boolean isSafe(int v, int graph[][], int color[], int c) {

        for (int i = 0; i < V; i++) {

            // Check adjacent vertices
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }

        return true;
    }

    // Backtracking function
    boolean graphColoringUtil(int graph[][], int m, int color[], int v) {

        // If all vertices are assigned color
        if (v == V) {
            return true;
        }

        // Try different colors
        for (int c = 1; c <= m; c++) {

            // Check if color is safe
            if (isSafe(v, graph, color, c)) {

                color[v] = c;

                // Recur for next vertex
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true;
                }

                // Backtrack
                color[v] = 0;
            }
        }

        return false;
    }

    // Main coloring function
    boolean graphColoring(int graph[][], int m) {

        color = new int[V];

        // Start coloring from vertex 0
        if (!graphColoringUtil(graph, m, color, 0)) {

            System.out.println("Solution does not exist");
            return false;
        }

        printSolution();
        return true;
    }

    // Print colors
    void printSolution() {

        System.out.println("Solution Exists:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " ---> Color " + color[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        int graph[][] = new int[v][v];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        GraphColoring gc = new GraphColoring();
        gc.V = v;

        gc.graphColoring(graph, m);

        sc.close();
    }
}