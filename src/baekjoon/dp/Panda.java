package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Panda {

    private static final int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[][] dp = new int[n][n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max,dfs(i, j, board, dp));
            }
        }

        System.out.println(max + 1);
    }

    private static Integer dfs(int i, int j, int[][] board, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        for (int[] move : moves) {
            int nextRow = i + move[0];
            int nextCol = j + move[1];
            if (isPossible(nextRow, nextCol, i, j, board)) {
                dp[i][j] = Math.max(dp[i][j], dfs(nextRow, nextCol, board, dp) + 1);
            }
        }

        return dp[i][j];
    }

    private static boolean isPossible(int nextRow, int nextCol, int i, int j, int[][] board) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length && board[nextRow][nextCol] > board[i][j];
    }
}
