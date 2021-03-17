package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int startNode = Integer.parseInt(stringTokenizer.nextToken());

        List<List<int[]>> links = new ArrayList<>();

        for (int i = 0; i <= node; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(new int[]{b,cost});
        }

        int[] dist = new int[node+1];
        int BIG = 999999999;
        Arrays.fill(dist, BIG);
        dist[startNode] = 0;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        priorityQueue.add(new int[]{startNode, 0});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[0];

            for (int[] next : links.get(currentNode)) {
                int nextNode = next[0];
                int distFromCurrentToNext = next[1];
                if (dist[nextNode] > dist[currentNode] + distFromCurrentToNext) {
                    dist[nextNode] = dist[currentNode] + distFromCurrentToNext;
                    priorityQueue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == BIG) {
                System.out.println("INF");
            } else{
                System.out.println(dist[i]);
            }
        }
    }
}
