package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IntervalDivision {

    private static int n, m;
    private static int[] nums;
    private static int[] sumOfNums;
    private static int[][] dp;
    private static boolean[][] visit;
    private static final int MIN = -999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        nums = new int[n + 1];
        sumOfNums = new int[n + 1];
        dp = new int[m + 1][n + 1];
        visit = new boolean[m + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(bufferedReader.readLine());
            sumOfNums[i] = sumOfNums[i - 1] + nums[i];
        }

        System.out.println(recursive(m, n));
    }

    private static int recursive(int row, int col) {
        if (row == 0) {
            return 0;
        }

        if (col < 0) {
            return MIN;
        }

        if (visit[row][col]) {
            return dp[row][col];
        }

        visit[row][col] = true;

        dp[row][col] = recursive(row, col - 1);
        for (int i = col; i > 0; i--) {
            dp[row][col] = Math.max(dp[row][col], recursive(row - 1, i - 2) + sumOfNums[col] - sumOfNums[i - 1]);
        }

        return dp[row][col];
    }
}
