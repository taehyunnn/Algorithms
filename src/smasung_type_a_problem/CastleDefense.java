package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CastleDefense {

    private static final int[] dRow = new int[]{0, -1, 0};
    private static final int[] dCol = new int[]{-1, 0, 1};

    private static int[][] map;
    private static int max = 0;
    private static int numOfEnemy;
    private static int d;

    public static void main(String[] args) throws IOException {
        initMap();

        // combine 을 이용한 궁수 배치
        gameProcess(-1, 0, 3);
        System.out.println(max);
    }

    private static void gameProcess(int index, int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {
            // 궁수 배치 끝나면 게임 시작
            startGame();
            return;
        }

        int n = map.length;

        // 궁수 배치 (조합 사용)
        for (int i = index + 1; i < map[0].length; i++) {
            map[n - 1][i] = 1;
            gameProcess(i, currentDepth + 1, targetDepth);
            map[n - 1][i] = 0;
        }
    }

    private static void startGame() {
        int cnt = 0;
        // 궁수 배치 마다 게임 여러 번 실행할거니까 맵은 복사해서 사용
        int tempNumOfEnemy = numOfEnemy;
        int[][] tempMap = getTempMap();

        while (numOfEnemy != 0) {

            List<int[]> deathEnemyList = new ArrayList<>();
            //공격
            attack(tempMap, deathEnemyList);
            // 공격 당한 애 제외 (공격과 제외를 따로 하는것은, 동시에 같은 놈을 죽일 수도 있기 때문이다
            // 공격과 동시에 제외시키면 원래는 같은 적을 죽여야 하는데, 다른 적을 죽이게 된다.
            cnt += killEnemy(tempMap, deathEnemyList);

            // 적 이동
            updateMap(tempMap);

            // 죽인 적의 수 체크, 최대값과 비교
            max = Math.max(max, cnt);
        }

        numOfEnemy = tempNumOfEnemy;
    }

    private static int[][] getTempMap() {
        int[][] tempMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            tempMap[i] = map[i].clone();
        }
        return tempMap;
    }

    private static void attack(int[][] tempMap, List<int[]> deathEnemyList) {
        for (int i = 0; i < tempMap[0].length; i++) {
            if (tempMap[tempMap.length - 1][i] != 1) {
                continue;
            }

            // 궁수 별로 적 위치 확인.
            // bfs로 왼쪽, 위, 오른쪽 방향으로 탐색해야 한다. (문제 조건 : 거리가 가장 가까우면 왼쪽 적을 죽인다)
            deathEnemyList.add(findEnemy(tempMap, map.length - 1, i));
        }
    }

    private static int killEnemy(int[][] tempMap, List<int[]> deathEnemyList) {
        int cnt = 0;
        for (int[] ints : deathEnemyList) {
            if (ints.length == 0) {
                continue;
            }

            int row = ints[0];
            int col = ints[1];

            if (tempMap[row][col] != 0) {
                numOfEnemy--;
                tempMap[row][col] = 0;
                cnt++;
            }
        }

        return cnt;
    }

    private static int[] findEnemy(int[][] tempMap, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = poll[0] + dRow[i];
                int nextCol = poll[1] + dCol[i];
                int distance = poll[2] + 1;

                // 사정거리 밖이면 못 죽인다.
                if (distance > d) {
                    return new int[0];
                }

                if (!isRange(nextRow, nextCol)) {
                    continue;
                }

                // 적을 발견
                if (tempMap[nextRow][nextCol] > 1) {
                    return new int[]{nextRow, nextCol};
                }

                queue.add(new int[]{nextRow, nextCol, distance});
            }
        }

        // 사정거리 내에 적이 없으면 빈 배열 반환
        return new int[0];
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < map.length - 1 && nextCol < map[0].length;
    }

    private static void updateMap(int[][] tempMap) {
        for (int i = tempMap.length - 2; i >= 0; i--) {
            for (int j = 0; j < tempMap[0].length; j++) {
                if (tempMap[i][j] > 1) {
                    if (i == tempMap.length - 2) {
                        numOfEnemy--;
                    } else {
                        tempMap[i + 1][j] = tempMap[i][j];
                    }
                    tempMap[i][j] = 0;
                }
            }
        }
    }

    private static void initMap() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());
        int num = 2;

        // 궁수를 배치해야 하므로 행은 크기 1 증가
        map = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = num;
                    numOfEnemy++;
                }
            }
        }
    }
}
