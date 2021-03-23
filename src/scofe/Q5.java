package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q5 {

    private static final int[][] moves = new int[][]{{1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n, m;
        String[] split = input.split(" ");
        m = Integer.parseInt(split[0]);
        n = Integer.parseInt(split[1]);
        int minCount = n * m;

        char[][] board = new char[n][m];
        boolean[][] visit = new boolean[n][m];

        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'c') {
                    queue.add(new Node(i, j, 0));
                    visit[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.row == n - 1) {
                minCount = current.cntLeftRight;
                break;
            }

            for (int i = 0; i < moves.length; i++) {
                int[] move = moves[i];
                int nextRow = current.row + move[0];
                int nextCol = current.col + move[1];

                if (isMovable(nextRow, nextCol, board) && !visit[nextRow][nextCol]) {
                    visit[nextRow][nextCol] = true;
                    int cntLeftRight = current.cntLeftRight;
                    if (i != 0) {
                        cntLeftRight++;
                    }
                    queue.add(new Node(nextRow, nextCol, cntLeftRight));
                }
            }
        }


        if (minCount == n * m) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }


    private static boolean isMovable(int nextRow, int nextCol, char[][] board) {
        return nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && board[nextRow][nextCol] == '.';
    }

    static class Node {
        int row;
        int col;
        int cntLeftRight;

        Node(int row, int col, int cntLeftRight) {
            this.row = row;
            this.col = col;
            this.cntLeftRight = cntLeftRight;
        }
    }
}
