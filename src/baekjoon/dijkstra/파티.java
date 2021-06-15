package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파티 {

    private static int[] go, back;
    private static List<List<int[]>> goLinks, backLinks;
    private static int partyPlace;

    public static void main(String[] args) throws IOException {
        init();

        int result = 0;

        dijkstra(partyPlace, back, backLinks);
        dijkstra(partyPlace, go, goLinks);

        for (int i = 1; i < go.length; i++) {
            result = Math.max(result, back[i] + go[i]);
        }

        System.out.println(result);
    }

    private static void dijkstra(int start, int[] dist, List<List<int[]>> links) {
        Arrays.fill(dist, 1000 * 10000 * 100);

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{start, 0});
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNode = current[0];
            int costFromPartyPlace = current[1];

            if (dist[currentNode] < costFromPartyPlace) {
                continue;
            }

            for (int[] next : links.get(currentNode)) {
                int nextNode = next[0];
                int costFromCurrentToNext = next[1];

                if (dist[nextNode] > costFromPartyPlace + costFromCurrentToNext) {
                    dist[nextNode] = costFromPartyPlace + costFromCurrentToNext;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        partyPlace = Integer.parseInt(stringTokenizer.nextToken());

        go = new int[n + 1];
        back = new int[n + 1];
        goLinks = new ArrayList<>(n + 1);
        backLinks = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            goLinks.add(new ArrayList<>());
            backLinks.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            goLinks.get(a).add(new int[]{b, cost});
            backLinks.get(b).add(new int[]{a, cost});
        }
    }
}
