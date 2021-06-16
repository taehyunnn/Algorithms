package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형 {

    private static int n, m;
    private static int[][] map;
    private static int[][] dp;
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                result = Math.max(dp[i][j], result);
            }
        }

        System.out.println(result * result);
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            String s = bufferedReader.readLine();
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = s.charAt(j - 1) - '0';
            }
        }
    }
}
