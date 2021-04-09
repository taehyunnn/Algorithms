package kakao_2017;

import java.util.LinkedList;
import java.util.Queue;

public class ColoringBook {

    private static int[] dRow = new int[]{0, 0, 1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j] || picture[i][j] == 0) {
                    continue;
                }
                numberOfArea++;
                int currentSize = bfs(m, n, picture, visit, i, j);

                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, currentSize);
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int m, int n, int[][] picture, boolean[][] visit, int i, int j) {
        int currentSize = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        visit[i][j] = true;
        int currentValue = picture[i][j];

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < dRow.length; k++) {
                int nextRow = poll[0] + dRow[k];
                int nextCol = poll[1] + dCol[k];

                if (isRange(nextRow, nextCol, m, n) && !visit[nextRow][nextCol] && picture[nextRow][nextCol] == currentValue) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                    currentSize++;
                }
            }
        }
        return currentSize;
    }

    private boolean isRange(int nextRow, int nextCol, int m, int n) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n;
    }

    public static void main(String[] args) {
        int[] solution = new ColoringBook().solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}
