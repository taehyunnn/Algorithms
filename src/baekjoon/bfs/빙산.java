package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {

    private static int[][] map;
    private static int row, col;

    private static int[] dRow = new int[]{0, 0, -1, 1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();


        int yearCount = 0;
        while (true) {
            // 빙산의 개수 파악
            int cnt = bfs();

            if (cnt == 0) {
                System.out.println(0);
                break;
            } else if (cnt > 1) {
                System.out.println(yearCount);
                break;
            } else {
                meltIceBerg();
                yearCount++;
            }
        }
    }

    private static void meltIceBerg() {
        int[][] tempMap = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isIceBerg(i, j)) {
                    int cntAroundWater = calcWater(i, j);

                    tempMap[i][j] = Math.max(map[i][j] - cntAroundWater, 0);
                }
            }
        }

        map = tempMap;
    }

    private static int calcWater(int i, int j) {
        int cnt = 0;

        for (int k = 0; k < dRow.length; k++) {
            int nextRow = i + dRow[k];
            int nextCol = j + dCol[k];

            if (isRange(nextRow, nextCol) && !isIceBerg(nextRow, nextCol)) {
                cnt++;
            }
        }

        return cnt;
    }

    private static int bfs() {
        int cnt = 0;
        boolean[][] visit = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isIceBerg(i, j) && isNotVisited(visit, i, j)) {
                    cnt++;

                    // 연결된 빙산 확인
                    checkIceBergConnecting(visit, i, j);
                }
            }
        }

        return cnt;
    }

    private static void checkIceBergConnecting(boolean[][] visit, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visit[i][j] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int k = 0; k < dRow.length; k++) {
                int nextRow = poll[0] + dRow[k];
                int nextCol = poll[1] + dCol[k];

                if (isRange(nextRow, nextCol) && isIceBerg(nextRow, nextCol) && isNotVisited(visit, nextRow, nextCol)) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < row && nextCol < col;
    }

    private static boolean isNotVisited(boolean[][] visit, int i, int j) {
        return !visit[i][j];
    }

    private static boolean isIceBerg(int i, int j) {
        return map[i][j] != 0;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        row = Integer.parseInt(stringTokenizer.nextToken());
        col = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }
}
