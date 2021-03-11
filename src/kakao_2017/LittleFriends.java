package kakao_2017;

import java.util.*;

public class LittleFriends {

    private String answer = "IMPOSSIBLE";

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
            if (isRemovable(map, locations.get(i).row, locations.get(i).col, locations.get(i + 1).row, locations.get(i + 1).col)) {
                map[locations.get(i).row][locations.get(i).col] = map[locations.get(i + 1).row][locations.get(i + 1).col] = '.';
                visit[i/2] = true;
                if (dfs(map, locations, sb.append(locations.get(i).ch), success + 1, visit)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isRemovable(char[][] map, int startRow, int startCol, int endRow, int endCol) {
        int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] temp = new boolean[map.length][map[0].length];

        for (int[] move : moves) {
            int nextRow = startRow;
            int nextCol = startCol;
            while (true) {
                nextRow += move[0];
                nextCol += move[1];

                if (!isPossible(nextRow, nextCol, map.length, map[0].length)) {
                    break;
                }

                if (map[nextRow][nextCol] != '.' && !(nextRow == endRow &&  nextCol == endCol)) {
                    break;
                }

                temp[nextRow][nextCol] = true;
            }
        }

        for (int[] move : moves) {
            int nextRow = endRow;
            int nextCol = endCol;

            while (true) {
                if (temp[nextRow][nextCol]) {
                    return true;
                }

                nextRow += move[0];
                nextCol += move[1];

                if (!isPossible(nextRow, nextCol, map.length, map[0].length)) {
                    break;
                }

                if (map[nextRow][nextCol] != '.') {
                    break;
                }
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
