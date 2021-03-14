package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReasonablePath_2176 {

    private static final int BIG = 999999999;
    private static final int START = 1;
    private static final int END = 2;
    private static List<List<int[]>> links = new ArrayList<>();
    private static int[] dist;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int node = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i <= node; i++) {
            links.add(new ArrayList<>());
        }

        dist = new int[node + 1];
        Arrays.fill(dist, BIG);
        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            links.get(a).add(new int[]{b, cost});
            links.get(b).add(new int[]{a, cost});
        }

        dijkstra();

        dp = new int[node + 1];
        Arrays.fill(dp,-1);
        System.out.println(go(START));

    }

    private static int go(int cur) {
        if (dp[cur] != -1) return dp[cur];
        if (cur == 2) return dp[cur] = 1;
        dp[cur] = 0;
        for (int[] info : links.get(cur)) {
            int next = info[0];
            if (dist[cur] > dist[next]) {
                dp[cur] += go(next);
            }
        }
        return dp[cur];
    }


    private static void dijkstra() {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{END, 0});
        dist[END] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNode = current[0];

            for (int[] ints : links.get(currentNode)) {
                int nextNode = ints[0];
                int cost = ints[1];

                if (dist[nextNode] > dist[currentNode] + cost) {
                    dist[nextNode] = dist[currentNode] + cost;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
}
