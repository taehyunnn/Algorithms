package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬 {

    private static int n, m;
    private static char[][] map;
    private static final char WATER = 'W';
    private static final char LAND = 'L';
    private static int[] dRow = new int[]{1, -1, 0, 0};
    private static int[] dCol = new int[]{0, 0, 1, -1};
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == WATER) continue;

                // 인접한 육지의 개수가 1,2 개 일때만 탐색 시작
                int numOfAdjacentLand = findAdjacentLand(i, j);
                if (numOfAdjacentLand > 2) continue;

                // bfs로 탐색하여 가장 거리가 먼 지점 찾고 result와 비교
                int currentLongest = findLongestLand(i, j);
                result = Math.max(currentLongest, result);
            }
        }

        System.out.println(result);
    }

    private static int findAdjacentLand(int i, int j) {
        int cnt = 0;

        for (int k = 0; k < dRow.length; k++) {
            int nextRow = i + dRow[k];
            int nextCol = j + dCol[k];

            if (isRange(nextRow, nextCol) && isLand(nextRow, nextCol)) {
                cnt++;
            }
        }

        return cnt;
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m;
    }

    private static boolean isLand(int nextRow, int nextCol) {
        return map[nextRow][nextCol] == LAND;
    }

    private static int findLongestLand(int row, int col) {
        int longestDistance = 0;
        boolean[][] visit = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col, 0});
        visit[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int currentRow = poll[0];
            int currentCol = poll[1];
            int currentDist = poll[2];

            longestDistance = currentDist;

            for (int i = 0; i < dCol.length; i++) {
                int nextRow = currentRow + dRow[i];
                int nextCol = currentCol + dCol[i];

                if (!isRange(nextRow, nextCol) || !isLand(nextRow, nextCol) || isVisited(visit, nextRow, nextCol))
                    continue;

                visit[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol, currentDist + 1});
            }
        }

        return longestDistance;
    }

    private static boolean isVisited(boolean[][] visit, int nextRow, int nextCol) {
        return visit[nextRow][nextCol];
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }
}
