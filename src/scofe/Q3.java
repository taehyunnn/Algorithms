package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q3 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);

        int[][] board = new int[n][n];

        int cnt = 0;    // 크기가 1인 갯수
        // 방 초기화
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j) - '0';
                if (board[i][j] == 1) {
                    cnt++;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(cnt);

        int sum = cnt;
        for (int size = 2; size <= n; size++) {
            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isPossible(i, j, board, size)) {
                        cnt++;
                    }
                }
            }
            if (cnt == 0) {
                break;
            }
            sum += cnt;
            result.add(cnt);
        }

        System.out.println("total: " + sum);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(String.format("size[%d]: %d", i + 1, result.get(i)));
        }
    }

    private static boolean isPossible(int i, int j, int[][] board, int size) {
        if (i + size > board.length || j + size > board.length) {
            return false;
        }

        for (int k = i; k < i + size; k++) {
            for (int l = j; l < j + size; l++) {
                if (board[k][l] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
