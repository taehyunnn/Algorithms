package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소스패닝트리_크루스칼 {

    private static int[] parent;
    private static List<int[]> edges;
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        // 크루스칼 알고리즘 사용
        // 가중치가 가장 작은 edge를 하나씩 꺼내서 연결
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        priorityQueue.addAll(edges);

        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int firstParent = findParent(poll[0]);
            int secondParent = findParent(poll[1]);

            if (firstParent == secondParent) {
                continue;
            }

            unionParent(firstParent, secondParent);
            result += poll[2]; // 가중치 더하기
        }

        System.out.println(result);
    }

    private static int findParent(int value) {
        if (value == parent[value]) {
            return value;
        }

        return parent[value] = findParent(parent[value]);
    }

    private static void unionParent(int firstParent, int secondParent) {
        parent[secondParent] = firstParent;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[node + 1];
        edges = new ArrayList<>(edge);

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            edges.add(new int[]{a, b, cost});
        }
    }
}
