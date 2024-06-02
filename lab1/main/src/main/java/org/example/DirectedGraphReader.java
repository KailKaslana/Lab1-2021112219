package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirectedGraphReader {
    public static void main(String[] args) {
        String inputFile = "main/src/main/java/org/example/output.txt";

        try {
            // 创建一个Map来存储有向图的边和权重
            Map<String, Map<String, Integer>> graph = new HashMap<>();

            // 读取文件并解析内容
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // 使用空格分割单词
                String sourceNode = parts[0];
                String targetNode = parts[1];
                int weight = Integer.parseInt(parts[2]);

                // 将边和权重添加到图中
                graph.computeIfAbsent(sourceNode, k -> new HashMap<>()).put(targetNode, weight);
            }
            reader.close();

            // 输出有向图
            System.out.println("Directed Graph:");
            for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
                String sourceNode = entry.getKey();
                Map<String, Integer> edges = entry.getValue();
                for (Map.Entry<String, Integer> edge : edges.entrySet()) {
                    String targetNode = edge.getKey();
                    int weight = edge.getValue();
                    System.out.println(sourceNode + " -> " + targetNode + ", weight: " + weight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

