package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 네트워크연결 {

    private static int[] parent;
    private static Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int computer = poll[0];
            int otherComputer = poll[1];
            int cost = poll[2];

            if (isConnectedWithTwoComputers(computer, otherComputer)) {
                continue;
            }

            union(computer, otherComputer);
            result += cost;
        }

        System.out.println(result);
    }

    private static boolean isConnectedWithTwoComputers(int computer, int otherComputer) {
        return find(computer) == find(otherComputer);
    }

    private static int find(int computer) {
        if (computer == parent[computer]) {
            return computer;
        }

        return parent[computer] = find(parent[computer]);
    }

    private static void union(int computer, int otherComputer) {
        parent[find(computer)] = parent[find(otherComputer)];
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int numOfComputer = Integer.parseInt(bufferedReader.readLine());
        int numOfEdge = Integer.parseInt(bufferedReader.readLine());

        parent = new int[numOfComputer + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < numOfEdge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            queue.add(new int[]{a, b, c});
        }
    }
}
