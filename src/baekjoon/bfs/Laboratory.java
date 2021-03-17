package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Laboratory {

    private static List<int[]> virus;
    private static int max = Integer.MIN_VALUE;
    private static int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final int BLANK = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;
    private static final int WALL_MAX = 3;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] board = new int[n][m];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (board[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        createWall(board, 0); // 벽 생성
        System.out.println(max);
    }

    private static void createWall(int[][] board, int numOfWall) {
        if (numOfWall == WALL_MAX) {
            int[][] tempBoard = new int[board.length][board[0].length];
            for (int i = 0; i < tempBoard.length; i++) {
                tempBoard[i] = board[i].clone();
            }
            bfs(tempBoard);
            max = Math.max(max, numOfSafetyZone(tempBoard));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == BLANK) {
                    board[i][j] = WALL;
                    createWall(board, numOfWall + 1);
                    board[i][j] = BLANK;
                }
            }
        }
    }

    private static void bfs(int[][] tempBoard) {
        Queue<int[]> queue = new LinkedList<>(virus);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] move : moves) {
                int nextRow = current[0] + move[0];
                int nextCol = current[1] + move[1];

                if (isPossible(nextRow, nextCol, tempBoard)) {
                    tempBoard[nextRow][nextCol] = VIRUS;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }


    }

    private static boolean isPossible(int nextRow, int nextCol, int[][] tempBoard) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < tempBoard.length && nextCol < tempBoard[0].length && tempBoard[nextRow][nextCol] == BLANK;
    }

    private static int numOfSafetyZone(int[][] tempBoard) {
        int count = 0;
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {
                if (tempBoard[i][j] == BLANK) {
                    count++;
                }
            }
        }
        return count;
    }
}
