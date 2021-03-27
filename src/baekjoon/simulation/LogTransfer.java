package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class LogTransfer {

    private static int n;
    private static char[][] board;
    private static boolean[][][] visit; //

    private static int[] dRow = new int[]{1, -1, 0, 0};
    private static int[] dCol = new int[]{0, 0, 1, -1}; // 0,1 은 수직 2,3은 수평

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        board = new char[n][n];
        visit = new boolean[n][n][2];   // 마지막이 [0]이면 수직 [1]이면 수평


        Log startLog = new Log();
        Log endLog = new Log();
        init(bufferedReader, startLog, endLog);

        start(startLog, endLog);
    }

    private static void start(Log startLog, Log endLog) {
        Queue<Log> queue = new LinkedList<>();
        queue.add(startLog);
        visit[startLog.row][startLog.col][startLog.dir] = true;

        boolean result = true;

        while (!queue.isEmpty()) {
            Log current = queue.poll();

            if (current.equals(endLog)) {
                result = false;
                System.out.println(current.moveCnt);
                break;
            }

            for (int i = 0; i < 4; i++) {   // 이동

                int nextRow = current.row + dRow[i];
                int nextCol = current.col + dCol[i];

                if (isMovable(nextRow, nextCol, current.dir) && !visit[nextRow][nextCol][current.dir]) {
                    queue.add(new Log(nextRow, nextCol, current.dir == 1, current.moveCnt + 1));
                    visit[nextRow][nextCol][current.dir] = true;
                }
            }

            // 회전
            int nextDir = 1 - current.dir;
            if (isRotatable(current.row, current.col) && !visit[current.row][current.col][nextDir]) {
                queue.add(new Log(current.row, current.col, nextDir == 1, current.moveCnt + 1));
                visit[current.row][current.col][nextDir] = true;
            }
        }
        if (result) {
            System.out.println(0);
        }
    }

    private static boolean isRotatable(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || j < 0 || i >= n || j >= n || board[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isMovable(int row, int col, int dir) {
        if (row < 0 || col < 0 || row >= n || col >= n || board[row][col] == '1') {
            return false;
        }
        int dirTemp = dir * 2;

        for (int i = dirTemp; i <= dirTemp + 1; i++) {
            int edgeRow = row + dRow[i];
            int edgeCol = col + dCol[i];

            if (edgeRow < 0 || edgeCol < 0 || edgeRow >= n || edgeCol >= n || board[edgeRow][edgeCol] == '1') {
                return false;
            }
        }
        return true;
    }

    private static void init(BufferedReader bufferedReader, Log startLog, Log endLog) throws IOException {
        boolean startFlag = false;
        int startCount = 0;
        boolean endFlag = false;
        int endCount = 0;

        for (int i = 0; i < n; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);

                // 시작 통나무 찾기
                if (board[i][j] == 'B') {
                    startCount++;
                    if (startCount == 2) {
                        startLog.row = i;
                        startLog.col = j;
                        startLog.dir = startFlag ? 1 : 0;
                    }
                    startFlag = true;
                }

                // 종료 통나무 찾기
                if (board[i][j] == 'E') {
                    endCount++;
                    if (endCount == 2) {
                        endLog.row = i;
                        endLog.col = j;
                        endLog.dir = endFlag ? 1 : 0;
                    }
                    endFlag = true;
                }
            }
            startFlag = false;
            endFlag = false;
        }
    }

    static class Log {
        int row;
        int col;
        int dir;    // 0 면 수직, 1이면 수평
        int moveCnt;

        Log() {
        }

        Log(int row, int col, boolean dir, int moveCnt) {
            this.row = row;
            this.col = col;
            this.dir = dir ? 1 : 0;
            this.moveCnt = moveCnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Log log = (Log) o;
            return row == log.row &&
                    col == log.col &&
                    dir == log.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, dir);
        }

        @Override
        public String toString() {
            return "Log{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    ", moveCnt=" + moveCnt +
                    '}';
        }
    }
}
