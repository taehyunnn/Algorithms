package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ExcellentVillage {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[] numOfPeople = new int[n+1];
        List<List<Integer>> links = new ArrayList<>();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            numOfPeople[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(b);
            links.get(b).add(a);
        }

        int[][] dp = new int[n+1][2];

        start(1,-1,dp,links,numOfPeople);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void start(int num, int pre, int[][] dp, List<List<Integer>> links, int[] numOfPeople) {
        dp[num][0] = 0;
        dp[num][1] = numOfPeople[num];

        for (Integer nextVillage : links.get(num)) {
            if (nextVillage == pre) {
                continue;
            }

            start(nextVillage, num, dp, links, numOfPeople);
            dp[num][1] += dp[nextVillage][0];
            dp[num][0] += Math.max(dp[nextVillage][0], dp[nextVillage][1]);
        }
    }
}
