package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q5 {

    private static final int[][] moves = new int[][]{{1, 0}, {0, -1}, {0, 1}};
    private static int minCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n, m;
        String[] split = input.split(" ");
        m = Integer.parseInt(split[0]);
        n = Integer.parseInt(split[1]);
        minCount = n * m;

        char[][] board = new char[n][m];
        int[][] visit = new int[n][m];
        for (int[] ints : visit) {
            Arrays.fill(ints, n * m);
        }

        List<int[]> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'c') {
                    nodes.add(new int[]{i, j});
                }
            }
        }

        for (int[] node : nodes) {       // 시작 위치 될 수 있는 애들 꺼내서 확인
            visit[node[0]][node[1]] = 0;
            dfs(node[0], node[1], board, visit);
        }

        if (minCount == n * m) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }

    private static void dfs(int row, int col, char[][] board, int[][] visit) {
        if (row + 1 == board.length) {
            minCount = Math.min(minCount, visit[row][col]);
            return;
        }

        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isMovable(nextRow, nextCol, board)) {
                int moveCount = visit[row][col];
                if (i != 0) {
                    moveCount++;
                }

                if (moveCount < visit[nextRow][nextCol]) {  // (nextRow, nextCol) 을 가기 위한 좌우 움직임 횟수가 기존보다 크거나 같으면 할 필요 없다.
                    visit[nextRow][nextCol] = moveCount;
                    dfs(nextRow, nextCol, board, visit);
                }
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol, char[][] board) {
        return nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && board[nextRow][nextCol] == '.';
    }
}
