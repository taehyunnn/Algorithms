package kakao_2017;

import java.util.*;

public class LittleFriends {

    private String answer = "IMPOSSIBLE";
    private final int START = -9;

    /**
     * @param m     배열의 크기
     * @param n     각각의 원소는 n글자의 문자열로 구성 : . : 빈칸 , * : 막힌 칸 , A-Z : 타일
     * @param board 배치된 타일의 정보
     */
    public String solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        List<Node> locations = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = board[i].charAt(j);
                map[i][j] = ch;

                if (ch == '.' || ch == '*') {
                    continue;
                }
                locations.add(new Node(ch, i, j));
            }
        }

        Collections.sort(locations);

        dfs(map, locations, new StringBuilder(), 0, new boolean[26]);

        return answer;
    }

    private boolean dfs(char[][] map, List<Node> locations, StringBuilder sb, int success, boolean[] visit) {
        if (success == locations.size() / 2) {
            answer = sb.toString();
            return true;
        }

        for (int i = 0; i < locations.size(); i += 2) {
            if (visit[i/2]) {
                continue;
            }
            if (isRemovable(map, locations.get(i).row, locations.get(i).col, locations.get(i + 1).row, locations.get(i + 1).col, START, false)) {
                map[locations.get(i).row][locations.get(i).col] = map[locations.get(i + 1).row][locations.get(i + 1).col] = '.';
                visit[i/2] = true;
                if (dfs(map, locations, sb.append(locations.get(i).ch), success + 1, visit)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isRemovable(char[][] map, int startRow, int startCol, int endRow, int endCol, int direction, boolean turn) {
        int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < 4; i++) {   // 동 서 남 북
            boolean turnTemp = turn;

            if (turnTemp) {
                if (direction != i) {
                    continue;
                }
            }

            // 이미 한 번 턴 했을 때
            int nextRow = startRow + moves[i][0];
            int nextCol = startCol + moves[i][1];

            if (nextRow == endRow && nextCol == endCol) {
                return true;
            }

            if (!isPossible(nextRow, nextCol, map.length, map[0].length)) {
                continue;
            }

            if (map[nextRow][nextCol] != '.') {
                continue;
            }

            // 시작도 아니고 방향도 다르면 꺾는거
            if (direction != START && direction != i) {
                // 반대방향은 갈 필요 없다.
                if (direction / 2 == i / 2) {
                    continue;
                }
                turnTemp = true;
            }

            if (isRemovable(map, nextRow, nextCol, endRow, endCol, i, turnTemp)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPossible(int nextRow, int nextCol, int rowLen, int colLen) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen;
    }


    static class Node implements Comparable<Node> {
        char ch;
        int row;
        int col;

        Node(char ch, int row, int col) {
            this.ch = ch;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            return ch - o.ch;
        }
    }

    public static void main(String[] args) {
//        System.out.println(-2/2);
//        System.out.println(new LittleFriends().solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        System.out.println(new LittleFriends().solution(5, 5, new String[]{"FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE"}));
//        System.out.println(new LittleFriends().solution(2, 4, new String[]{"NRYN", "ARYA"}));
//        System.out.println(new LittleFriends().solution(2, 2, new String[]{"AB", "BA"}));
    }
}
