package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RedGreen {

    public static final int[][] moves = new int[][]{{0, 1}, {-1, 0}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());

        char[][] board = new char[n][n];
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        int a = 0, b = 0;
        a = start(n, board, visit, queue, a);
        changeBoard(board);
        clearVisit(visit);
        b = start(n, board, visit, queue, b);
        System.out.println(a + " " + b);

    }

    private static int start(int n, char[][] board, boolean[][] visit, Queue<int[]> queue, int num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) {
                    continue;
                }
                num = bfs(board, visit, queue, num, i, j);
            }
        }
        return num;
    }

    private static void changeBoard(char[][] board) {
        for (char[] chars : board) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'R') {
                    chars[i] = 'G';
                }
            }
        }
    }

    private static void clearVisit(boolean[][] visit) {
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
    }

    private static int bfs(char[][] board, boolean[][] visit, Queue<int[]> queue, int count, int i, int j) {
        char ch;
        count++;
        ch = board[i][j];
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] move : moves) {
                int nextRow = current[0] + move[0];
                int nextCol = current[1] + move[1];

                if (isPossible(nextRow, nextCol, board, visit) && board[nextRow][nextCol] == ch) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return count;
    }

    private static boolean isPossible(int nextRow, int nextCol, char[][] board, boolean[][] visit) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length && !visit[nextRow][nextCol];
    }
}
