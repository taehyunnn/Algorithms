package baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDelivery {

    private static List<int[]> houses = new ArrayList<>();
    private static List<int[]> chickenStore = new ArrayList<>();
    private static boolean[] visit;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] city = new int[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (city[i][j] == 1) {
                    houses.add(new int[]{i, j});
                } else if (city[i][j] == 2) {
                    chickenStore.add(new int[]{i, j});
                }
            }
        }

        visit = new boolean[chickenStore.size()];

        bf(-1, 0, m);
        System.out.println(min);
    }

    private static void bf(int index, int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {
            calc();
            return;
        }

        for (int i = index + 1; i < chickenStore.size(); i++) {
            visit[i] = true;
            bf(i, currentDepth + 1, targetDepth);
            visit[i] = false;
        }
    }

    private static void calc() {
        int currentMin = 0;
        for (int[] house : houses) {
            int temp = Integer.MAX_VALUE;
            for (int i = 0; i < visit.length; i++) {
                int dist = Math.abs(house[0] - chickenStore.get(i)[0]) + Math.abs(house[1] - chickenStore.get(i)[1]);
                if (visit[i]) {
                    temp = Math.min(temp, dist);
                }
            }
            currentMin += temp;
        }
        min = Math.min(min, currentMin);
    }
}
