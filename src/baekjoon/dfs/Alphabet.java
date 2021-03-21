package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {

    private static final int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int max = 0;
    private static boolean[] visit = new boolean[26];
    private static int[][] board;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - 'A';
            }
        }

        visit[board[0][0]] = true;
        dfs(0, 0, 1);
        System.out.println(max);
    }

    private static void dfs(int row, int col, int length) {
        max = Math.max(max, length);
        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isRange(nextRow, nextCol) && !visit[board[nextRow][nextCol]]) {
                visit[board[nextRow][nextCol]] = true;
                dfs(nextRow,nextCol,length+1);
                visit[board[nextRow][nextCol]] = false;
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m;
    }
}
