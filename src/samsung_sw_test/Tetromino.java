package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {

    private static int[][][] special = new int[][][]{
            {{1, -1}, {0, 1}, {0, 1}},
            {{-1, -1}, {1, 0}, {1, 0}},
            {{-1, -1}, {0, 1}, {0, 1}},
            {{-1, 1}, {1, 0}, {1, 0}}
    };

    private static int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}};
    private static int max = Integer.MIN_VALUE;
    private static final int TARGET = 4;
    private static int[][] board;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n, m;
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = true;
                dfs(i, j, board[i][j], 1, TARGET);
                specialMove(i, j, board[i][j]);
                visit[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void specialMove(int i, int j, int score) {
        for (int[][] ints : special) {
            int tempScore = score;
            int nextRow = i, nextCol = j;
            for (int[] anInt : ints) {
                nextRow += anInt[0];
                nextCol += anInt[1];

                if (!isPossible(nextRow, nextCol)) {
                    break;
                }

                tempScore += board[nextRow][nextCol];
            }
            max = Math.max(tempScore, max);
        }
    }

    private static void dfs(int i, int j, int score, int current, int target) {
        if (current == target) {
            max = Math.max(max, score);
            return;
        }

        for (int[] move : moves) {
            int nextRow = i + move[0];
            int nextCol = j + move[1];

            if (!isPossible(nextRow, nextCol)) {
                continue;
            }

            if (visit[nextRow][nextCol]) {
                continue;
            }

            visit[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, score + board[nextRow][nextCol], current + 1, target);
            visit[nextRow][nextCol] = false;
        }
    }

    private static boolean isPossible(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length;
    }
}
