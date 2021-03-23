package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WeightLimit {

    private static List<List<int[]>> links = new ArrayList<>();
    private static int min, max;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(new int[]{b, cost});
            links.get(b).add(new int[]{a, cost});
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken());
        int end = Integer.parseInt(stringTokenizer.nextToken());

        initMinAndMax(start, end);

        int result = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (isPossible(start, end, mid)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(result);
    }

    private static boolean isPossible(int start, int end, int weight) {
        Arrays.fill(visit, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (int[] next : links.get(current)) {
                int nextNode = next[0];
                int nextCost = next[1];

                if (nextCost < weight || visit[nextNode]) {
                    continue;
                }

                if (nextNode == end) {
                    return true;
                }

                visit[nextNode] = true;
                queue.add(nextNode);
            }
        }
        return false;
    }


    private static void initMinAndMax(int start, int end) {
        for (int[] next : links.get(start)) {
            min = Math.min(min, next[1]);
            max = Math.max(max, next[1]);
        }

        for (int[] next : links.get(end)) {
            min = Math.min(min, next[1]);
            max = Math.max(max, next[1]);
        }
    }
}
