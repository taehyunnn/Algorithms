package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BeadEscape {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        int[] directionRow = {0,0,1,-1};
        int[] directionCol = {1,-1,0,0};

        int startRedRow = 0, startRedCol =0, startBlueRow =0, startBlueCol=0;
        boolean[][][][] visit = new boolean[n][m][n][m];
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (map[i][j]) {
                    case 'R':
                        startRedRow = i;
                        startRedCol = j;
                        break;
                    case 'B':
                        startBlueRow = i;
                        startBlueCol = j;
                        break;
                }
            }
        }

        queue.add(new Node(startBlueRow,startBlueCol,startRedRow,startRedCol,0));
        visit[startBlueRow][startBlueCol][startRedRow][startRedCol] = true;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.depth >= 10) {
                System.out.println(-1);
                return;
            }

            // 동서남북으로 이동
            for (int i = 0; i < 4; i++) {

                boolean flag = false;
                int nextBlueRow = currentNode.blueRow;
                int nextBlueCol = currentNode.blueCol;
                int nextRedRow = currentNode.redRow;
                int nextRedCol = currentNode.redCol;

                // 파랑 먼저 이동
                while (map[nextBlueRow + directionRow[i]][nextBlueCol + directionCol[i]] != '#') {
                    nextBlueRow += directionRow[i];
                    nextBlueCol += directionCol[i];

                    // 파랑이 구멍에 빠지면 그건 제외
                    if (map[nextBlueRow][nextBlueCol] == 'O') {
                        flag = true;
                    }
                }

                if(flag) continue;

                // 빨강 이동
                while (map[nextRedRow + directionRow[i]][nextRedCol + directionCol[i]] != '#') {
                    nextRedRow += directionRow[i];
                    nextRedCol += directionCol[i];

                    // 빨강이 구멍에 빠지면 탐색 종료
                    if (map[nextRedRow][nextRedCol] == 'O') {
                        System.out.println(currentNode.depth +1);
                        return;
                    }
                }

                // 파랑, 빨강 위치가 같으면 위치 조정
                if(nextBlueRow == nextRedRow && nextBlueCol == nextRedCol){
                    switch (i){ // i : 0-동 , 1-서 , 2-남 , 3-북
                        case 0:
                            if(currentNode.redCol > currentNode.blueCol){
                                nextBlueCol--;
                            } else {
                                nextRedCol--;
                            }
                            break;
                        case 1:
                            if(currentNode.redCol > currentNode.blueCol){
                                nextRedCol ++;
                            } else {
                                nextBlueCol++;
                            }
                            break;
                        case 2:
                            if (currentNode.redRow > currentNode.blueRow) {
                                nextBlueRow--;
                            } else {
                                nextRedRow--;
                            }
                            break;
                        case 3:
                            if (currentNode.redRow > currentNode.blueRow) {
                                nextRedRow++;
                            } else {
                                nextBlueRow++;
                            }
                            break;
                    }
                }

                // 최종 위치가 방문한 적 없으면 큐에 삽입
                if (!visit[nextBlueRow][nextBlueCol][nextRedRow][nextRedCol]) {
                    visit[nextBlueRow][nextBlueCol][nextRedRow][nextRedCol] = true;
                    queue.add(new Node(nextBlueRow, nextBlueCol, nextRedRow, nextRedCol, currentNode.depth + 1));
                }
            }
        }
        System.out.println(-1);
    }

    static class Node{
        int blueRow;
        int blueCol;
        int redRow;
        int redCol;
        int depth;

        public Node(int blueRow, int blueCol, int redRow, int redCol, int depth) {
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.redRow = redRow;
            this.redCol = redCol;
            this.depth = depth;
        }
    }
}
