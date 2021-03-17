package baekjoon.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sum5_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int testCnt = Integer.parseInt(stringTokenizer.nextToken());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[][] dp = new int[n + 1][n + 1];
        calc(dp, board);

        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row1 = Integer.parseInt(stringTokenizer.nextToken());
            int col1 = Integer.parseInt(stringTokenizer.nextToken());
            int row2 = Integer.parseInt(stringTokenizer.nextToken());
            int col2 = Integer.parseInt(stringTokenizer.nextToken());

            System.out.println(dp[row2][col2] - dp[row2][col1 - 1] - dp[row1 - 1][col2] + dp[row1 - 1][col1 - 1]);
        }

    }

    private static void calc(int[][] dp, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] - dp[i][j] + board[i][j];
            }
        }
    }

}
