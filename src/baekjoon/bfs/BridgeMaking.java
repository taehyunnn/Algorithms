package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BridgeMaking {

    private static int[][] map; // 나라 지도
    private static int[][] moveCount;   // 섬들을 확장시킬때 얼마나 움직였는지 기록
    private static int N;
    private static int min = Integer.MAX_VALUE;
    private static int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        moveCount = new int[N][N];

        // 맵 초기화
        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(arr[j]);
        }

        // 섬 labeling 2번 섬, 3번 섬 ...
        int islandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = islandNum;
                    checkSameIsland(i, j, islandNum++);
                }
            }
        }

        // 섬을 확장시키기 위해 모든 섬의 가장자리들을 큐에 삽입
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isEdge(i, j)) {
                    queue.add(new Node(i, j, 0, map[i][j]));
                }
            }
        }

        // 섬 확장 시키면서 최단거리 계산
        constructBridge(queue);
        System.out.println(min);

    }

    private static void constructBridge(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int[] move : moves) {  // 동서남북 이동
                int nextRow = current.row + move[0];
                int nextCol = current.col + move[1];

                if (isRange(nextRow, nextCol)) {    // 맵 내부면서
                    if (map[nextRow][nextCol] == 0) {    // 물이면
                        map[nextRow][nextCol] = current.islandNum;  // 섬 확장
                        moveCount[nextRow][nextCol] = current.moveCount + 1;    // 그 지점까지 움직인 거리 기록
                        queue.add(new Node(nextRow, nextCol, current.moveCount + 1, current.islandNum));    // 다시 큐에 삽입
                    } else if (map[nextRow][nextCol] != current.islandNum) {    // 다른 섬이면
                        min  = Math.min(min, moveCount[nextRow][nextCol] + current.moveCount);  // 최소 거리 계산
//                        return; // 이 부분이 없으면 성공하는데 있으면 실패함. queue에서 뽑으니까 가장 최초로 만나는 거리가 당연히 최소값이라 생각했는데..
                    }
                }
            }
        }
    }


    /**
     * 섬의 가장 자리면 true 아니면 false
     */
    private static boolean isEdge(int row, int col) {
        if (map[row][col] == 0) {
            return false;
        }

        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isRange(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *   같은 섬 labeling
     */
    private static void checkSameIsland(int row, int col, int islandNum) {

        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isRange(nextRow, nextCol) && map[nextRow][nextCol] == 1) {
                map[nextRow][nextCol] = islandNum;
                checkSameIsland(nextRow, nextCol, islandNum);
            }
        }
    }

    /**
     *  맵 내부면 true, 아니면 false
     */
    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < N;
    }


    static class Node {
        int row;
        int col;
        int moveCount;  // 현재까지 움직인 count
        int islandNum;  // 섬 번호

        Node(int row, int col, int moveCount, int islandNum) {
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
            this.islandNum = islandNum;
        }
    }

}
