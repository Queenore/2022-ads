package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/12720677
 */
public final class StronglyConnectedComponent {
    private StronglyConnectedComponent() {
        // Should not be instantiated
    }

    private enum Color {
        WHITE, GRAY, BLACK, PURPLE
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertexNumber];
        Color[] visited = new Color[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }

        for (int i = 0; i < edgesNumber; i++) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            graph[firstVert - 1].add(secondVert - 1);
        }

        Deque<Integer> topSortDeque = new ArrayDeque<>();
        for (int i = 0; i < vertexNumber; i++) {
            if (visited[i] == Color.WHITE) {
                topSort(visited, topSortDeque, graph, i);
            }
        }

        for (int i = 0; i < vertexNumber; i++) {
            visited[i] = Color.WHITE;
        }

        ArrayList<Integer>[] transposeGraph = transpose(graph, vertexNumber);
        Stack<Integer> vertexStack = new Stack<>();
        int[] sccId = new int[vertexNumber];
        for (Integer vertex : topSortDeque) {
            if (visited[vertex] == Color.WHITE) {
                dfs(transposeGraph, visited, vertex, vertexStack, sccId, vertex); // using vertex number as id
                while (!vertexStack.isEmpty()) {
                    visited[vertexStack.pop()] = Color.BLACK;
                }
            }
        }

        HashSet<Integer>[] connSet = new HashSet[vertexNumber];
        for (int vertex = 0; vertex < vertexNumber; vertex++) {
            int vertexId = sccId[vertex];
            if (connSet[vertexId] == null) {
                connSet[vertexId] = new HashSet<>();
            }
            for (Integer neighbourVertex : graph[vertex]) {
                int neighbourVertexId = sccId[neighbourVertex];
                if (vertexId != neighbourVertexId) {
                    connSet[vertexId].add(neighbourVertexId);
                }
            }
        }

        out.println(Arrays.stream(connSet).filter(Objects::nonNull).collect(Collectors.summarizingInt(HashSet::size)).getSum());
    }

    private static void dfs(ArrayList<Integer>[] graph, Color[] visited, int vertex,
                            Stack<Integer> vertexStack, int[] sccId, int id) {
        visited[vertex] = Color.GRAY;
        vertexStack.push(vertex);
        sccId[vertex] = id;
        for (int neighbourVertex : graph[vertex]) {
            Color currColor = visited[neighbourVertex];
            if (currColor == Color.WHITE) {
                dfs(graph, visited, neighbourVertex, vertexStack, sccId, id);
            }
        }
        visited[vertex] = Color.PURPLE;
    }

    private static void topSort(Color[] visited, Deque<Integer> topSortDeque, List<Integer>[] graph, int vertex) {
        visited[vertex] = Color.BLACK;
        for (Integer currVertex : graph[vertex]) {
            Color visitedCurrState = visited[currVertex];
            if (visitedCurrState == Color.WHITE) {
                topSort(visited, topSortDeque, graph, currVertex);
            }
        }
        topSortDeque.addFirst(vertex);
    }

    private static ArrayList<Integer>[] transpose(List<Integer>[] graph, int vertexNumber) {
        ArrayList<Integer>[] transposeGraph = new ArrayList[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            transposeGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < vertexNumber; i++) {
            for (Integer currVertex : graph[i]) {
                transposeGraph[currVertex].add(i);
            }
        }
        return transposeGraph;
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
