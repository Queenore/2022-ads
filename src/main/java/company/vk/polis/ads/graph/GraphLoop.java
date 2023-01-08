package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 * <p>
 * https://www.eolymp.com/ru/submissions/12621000
 */
public final class GraphLoop {
    private GraphLoop() {
        // Should not be instantiated
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        if (!in.reader.ready()) {
            out.println(-1);
            return;
        }

        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertexNumber];
        Color[] visited = new Color[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }

        for (int i = 0; i < edgesNumber; i++) {
            int firstVert = in.nextInt() - 1;
            int secondVert = in.nextInt() - 1;
            graph[firstVert].add(secondVert);
            graph[secondVert].add(firstVert);
        }

        int min = Integer.MAX_VALUE;
        Set<Integer> loopSet = new TreeSet<>();
        for (int currVertex = 1; currVertex < vertexNumber; currVertex++) {
            if (visited[currVertex] == Color.WHITE) {
                Stack<Integer> mainStack = new Stack<>();
                dfs(visited, graph, mainStack, loopSet, null, currVertex);
            }
        }

        for (Integer element : loopSet) {
            if (element < min) {
                min = element;
            }
        }

        out.println((min < Integer.MAX_VALUE) ? "Yes\n" + (min + 1) : "No");
    }

    private static void dfs(Color[] visited, List<Integer>[] graph,
                            Stack<Integer> mainStack, Set<Integer> loopSet, Integer fromVertex, Integer toVertex) {
        visited[toVertex] = Color.GRAY;
        boolean wasLoop = false;
        for (Integer currVertex : graph[toVertex]) {
            if (Objects.equals(fromVertex, currVertex)) {
                continue;
            }
            Color visitedCurrState = visited[currVertex];
            if (visitedCurrState == Color.WHITE) {
                mainStack.push(currVertex);
                dfs(visited, graph, mainStack, loopSet, toVertex, currVertex);
            } else if (visitedCurrState == Color.GRAY) {
                wasLoop = true;
                loopSet.add(currVertex);
                while (mainStack.size() > 0 && !Objects.equals(mainStack.peek(), currVertex)) {
                    loopSet.add(mainStack.pop());
                }
            }
        }
        if (!wasLoop) {
            while (mainStack.size() > 0 && !Objects.equals(mainStack.peek(), fromVertex)) {
                mainStack.pop();
            }
        }
        visited[toVertex] = Color.BLACK;
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
