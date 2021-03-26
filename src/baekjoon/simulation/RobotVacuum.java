package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotVacuum {

    private static int[][] room;
    private static int[] dRow = new int[]{-1, 0, 1, 0}; //  북 동 남 서
    private static int[] dCol = new int[]{0, 1, 0, -1}; //  북 동 남 서
    private static final int BLANK = 0;
    private static final int WALL = 1;
    private static final int CLEAN = 2;
    private static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        room = new int[n][m];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int startRow = Integer.parseInt(stringTokenizer.nextToken());
        int startCol = Integer.parseInt(stringTokenizer.nextToken());
        int startDir = Integer.parseInt(stringTokenizer.nextToken());


        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        vacuum(startRow, startCol, startDir);
        System.out.println(result);
    }

    private static void vacuum(int row, int col, int dir) {
        // step 1
        cleanRoom(row, col);

        // step 2
        search(row, col, dir, 0);
    }

    private static boolean search(int row, int col, int dir, int depth) {
        if (depth == 4) {   // 네 방향 모두 청소되었거나 벽일 경우,
            int nextDir = (dir + 2) % 4;    // 후진 방향
            int nextRow = row + dRow[nextDir];
            int nextCol = col + dCol[nextDir];

            if (room[nextRow][nextCol] == WALL) {  // 후진이 불가능 하면 종료
                return true;
            } else {    // 후진 하고 다시 탐색
                if (search(nextRow, nextCol, dir, 0)) {
                    return true;
                } // 이동은 하지만 방향은 유지한다
            }
        }

        int nextDir = (dir + 3) % 4;
        int nextRow = row + dRow[nextDir];
        int nextCol = col + dCol[nextDir];

        if (room[nextRow][nextCol] == BLANK) {
            vacuum(nextRow, nextCol, nextDir);
        } else {
            // 왼쪽 방향 계속 탐색
            return search(row, col, nextDir, depth + 1);
        }
        return true;
    }

    private static void cleanRoom(int row, int col) {
        room[row][col] = CLEAN;
        result++;
    }

}
