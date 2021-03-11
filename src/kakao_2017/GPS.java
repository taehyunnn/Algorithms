package kakao_2017;

import java.util.*;

class GPS {

    private final int INF = 999999999;

    /**
     * @param n         거점 개수
     * @param m         도로의 개수
     * @param edge_list 거점간의 연결 정보
     * @param k         택시가 보내는 시간대별 거점 정보의 총 개수
     * @param gps_log   머물렀던 거점 정보
     */
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        int[][] map = new int[n+1][n+1];

        // 맵 초기화
        initMap(edge_list, map);

        int[][] dp = new int[k][n+1]; // dp[i][j] : 경로의 i번째 값의 위치로 j노드가 왔을때 몇 번 고쳐야 하는지

        // 출발 노드 세팅
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }
        dp[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    if (map[j][l] == INF) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][l]);
                }
                dp[i][j] += j == gps_log[i] ? 0 : 1;
            }
        }

        return dp[k - 1][gps_log[k - 1]] >= INF ? -1 : dp[k - 1][gps_log[k - 1]];
    }


    private void initMap(int[][] edge_list, int[][] map) {
        for (int i = 1; i < map.length; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int[] ints : edge_list) {
            map[ints[0]][ints[1]] = map[ints[1]][ints[0]] = 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new GPS().solution(7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[]{1, 2, 3, 3, 6, 7}));
    }
}