package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cheese {

    private static int n, m;

    private static int[][] board;
    private static int[][] contact;
    private static boolean[][] visit;

    private static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static final int BLANK = 0;
    private static final int CHEESE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        board = new int[n][m];

        int numOfCheese = 0;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (board[i][j] == CHEESE) {
                    numOfCheese++;
                }
            }
        }

        System.out.println(cheeseProcess(numOfCheese));
    }

    private static int cheeseProcess(int numOfCheese) {
        if (numOfCheese == 0) {
            return 0;
        }

        contact = new int[n][m];
        visit = new boolean[n][m];
        calcContactDegree(0, 0);
        int removeCnt = searchOutsideCheese();
        return cheeseProcess(numOfCheese - removeCnt) + 1;
    }

    private static int searchOutsideCheese() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == CHEESE && contact[i][j] >= 2) {
                    board[i][j] = BLANK;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 외부 공기를 기준으로 확인
    private static void calcContactDegree(int row, int col) {
        if (!isRange(row, col) || visit[row][col] || board[row][col] == 1) {
            return;
        }

        visit[row][col] = true;
        addContact(row, col);
        calcContactDegree(row + 1, col);
        calcContactDegree(row - 1, col);
        calcContactDegree(row, col - 1);
        calcContactDegree(row, col + 1);
    }

    // 외부 공기좌표 (row,col) 기준으로 상하좌우에 치즈가 있으면 해당 치즈의 contact값 +1
    private static void addContact(int row, int col) {
        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];
            if (isRange(nextRow, nextCol) && board[nextRow][nextCol] == 1) {
                contact[nextRow][nextCol]++;
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m;
    }
}
