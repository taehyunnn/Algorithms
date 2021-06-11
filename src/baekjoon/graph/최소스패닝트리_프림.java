package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소스패닝트리_프림 {

    private static int[] parent;
    private static List<List<int[]>> edges;
    private static boolean[][] visit;
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        // 프림 알고리즘
        // 현재 구성된 그래프의 노드들에 연결된 간선들 중에서 가중치가 가장 작은 간선을 고른다.
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        priorityQueue.addAll(edges.get(1));


        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();

            int from = poll[0];
            int to = poll[1];

            if (visit[from][to]) {
                continue;
            }

            int firstParent = findParent(from);
            int secondParent = findParent(to);

            if (firstParent == secondParent) {
                continue;
            }

            union(firstParent, secondParent);
            visit[from][to] = visit[to][from] = true;
            priorityQueue.addAll(edges.get(to));
            result += poll[2];
        }

        System.out.println(result);
    }

    private static void union(int firstParent, int secondParent) {
        parent[secondParent] = firstParent;
    }

    private static int findParent(int value) {
        if (value == parent[value]) {
            return value;
        }

        return parent[value] = findParent(parent[value]);
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[node + 1];
        edges = new ArrayList<>(node + 1);
        visit = new boolean[node + 1][node + 1];

        for (int i = 0; i <= node; i++) {
            parent[i] = i;
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            edges.get(a).add(new int[]{a, b, cost});
            edges.get(b).add(new int[]{b, a, cost});
        }
    }
}
