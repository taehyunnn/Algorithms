package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SpecialShortestPath {

    private static List<List<int[]>> links = new ArrayList<>();
    private static final int BIG = 800*1000;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());
        dist = new int[node + 1];

        for (int i = 0; i <= node; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            links.get(a).add(new int[]{b, cost});
            links.get(b).add(new int[]{a, cost});
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = 1;
        int firstStop = Integer.parseInt(stringTokenizer.nextToken());
        int secondStop = Integer.parseInt(stringTokenizer.nextToken());

        int min = 0;
        min += dijkstra(start, firstStop);
        min += dijkstra(firstStop, secondStop);
        min += dijkstra(secondStop, node);

        int min2 = 0;
        min2 += dijkstra(start, secondStop);
        min2 += dijkstra(secondStop, firstStop);
        min2 += dijkstra(firstStop, node);

        min = Math.min(min, min2);

        if (min >= BIG) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, BIG);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNode = current[0];
            int currentDist = current[1];

            for (int[] next : links.get(currentNode)) {
                int nextNode = next[0];
                int distFromCurrentToNext = next[1];
                if (dist[nextNode] > currentDist + distFromCurrentToNext) {
                    dist[nextNode] = currentDist + distFromCurrentToNext;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        return dist[end];
    }
}
