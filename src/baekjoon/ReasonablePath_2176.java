package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReasonablePath_2176 {

    private static int answer = 0;
    private static final int BIG = 999999999;
    private static final int START = 1;
    private static final int END = 2;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        List<List<int[]>> links = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            links.add(new ArrayList<>());
        }

        int[] dist = new int[node + 1];
        Arrays.fill(dist, BIG);
        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            links.get(a).add(new int[]{b,cost});
            links.get(b).add(new int[]{a,cost});
        }

        dijkstra(dist, links);

        int[] dp = new int[node + 1];
        go(dp);

        System.out.println(answer);
    }

    private static void go(int[] dp) {

    }

    private static void dijkstra(int[] dist, List<List<int[]>> board) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{END, 0});
        dist[END] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNode = current[0];
            int currentDist = current[1];

            for (int[] ints : board.get(currentNode)) {
                int nextNode = ints[0];
                int nextNodeDist = ints[1];

                if (dist[nextNode] > dist[currentNode] + nextNodeDist) {
                    dist[nextNode] = dist[currentNode] + nextNodeDist;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

    }
}
