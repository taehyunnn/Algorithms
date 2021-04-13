package programmers.kakao_2020;

import java.util.LinkedList;
import java.util.Queue;

public class BlockMove {

    private int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // 우 하 좌 상
    private int[][] rotations = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}}; // 우상, 우하, 좌하, 좌상
    private boolean[][][] visit;

    public int solution(int[][] board) {
        int answer = 0;
        int length = board.length;
        visit = new boolean[length][length][2];
        Queue<Robot> queue = new LinkedList<>();

        visit[0][0][0] = true;
        queue.add(new Robot(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Robot current = queue.poll();

            if (checkArrival(current, length - 1)) {
                answer = current.time;
                break;
            }

            int row = current.row;
            int col = current.col;
            int otherRow = current.getOtherRow();
            int otherCol = current.getOtherCol();

            // 상하좌우 이동
            move(board, queue, current, row, col, otherRow, otherCol);

            // row, col 중심 회전
            rotateFromOne(board, queue, current.direction, current.time, row, col);
            // otherRow, otherCol 중심 회전
            rotateFromOne(board, queue, (current.direction + 2) % 4, current.time, otherRow, otherCol);
        }
        return answer;
    }

    private void rotateFromOne(int[][] board, Queue<Robot> queue, int direction, int time, int row, int col) {
        for (int i = 0; i < 2; i++) {
            int changedDir = (direction + 3 + i * 2) % 4;

            int rotatedRow = row + moves[changedDir][0];
            int rotatedCol = col + moves[changedDir][1];

            int diagonalRow = row + rotations[(direction + i) % 4][0];
            int diagonalCol = col + rotations[(direction + i) % 4][1];

            if (!movable(diagonalRow, diagonalCol, board) || !movable(rotatedRow, rotatedCol, board)) {
                continue;
            }
            if (isVisit(row, col, changedDir)) {
                continue;
            }

            markVisit(row, col, changedDir);
            queue.add(new Robot(row, col, changedDir, time + 1));
        }
    }

    private void move(int[][] board, Queue<Robot> queue, Robot current, int row, int col, int otherRow, int otherCol) {
        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];
            int nextOtherRow = otherRow + move[0];
            int nextOtherCol = otherCol + move[1];


            if (!movable(nextRow, nextCol, board) || !movable(nextOtherRow, nextOtherCol, board)) {
                continue;
            }
            if (isVisit(nextRow, nextCol, current.direction)) {
                continue;
            }

            markVisit(nextRow, nextCol, current.direction);
            queue.add(new Robot(nextRow, nextCol, current.direction, current.time + 1));
        }
    }

    private boolean checkArrival(Robot current, int length) {
        return (current.row == length && current.col == length) || (current.getOtherRow() == length && current.getOtherCol() == length);
    }

    private void markVisit(int row, int col, int direction) {
        if (direction > 1) {
            row = row + moves[direction][0];
            col = col + moves[direction][1];
        }
        visit[row][col][direction % 2] = true;
    }

    private boolean movable(int nextRow, int nextCol, int[][] board) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board.length && board[nextRow][nextCol] == 0;
    }

    private boolean isVisit(int row, int col, int direction) {
        if (direction > 1) {
            row = row + moves[direction][0];
            col = col + moves[direction][1];
        }
        return visit[row][col][direction % 2];
    }

    class Robot {
        int row;
        int col;
        int direction;
        int time;

        Robot(int row, int col, int direction, int time) {
            this.row = row;
            this.col = col;
            this.direction = direction; // 0이 오른쪽 방향 , 1은 아래 방향
            this.time = time;
        }

        int getOtherRow() {
            return row + moves[direction][0];
        }

        int getOtherCol() {
            return col + moves[direction][1];
        }
    }

    public static void main(String[] args) {
        new BlockMove().solution(new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}});
    }
}
