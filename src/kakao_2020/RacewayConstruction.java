package kakao_2020;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RacewayConstruction {

    private int[][] move = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};   // 상 하 좌 우
    private final int STRAIGHT = 100;
    private final int CORNER = 600;
    private int min = Integer.MAX_VALUE;

    public int solution(int[][] board) {

        int length = board.length;
        int[][][] visit = new int[length][length][2];
        visit[0][0][0] = visit[0][0][1] = 0;

        for (int[][] ints : visit) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, 999999999);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, -1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.row == length - 1 && current.col == length - 1) {
                min = Math.min(min, current.cost);
                continue;
            }

            move(current,queue,board,visit);
        }

        return min;
    }

    private void move(Node current, Queue<Node> queue, int[][] board, int[][][] visit) {
        for (int i = 0; i < move.length; i++) { // i: 0,1 일때는 상하, 2,3 일때는 좌우 방향 움직임
            int[] ints = move[i];
            int nextRow = current.row + ints[0];
            int nextCol = current.col + ints[1];

            if (!isPossible(board, nextRow, nextCol)) {
                continue;
            }

            int cost;
            int direction = i / 2;
            if (current.direction == direction || current.direction == -1) {
                cost = STRAIGHT;
            } else {
                cost = CORNER;
            }

            cost += current.cost;

            if (visit[nextRow][nextCol][direction] <= cost) {
                continue;
            }

            visit[nextRow][nextCol][direction] = cost;
            queue.add(new Node(nextRow, nextCol, cost, direction));
        }
    }



    private boolean isPossible(int[][] board, int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board.length && board[nextRow][nextCol] == 0;
    }

    static class Node {
        int row;
        int col;
        int cost;
        int direction; // 0 은 출발 , 1은 가로 , 2는 세로

        Node(int row, int col, int cost, int state) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.direction = state;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RacewayConstruction().solution(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 1, 0, 0}
        }));
    }
}
