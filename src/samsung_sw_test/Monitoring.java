package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Monitoring {

    private static int[][] room;
    private static List<CCTV> cctvList;
    private static int min = Integer.MAX_VALUE;

    private static int[] dRow = new int[]{-1, 0, 1, 0}; // 상 좌 하 우
    private static int[] dCol = new int[]{0, -1, 0, 1};
    private static int[][] dir = new int[][]{
            {},
            {0},    // 1번 카메라는 한 방향
            {0, 2}, // 2번 카메라는 반대 방향
            {0, 1}, // 3번 카메라는 수직 방향
            {0, 1, 2},  // 4번 카메라는 한 방향만 안 본다
            {0, 1, 2, 3},   // 5번 카메라는 모든 방향
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        room = new int[n][m];

        cctvList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if (room[i][j] > 0 && room[i][j] < 6) {
                    cctvList.add(new CCTV(room[i][j], i, j));
                }
            }
        }

        bf(0, cctvList.size(), room);
        System.out.println(min);
    }

    private static void bf(int currentDepth, int targetDepth, int[][] room) {
        if (currentDepth == targetDepth) {
            min = Math.min(min, searchBlindSpot(room));
            return;
        }

        for (int i = 0; i < 4; i++) {   // 4방향 회전
            CCTV cctv = cctvList.get(currentDepth);
            int currentCCTVNum = cctv.num;

            // 방향이 달라질때마다 지도가 이전 결과를 가지고 있으며 안된다.
            int[][] tempRoom = getTempRoom(room);

            for (int j = 0; j < dir[currentCCTVNum].length; j++) {  // cctv 번호가 보고 있는 개수만큼 ex) 4번은 세 방향이므로 방향 각각 회전별로 계산
                int nextDir = (dir[currentCCTVNum][j] + i) % 4; // 4방향으로 회전해야 하므로
                int nextRow = cctv.row;
                int nextCol = cctv.col;

                while (true) {  // 정해진 방향으로 cctv로 확인했다는 표시를 남긴다.
                    nextRow += dRow[nextDir];
                    nextCol += dCol[nextDir];

                    if (!isRange(nextRow, nextCol) || tempRoom[nextRow][nextCol] == 6) { // 맵을 벗어나거나 벽을 만나면 끝
                        break;
                    }

                    tempRoom[nextRow][nextCol] = 7;
                }

                bf(currentDepth + 1, targetDepth, tempRoom);
            }

        }
    }

    private static int[][] getTempRoom(int[][] room) {
        int[][] tempRoom = new int[room.length][room[0].length];

        for (int i = 0; i < room.length; i++) {
            System.arraycopy(room[i], 0, tempRoom[i], 0, room[i].length);
        }

        return tempRoom;
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < room.length && nextCol < room[0].length;
    }

    private static int searchBlindSpot(int[][] room) {
        int cnt = 0;
        for (int[] ints : room) {
            for (int j = 0; j < room[0].length; j++) {
                if (ints[j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class CCTV {
        int num;
        int row;
        int col;

        CCTV(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }
    }
}
