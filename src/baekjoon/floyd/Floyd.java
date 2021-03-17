package baekjoon.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Floyd {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int node = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        int BIG = 999999999;
        int[][] board = new int[node][node];
        for (int i = 0; i < board.length; i++) {
            int[] ints = board[i];
            Arrays.fill(ints, BIG);
            board[i][i] = 0;
        }

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            board[a - 1][b - 1] = Math.min(board[a - 1][b - 1], cost);
        }

        for (int k = 0; k < node; k++) {
            for (int i = 0; i < node; i++) {
                for (int j = 0; j < node; j++) {
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                if (board[i][j] == BIG) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
