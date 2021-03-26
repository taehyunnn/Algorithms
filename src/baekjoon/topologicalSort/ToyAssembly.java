package baekjoon.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ToyAssembly {

    private static List<List<int[]>> links;
    private static int[] numOfComponent;
    private static List<int[]> basicComponent;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        numOfComponent = new int[n + 1];
        inDegree = new int[n + 1];
        links = new ArrayList<>(n + 1);
        basicComponent = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        input(bufferedReader, m);
        start(n);
        searchBasicComponent();
        printResult();
    }

    private static void start(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        numOfComponent[n]++;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (int[] next : links.get(current)) {
                int nextComponent = next[0];
                int requiredCntForNextComponent = next[1];

                numOfComponent[nextComponent] += requiredCntForNextComponent * numOfComponent[current];
                inDegree[nextComponent]--;

                if (inDegree[nextComponent] == 0) {
                    queue.add(nextComponent);
                }
            }
        }
    }

    private static void printResult() {
        basicComponent.sort(Comparator.comparingInt(o -> o[0]));

        for (int[] ints : basicComponent) {
            System.out.println(ints[0] + " " + ints[1]);
        }
    }

    private static void searchBasicComponent() {
        for (int i = 1; i < links.size(); i++) {
            if (links.get(i).size() == 0) {
                basicComponent.add(new int[]{i, numOfComponent[i]});
            }
        }
    }

    private static void input(BufferedReader bufferedReader, int m) throws IOException {
        StringTokenizer stringTokenizer;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            links.get(from).add(new int[]{to, cost});
            inDegree[to]++;
        }
    }
}
