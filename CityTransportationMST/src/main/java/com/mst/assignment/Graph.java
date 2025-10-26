package com.mst.assignment;

import java.util.*;

/**
 * Graph class to represent city districts (vertices)
 * and possible roads (edges) with costs.
 */
public class Graph {
    public int vertices;
    public List<Edge> edges = new ArrayList<>();

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Convert to adjacency list for Prim’s algorithm
    public Map<Integer, List<Edge>> getAdjacencyList() {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.src).add(e);
            adj.get(e.dest).add(new Edge(e.dest, e.src, e.weight)); // undirected
        }
        return adj;
    }
}
