package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Incline {

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());

        board = new int[2 * n][n];

        initBoard(br, n);

        int cnt = 0;

        for (int i = 0; i < 2 * n; i++) {
            if (isPossible(board[i], l)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean isPossible(int[] road, int l) {
        boolean[] check = new boolean[road.length];

        for (int i = 0; i < road.length - 1; i++) {
            // 다음 칸과의 차이가 2 이상이면 이동 불가능
            if (Math.abs(road[i] - road[i + 1]) > 1) {
                return false;
            }

            // 다음 칸과의 차이가 1이면 경사로의 길이를 확인
            // 낮은 쪽의 길이가 l보다 작으면 이동 불가능
            // 올라가는 경사로는 이전에 설치한 경로가 있는지 확인해야 한다.
            if (road[i] < road[i + 1]) {
                if (i + 1 - l < 0) return false;

                for (int j = i; j > i - l; j--) {
                    if (road[i] != road[j] || check[j]) {   // 높이가 다르거나 방문한 적 있으면 실패
                        return false;
                    }
                }
            }
            // 내려가는 경사로는 이전에 설치한 경사로는 없으므로 확인은 안 해도 되지만, 새로 설치할 경사로 표시를 해줘야 한다.
            else if (road[i] > road[i + 1]) {
                if (i + l >= road.length) return false;    // 추가해야 되는 경사로의 길이가 맵을 넘어가면 이동 불가능

                for (int j = i + 1; j < i + 1 + l; j++) {
                    check[j] = true;
                    if (road[i + 1] != road[j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void initBoard(BufferedReader br, int n) throws IOException {
        StringTokenizer stringTokenizer;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = n; i < 2 * n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[j][i - n];
            }
        }
    }
}
