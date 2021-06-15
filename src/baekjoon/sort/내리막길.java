package baekjoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길 {

    private static int[][] map;
    private static int n, m;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(dfs(n - 1, m - 1, 0));
        System.out.println();
    }

    private static int dfs(int row, int col, int postHeight) {
        if (row < 0 || col < 0 || row == n || col == m) {
            return 0;
        }

        if (map[row][col] <= postHeight) {
            return 0;
        }

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        if (row == 0 && col == 0) {
            return 1;
        }


        dp[row][col] = 0;
        dp[row][col] += dfs(row + 1, col, map[row][col]);
        dp[row][col] += dfs(row - 1, col, map[row][col]);
        dp[row][col] += dfs(row, col + 1, map[row][col]);
        dp[row][col] += dfs(row, col - 1, map[row][col]);

        return dp[row][col];
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][m];
        dp = new int[n][m];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }
}
