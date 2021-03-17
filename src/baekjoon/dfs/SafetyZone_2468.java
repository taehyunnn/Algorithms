package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafetyZone_2468 {

    public static final int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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

        int max = 0;

        int[][] tempBoard = new int[n][n];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < tempBoard.length; j++) {
                tempBoard[j] = board[j].clone();
            }
            max = Math.max(max, bfs(i, tempBoard));
        }

        System.out.println(max);
    }

    private static int bfs(int height, int[][] tempBoard) {
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {
                if (tempBoard[i][j] <= height) {
                    tempBoard[i][j] = -2;
                }
            }
        }

        final int VISIT = -1;
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {
                if (tempBoard[i][j] > VISIT) {
                    tempBoard[i][j] = VISIT;
                    queue.add(new int[]{i, j});
                    count++;
                }

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();

                    for (int[] move : moves) {
                        int nextRow = current[0] + move[0];
                        int nextCol = current[1] + move[1];


                        if (isPossible(nextRow, nextCol, tempBoard) && tempBoard[nextRow][nextCol] > VISIT) {
                            tempBoard[nextRow][nextCol] = VISIT;
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean isPossible(int nextRow, int nextCol, int[][] tempBoard) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < tempBoard.length && nextCol < tempBoard[0].length;
    }
}
