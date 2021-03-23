package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotControl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] board = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[] temp = new int[m + 2];

        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        dp[1][1] = board[1][1];
        for (int i = 2; i <= m; i++) {
            dp[1][i] += dp[1][i - 1] + board[1][i];
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1];  // 이 초기화 값 없이 진행할 때 문제점 : dp[i-1][1] 이 음수면 dp[i-1][1] 과 dp[i][0] max에서 0이 돼버린다
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + board[i][j];
            }

            temp[m + 1] = dp[i - 1][m]; // 이 것도 위와 마찬가지
            for (int j = m; j >= 1; j--) {
                temp[j] = Math.max(dp[i - 1][j], temp[j + 1]) + board[i][j];
            }

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i][j], temp[j]);
            }
        }
        System.out.println(dp[n][m]);
    }
}
