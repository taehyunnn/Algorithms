package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TransferPipe {

    private static int n;
    private static int[][] room;
    private static int cnt;
    private static final int HORIZONTAL = 1;
    private static final int VERTICAL = -1;
    private static final int DIAGONAL = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        room = new int[n][n];

        initRoom(bufferedReader, n);

        dp(0, 1, HORIZONTAL);

        System.out.println(cnt);
    }

    private static void dp(int row, int col, int direction) {
        // 맵 벗어남
        if (row == n || col == n) {
            return;
        }

        // 벽
        if (room[row][col] == 1) {
            return;
        }

        // 대각선일땐 위 왼쪽도 벽이면 안됨
        if (direction == DIAGONAL) {
            if (room[row - 1][col] == 1 || room[row][col - 1] == 1) {
                return;
            }
        }

        // 도착
        if (row == n - 1 && col == n - 1) {
            cnt++;
        }


        if (direction == HORIZONTAL) {
            dp(row, col + 1, direction);
        } else if (direction == VERTICAL) {
            dp(row + 1, col, direction);
        } else {
            dp(row + 1, col, VERTICAL);
            dp(row, col + 1, HORIZONTAL);
        }
        dp(row+1, col+1, DIAGONAL);
    }


    private static void initRoom(BufferedReader bufferedReader, int n) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
