package lw05.prelab;

import java.util.*;

public class Main {

    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsOrder = new ArrayList<>();

    static void addEdge(int u, int v, int minutes) {
        graph[u][v] = minutes;
        graph[v][u] = minutes;
    }

    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i != path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();                                   
            order.add(current);
            for (int next = 0; next < size; next++) {
                if (graph[current][next] != 0 && !visited.contains(next)) { 
                    visited.add(next);
                    queue.add(next);                                    
                }
            }
        }
        return order;
    }

    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);                                            
        for (int next = 0; next < size; next++) {
            if (graph[current][next] != 0 && !visited.contains(next)) {
                dfs(next, visited);                                       
            }
        }
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < minDist) {                       
                minDist = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void route(int[] prev, int destination) {
        List<Integer> path = new ArrayList<>();
        int at = destination;
        while (at != -1) {
            path.add(0, at);
            at = prev[at];                                                
        }
        printPath(path);
    }

    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            int u = findTheNextNode(dist, visited);
            visited[u] = true;                                           
            for (int v = 0; v < size; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] + graph[u][v] < dist[v]) { 
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;                                          
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }
        System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");
        route(prev, destination);
        System.out.println("Total time: " + dist[destination] + " min");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }
        graph = new int[size][size];

        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int minutes = sc.nextInt();
            addEdge(u, v, minutes);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();
        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, destination);
    }
}