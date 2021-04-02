package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DragonCurve {

    // 0: x좌표가 증가하는 방향 (→)
    //1: y좌표가 감소하는 방향 (↑)
    //2: x좌표가 감소하는 방향 (←)
    //3: y좌표가 증가하는 방향 (↓)
    private static int[] dRow = new int[]{0, -1, 0, 1};
    private static int[] dCol = new int[]{1, 0, -1, 0};
    private static int[][] board = new int[101][101];
    private static List<Dragon> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int startCol = Integer.parseInt(stringTokenizer.nextToken());
            int startRow = Integer.parseInt(stringTokenizer.nextToken());
            int dir = Integer.parseInt(stringTokenizer.nextToken());
            int generation = Integer.parseInt(stringTokenizer.nextToken());

            list.add(new Dragon(startRow, startCol, dir, generation));
        }

        // 드래곤이 있는 좌표 표시
        checkDragonLocation();

        // 4각형 개수 세기
        int cnt = getCnt();

        System.out.println(cnt);
    }

    private static void checkDragonLocation() {
        for (Dragon dragon : list) {
            int nextRow = dragon.startRow;
            int nextCol = dragon.startCol;

            board[nextRow][nextCol] = 1;

            for (Integer direction : dragon.directions) {
                nextRow += dRow[direction];
                nextCol += dCol[direction];

                board[nextRow][nextCol] = 1;
            }
        }
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isRectangle(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isRectangle(int i, int j) {
        return board[i][j] == 1 && board[i + 1][j] == 1 & board[i][j + 1] == 1 && board[i + 1][j + 1] == 1;
    }

    static class Dragon {
        List<Integer> directions = new ArrayList<>();
        int startRow;
        int startCol;

        Dragon(int startRow, int startCol, int dir, int generation) {
            this.startRow = startRow;
            this.startCol = startCol;
            directions.add(dir);
            upGeneration(0, generation);
        }

        void upGeneration(int currentLevel, int maxLevel) {
            if (currentLevel == maxLevel) {
                return;
            }

            List<Integer> temp = new ArrayList<>();
            for (int i = directions.size()-1; i >=0 ; i--) {
                Integer direction = directions.get(i);
                int nextDir = (direction + 1) % 4;
                temp.add(nextDir);
            }
            directions.addAll(temp);

            upGeneration(currentLevel + 1, maxLevel);
        }
    }
}
