import java.util.LinkedList;
import java.util.Queue;

public class Q2 {

    private final int TABLE = 'O';
    private final int PERSON = 'P';
    private final int MAX_DISTANCE = 2;

    private static final int WAITING_SIZE = 5;
    private int[] dRow = new int[]{0, 0, 1, -1};
    private int[] dCol = new int[]{1, -1, 0, 0};

    private char[][] room;

    public int[] solution(String[][] places) {
        int[] answer = new int[WAITING_SIZE];

        for (int i = 0, placesLength = places.length; i < placesLength; i++) {
            initRoom(places[i]);
            if (distanceCheck(room)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean distanceCheck(char[][] room) {
        for (int i = 0; i < WAITING_SIZE; i++) {
            for (int j = 0; j < WAITING_SIZE; j++) {
                if (room[i][j] == PERSON) {
                    if (!isSatisfiedDistance(i, j, room)) {
                        System.out.println();
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isSatisfiedDistance(int row, int col, char[][] room) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[WAITING_SIZE][WAITING_SIZE];
        queue.add(new int[]{row, col, 0});
        visit[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int currentRow = poll[0];
            int currentCol = poll[1];
            int distance = poll[2];

            if (distance == MAX_DISTANCE) {
                continue;
            }

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = currentRow + dRow[i];
                int nextCol = currentCol + dCol[i];

                if (!isRange(nextRow, nextCol)) continue;

                if (canVisit(nextRow, nextCol, visit, room)) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol, distance + 1});

                    if (room[nextRow][nextCol] == PERSON) {
                        System.out.println();
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean canVisit(int nextRow, int nextCol, boolean[][] visit, char[][] room) {
        return !visit[nextRow][nextCol] && (room[nextRow][nextCol] == TABLE || room[nextRow][nextCol] == PERSON);
    }

    private boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < WAITING_SIZE && nextCol < WAITING_SIZE;
    }


    private void initRoom(String[] place) {
        room = new char[WAITING_SIZE][WAITING_SIZE];

        for (int i = 0; i < place.length; i++) {
            String s = place[i];
            for (int j = 0; j < s.length(); j++) {
                room[i][j] = s.charAt(j);
            }
        }
    }

    public static void main(String[] args) {
        int[] solution = new Q2().solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {
                "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});

        for (int i : solution) {
            System.out.print(i);
        }
    }
}
