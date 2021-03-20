package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int m, n;
        String[] split = input.split(" ");
        n = Integer.parseInt(split[1]);
        m = Integer.parseInt(split[0]);

        int[][] board = new int[n][m];

        StringTokenizer stringTokenizer;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]) + board[i-1][j-1];
            }
        }

        System.out.println(dp[n][m]);
    }
}
