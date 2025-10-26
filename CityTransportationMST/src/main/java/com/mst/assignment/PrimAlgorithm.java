package com.mst.assignment;

import java.util.*;

/**
 * Implements Prim's algorithm for Minimum Spanning Tree.
 */
public class PrimAlgorithm {
    public static List<Edge> primMST(Graph graph) {
        int V = graph.vertices;
        Map<Integer, List<Edge>> adj = graph.getAdjacencyList();

        boolean[] visited = new boolean[V];
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visited[0] = true;  // start from vertex 0
        pq.addAll(adj.get(0));

        while (!pq.isEmpty() && mstEdges.size() < V - 1) {
            Edge edge = pq.poll();
            if (visited[edge.dest]) continue;

            visited[edge.dest] = true;
            mstEdges.add(edge);

            for (Edge next : adj.get(edge.dest)) {
                if (!visited[next.dest]) pq.add(next);
            }
        }

        return mstEdges;
    }

    public static int getTotalCost(List<Edge> mstEdges) {
        int sum = 0;
        for (Edge e : mstEdges) sum += e.weight;
        return sum;
    }
}
