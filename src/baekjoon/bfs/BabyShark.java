package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabyShark {

    public static final int[][] moves = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};  // 상 좌 우 하

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());

        int[][] board = new int[n][n];
        Shark startShark = new Shark();
        Queue<Shark> queue = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (board[i][j] == 9) {
                    startShark.row = i;
                    startShark.col = j;
                    startShark.weight = 2;
                    queue.add(startShark);
                    visit[i][j] = true;
                    board[i][j] = 0;
                }
            }
        }

        int time = 0;
        List<Shark> locations = new ArrayList<>();
        while (!queue.isEmpty()) {
            Shark currentShark = queue.poll();

            for (int[] move : moves) {  // 동서남북 이동
                int nextRow = currentShark.row + move[0];
                int nextCol = currentShark.col + move[1];

                if (isPossible(nextRow, nextCol, board, currentShark) && !visit[nextRow][nextCol]) {    // 이동 가능 하면
                    Shark newShark = new Shark(nextRow, nextCol, currentShark.weight, currentShark.numOfTimeEaten, currentShark.moveCount + 1);
                    visit[nextRow][nextCol] = true;

                    if (board[nextRow][nextCol] > 0 && board[nextRow][nextCol] < currentShark.weight) {    // 먹을 수 있으면
                        newShark.eat();
                        locations.add(newShark);
                    }
                    queue.add(newShark);
                }
            }

            if (queue.isEmpty() && locations.size() != 0) {
                locations.sort((o1, o2) -> {
                    if (o1.moveCount == o2.moveCount) {
                        if (o1.row == o2.row) {
                            return o1.col - o2.col;
                        }
                        return o1.row - o2.row;
                    }
                    return o1.moveCount - o2.moveCount;
                });
                Shark nextShark = locations.get(0);
                time = nextShark.moveCount;
                locations.clear();
                queue.clear();
                queue.add(nextShark);
                clearVisit(visit);
                board[nextShark.row][nextShark.col] = 0;
                visit[nextShark.row][nextShark.col] = true;
            }
        }
        System.out.println(time);
    }

    private static void clearVisit(boolean[][] visit) {
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
    }

    private static boolean isPossible(int nextRow, int nextCol, int[][] board, Shark currentShark) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length && board[nextRow][nextCol] <= currentShark.weight;
    }

    static class Shark {
        int row;
        int col;
        int weight;
        int numOfTimeEaten;
        int moveCount;

        Shark() {

        }

        public Shark(int row, int col, int weight, int numOfTimeEaten, int moveCount) {
            this.row = row;
            this.col = col;
            this.weight = weight;
            this.numOfTimeEaten = numOfTimeEaten;
            this.moveCount = moveCount;
        }

        void eat() {
            numOfTimeEaten++;
            if (numOfTimeEaten == weight) {
                weight++;
                numOfTimeEaten = 0;
            }
        }
    }
}
