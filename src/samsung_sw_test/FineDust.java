package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FineDust {

    private static int[][] room;
    private static int[] airCleanerUp;
    private static int[] airCleanerDown;
    private static Queue<int[]> dustList;
    private static int[] dRow = new int[]{0, 0, 1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int t = Integer.parseInt(stringTokenizer.nextToken());

        room = new int[n][m];
        dustList = new LinkedList<>();

        init(br, n, m);

        // t초 동안 동작
        for (int i = 0; i < t; i++) {

            // 새로운 미세먼지 추가할 임시 방
            int[][] tempRoom = new int[n][m];
            spreadDust(tempRoom);

            // 기존 방에 새로운 먼지 모두 추가 해준다.
            addNewDustToRoom(n, m, tempRoom);

//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < m; k++) {
//                    System.out.print(room[j][k] +" " );
//                }
//                System.out.println();
//            }

            // 공기청정기 작동
            airCleanerProcess();

            // 먼지들을 찾고 큐에삽입
            addAllDustToQueue(n, m);
        }

        // 미세먼지 양 구하기
        int result = getResult(n, m);
        System.out.println(result);
    }

    private static int getResult(int n, int m) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
//                System.out.print(room[i][j]+" ");
                if (room[i][j] > 0) {
                    result += room[i][j];
                }
            }
//            System.out.println();
        }
        return result;
    }

    private static void addAllDustToQueue(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] > 0) {
                    dustList.add(new int[]{i, j, room[i][j]});
                }
            }
        }
    }

    private static void airCleanerProcess() {
        upProcess();
        downProcess();
    }

    private static void upProcess() {
        int airFlowRow = airCleanerUp[0] - 1;
        int airFlowCol = airCleanerUp[1];

        while (airFlowRow != 0) {
            room[airFlowRow][airFlowCol] = room[airFlowRow - 1][airFlowCol];
            airFlowRow--;
        }

        while (airFlowCol != room[0].length-1) {
            room[airFlowRow][airFlowCol] = room[airFlowRow][airFlowCol + 1];
            airFlowCol++;
        }

        while (airFlowRow != airCleanerUp[0]) {
            room[airFlowRow][airFlowCol] = room[airFlowRow + 1][airFlowCol];
            airFlowRow++;
        }

        while (airFlowCol != 1) {
            room[airFlowRow][airFlowCol] = room[airFlowRow][airFlowCol - 1];
            airFlowCol--;
        }

        room[airFlowRow][airFlowCol] = 0;
    }

    private static void downProcess() {
        int airFlowRow = airCleanerDown[0] + 1;
        int airFlowCol = airCleanerDown[1];

        while (airFlowRow != room.length - 1) {
            room[airFlowRow][airFlowCol] = room[airFlowRow + 1][airFlowCol];
            airFlowRow++;
        }

        while (airFlowCol != room[0].length-1) {
            room[airFlowRow][airFlowCol] = room[airFlowRow][airFlowCol + 1];
            airFlowCol++;
        }

        while (airFlowRow != airCleanerDown[0]) {
            room[airFlowRow][airFlowCol] = room[airFlowRow - 1][airFlowCol];
            airFlowRow--;
        }

        while (airFlowCol != 1) {
            room[airFlowRow][airFlowCol] = room[airFlowRow][airFlowCol - 1];
            airFlowCol--;
        }

        room[airFlowRow][airFlowCol] = 0;
    }

    private static void spreadDust(int[][] tempRoom) {
        while (!dustList.isEmpty()) {
            int[] dust = dustList.poll();
            // 4방향 확산
            int spreadDust = dust[2] / 5;
            for (int j = 0; j < dRow.length; j++) {
                int nextRow = dust[0] + dRow[j];
                int nextCol = dust[1] + dCol[j];

                // 임시 방에 새로운 먼지 증가
                if (isRange(nextRow, nextCol) && room[nextRow][nextCol] != -1) {
                    tempRoom[nextRow][nextCol] += spreadDust;
                    // 기존 방에서는 먼지 감소
                    room[dust[0]][dust[1]] -= spreadDust;
                }
            }
        }
    }

    private static void addNewDustToRoom(int n, int m, int[][] newDust) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] += newDust[i][j];
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < room.length && nextCol < room[0].length;
    }

    private static void init(BufferedReader br, int n, int m) throws IOException {
        StringTokenizer stringTokenizer;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if (room[i][j] == -1) { // 공기 청정기
                    airCleanerDown = new int[]{i, 0};
                    airCleanerUp = new int[]{i - 1, 0};
                } else if (room[i][j] != 0) {   // 미세 먼지
                    dustList.add(new int[]{i, j, room[i][j]});
                }
            }
        }
    }
}
