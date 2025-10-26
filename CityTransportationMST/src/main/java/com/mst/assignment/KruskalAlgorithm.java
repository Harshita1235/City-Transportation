package com.mst.assignment;

import java.util.*;

/**
 * Implements Kruskal's algorithm for Minimum Spanning Tree.
 */
public class KruskalAlgorithm {
    // Disjoint Set Union (Union-Find)
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (x != parent[x])
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else { parent[ry] = rx; rank[rx]++; }
        }
    }

    public static List<Edge> kruskalMST(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        Collections.sort(graph.edges);

        DSU dsu = new DSU(graph.vertices);

        for (Edge edge : graph.edges) {
            int root1 = dsu.find(edge.src);
            int root2 = dsu.find(edge.dest);

            if (root1 != root2) {
                mstEdges.add(edge);
                dsu.union(root1, root2);
            }

            if (mstEdges.size() == graph.vertices - 1) break;
        }

        return mstEdges;
    }

    public static int getTotalCost(List<Edge> mstEdges) {
        int sum = 0;
        for (Edge e : mstEdges) sum += e.weight;
        return sum;
    }
}
