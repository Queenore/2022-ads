package company.vk.polis.ads.kr2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class Graph {
    private final Integer vertexNumber;
    private final Integer edgesNumber;
    private ArrayList<Integer>[] graph;

    public Graph(Integer vertexNumber, Integer edgesNumber) {
        this.vertexNumber = vertexNumber;
        this.edgesNumber = edgesNumber;
        this.graph = new ArrayList[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addConnection(Integer firstVertex, Integer secondVertex) {
        graph[firstVertex - 1].add(secondVertex - 1);
    }

    public boolean contains(Integer vertex) {
        return bfs(vertex - 1);
    }

    private boolean bfs(Integer vertex) {
        Deque<Integer> deque = new ArrayDeque<>();
        Boolean[] visited = new Boolean[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            visited[i] = false;
        }
        deque.add(0);
        while (!deque.isEmpty()) {
            Integer dequeVertex = deque.pollFirst();
            System.out.println(dequeVertex + 1);
            if (Objects.equals(dequeVertex, vertex)) {
                return true;
            }
            for (Integer currVertex : graph[dequeVertex]) {
                if (!visited[currVertex]) {
                    deque.addLast(currVertex);
                    visited[currVertex] = true;
                }
            }
        }
        return false;
    }

}
