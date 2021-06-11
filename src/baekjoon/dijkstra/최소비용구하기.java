package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소비용구하기 {

    private static List<List<int[]>> links;
    private static int[] dist;
    private static int start, end;

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();
        System.out.println(dist[end]);
    }

    private static void dijkstra() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{start, 0});
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int currentNode = poll[0];
            int cost = poll[1];

            if (dist[currentNode] < cost) {
                continue;
            }

            for (int[] edge : links.get(currentNode)) {
                int nextNode = edge[0];
                int costFromCurrentToNext = edge[1];

                if (dist[nextNode] > dist[currentNode] + costFromCurrentToNext) {
                    dist[nextNode] = dist[currentNode] + costFromCurrentToNext;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());

        links = new ArrayList<>(n + 1);
        dist = new int[n + 1];
        Arrays.fill(dist, 1000000000);

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());

        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            addEdge(a, b, cost);
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        start = Integer.parseInt(stringTokenizer.nextToken());
        end = Integer.parseInt(stringTokenizer.nextToken());

    }

    private static void addEdge(int a, int b, int cost) {
        links.get(a).add(new int[]{b, cost});
    }
}
