package com.mst.assignment;

/**
 * Represents a road (edge) connecting two districts (vertices)
 * with a given construction cost (weight).
 */
public class Edge implements Comparable<Edge> {
    public int src;     // source district
    public int dest;    // destination district
    public int weight;  // construction cost

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return src + " -- " + dest + " (" + weight + ")";
    }
}
