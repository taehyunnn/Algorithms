package programmers.kakao_2021;

import java.util.Arrays;

public class TaxiFare {

    private static final int MAX = 99999999;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        // 1. s에서 각 노드까지 가는 비용을 계산
        // 2. 각 노드에서 a,b 까지의 비용을 계산
        // 1.2. 에서 구한 값을 더한 값의 최소값이 정답

        int[][] map = new int[n + 1][n + 1];
        initMap(n, fares, map);

        // 중간노드
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (map[j][k] > map[j][i] + map[i][k]) {
                        map[j][k] = map[j][i] + map[i][k];
                    }
                }
            }
        }

        int min = MAX;
        // 최소 비용 계산
        for (int i = 1; i <= n; i++) {
            min = Math.min (min, map[s][i] + map[i][a] + map[i][b]);
        }
        return min;
    }

    private void initMap(int n, int[][] fares, int[][] map) {
        for (int[] ints : map) {
            Arrays.fill(ints, MAX);
        }
        for (int[] fare : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }
        for (int i = 0; i < n + 1; i++) {
            map[i][i] = 0;
        }
    }

    public static void main(String[] args) {
        new TaxiFare().solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
    }
}
