package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACM_Craft {

    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int testCnt = Integer.parseInt(stringTokenizer.nextToken());
        int buildingCnt;
        int orderRule;
        int[] buildTime;
        List<List<Integer>> links;
        int[] inComingCnt;

        int target;
        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            buildingCnt = Integer.parseInt(stringTokenizer.nextToken());
            orderRule = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            buildTime = new int[buildingCnt + 1];
            inComingCnt = new int[buildingCnt + 1];

            for (int j = 0; j < buildingCnt; j++) {
                buildTime[j + 1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            links = new ArrayList<>();
            for (int j = 0; j <= buildingCnt; j++) {
                links.add(new ArrayList<>());
            }

            for (int j = 0; j < orderRule; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int start =Integer.parseInt(stringTokenizer.nextToken());
                int end =Integer.parseInt(stringTokenizer.nextToken());
                inComingCnt[end]++;
                links.get(start).add(end);
            }
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            target = Integer.parseInt(stringTokenizer.nextToken());

            start(links, target, inComingCnt, buildTime);
            System.out.println(max);
            max = 0;
        }
    }

    private static void start(List<List<Integer>> links, int target, int[] inComingCnt, int[] buildTime) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[inComingCnt.length];

        for (int i = 1; i < inComingCnt.length; i++) {
            dp[i] = buildTime[i];
            if (inComingCnt[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == target && inComingCnt[current] == 0) {
                max = dp[current];
                break;
            }

            for (Integer next : links.get(current)) {
                inComingCnt[next]--;
                dp[next] = Math.max(dp[next], dp[current] + buildTime[next]);

                if (inComingCnt[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}
