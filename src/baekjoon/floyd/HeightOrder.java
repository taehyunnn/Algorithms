package baekjoon.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeightOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int numOfPeople = Integer.parseInt(stringTokenizer.nextToken());
        int edge = Integer.parseInt(stringTokenizer.nextToken());

        boolean[][] board = new boolean[numOfPeople][numOfPeople];

        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            board[Integer.parseInt(stringTokenizer.nextToken())-1][Integer.parseInt(stringTokenizer.nextToken())-1] = true;
        }

        for (int k = 0; k < numOfPeople; k++) {
            for (int i = 0; i < numOfPeople; i++) {
                for (int j = 0; j < numOfPeople; j++) {
                    if (i == j) {
                        board[i][j] = true;
                        continue;
                    }
                    board[i][j] = board[i][j] || (board[i][k] && board[k][j]);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < numOfPeople; i++) {
            if (count(numOfPeople, board, i)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean count(int numOfPeople, boolean[][] board, int i) {
        for (int j = 0; j < numOfPeople; j++) {
            if (!board[i][j] && !board[j][i]) {
                return false;
            }
        }
        return true;
    }

}
