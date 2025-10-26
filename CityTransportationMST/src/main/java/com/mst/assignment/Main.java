package com.mst.assignment;

import com.google.gson.*;
import java.io.*;
import java.util.*;

/**
 * Main class:
 * - Reads graph input from JSON
 * - Runs Prim’s & Kruskal’s algorithms
 * - Prints & saves MST results
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Read JSON input
            JsonObject data = JsonParser.parseReader(new FileReader("src/main/resources/assign_3_input.json.json")).getAsJsonObject();
            JsonArray graphs = data.getAsJsonArray("graphs");

            JsonArray outputGraphs = new JsonArray();

            for (JsonElement gElem : graphs) {
                JsonObject gObj = gElem.getAsJsonObject();
                int vertices = gObj.get("vertices").getAsInt();
                JsonArray edges = gObj.getAsJsonArray("edges");

                Graph graph = new Graph(vertices);
                for (JsonElement eElem : edges) {
                    JsonObject eObj = eElem.getAsJsonObject();
                    graph.addEdge(eObj.get("src").getAsInt(),
                            eObj.get("dest").getAsInt(),
                            eObj.get("weight").getAsInt());
                }

                long startPrim = System.nanoTime();
                List<Edge> primMST = PrimAlgorithm.primMST(graph);
                long endPrim = System.nanoTime();

                long startKruskal = System.nanoTime();
                List<Edge> kruskalMST = KruskalAlgorithm.kruskalMST(graph);
                long endKruskal = System.nanoTime();

                JsonObject result = new JsonObject();
                result.addProperty("vertices", vertices);
                result.addProperty("edges", graph.edges.size());
                result.addProperty("Prim_total_cost", PrimAlgorithm.getTotalCost(primMST));
                result.addProperty("Kruskal_total_cost", KruskalAlgorithm.getTotalCost(kruskalMST));
                result.addProperty("Prim_time_ms", (endPrim - startPrim) / 1_000_000.0);
                result.addProperty("Kruskal_time_ms", (endKruskal - startKruskal) / 1_000_000.0);

                outputGraphs.add(result);

                System.out.println("Graph with " + vertices + " vertices:");
                System.out.println("Prim MST cost: " + PrimAlgorithm.getTotalCost(primMST));
                System.out.println("Kruskal MST cost: " + KruskalAlgorithm.getTotalCost(kruskalMST));
                System.out.println();
            }

            JsonObject outputData = new JsonObject();
            outputData.add("results", outputGraphs);

            // Save output JSON
            try (FileWriter writer = new FileWriter("src/main/resources/assign_3_output.json")) {
                new GsonBuilder().setPrettyPrinting().create().toJson(outputData, writer);
            }

            System.out.println("✅ Results saved to assign_3_output.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
