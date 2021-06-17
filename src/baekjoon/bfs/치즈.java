package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {

    private static final int CHEEZE = 1;
    private static final int AIR = 0;
    private static final int MELTINGCHEEZE = 2;

    private static int r, c;
    private static int[][] map;
    private static boolean[][] visit;
    private static int cheezeNum;
    private static int[] dRow = new int[]{0, 0, -1, 1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();

        int time = 0;
        int meltingCheezeNum = cheezeNum;
        while (cheezeNum != 0) {

            // (0,0) 위치에서 공기랑 맞닿은 치즈를 삭제
            meltingCheezeNum = bfs();
            removeMeltingCheeze();

            // 남은 치즈 개수 확인
            cheezeNum -= meltingCheezeNum;
            time++;
        }

        System.out.println(time);
        System.out.println(meltingCheezeNum);
    }

    private static void removeMeltingCheeze() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == MELTINGCHEEZE) {
                    map[i][j] = AIR;
                }
            }
        }
    }

    private static int bfs() {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        clearVisit();
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = poll[0] + dRow[i];
                int nextCol = poll[1] + dCol[i];

                if(!isRange(nextRow,nextCol) || visit[nextRow][nextCol]) continue;

                if (map[nextRow][nextCol] == CHEEZE) {
                    map[nextRow][nextCol] = MELTINGCHEEZE;
                    cnt ++;
                }

                if (map[nextRow][nextCol] == AIR) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return cnt;
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >=0 && nextCol >=0 && nextRow < r && nextCol < c;
    }

    private static void clearVisit() {
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == CHEEZE) {
                    cheezeNum++;
                }
            }
        }
    }
}
