package scofe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q4 {

    public static final int numOfContents = 5;
    private static List<Content> neverSeenList = new ArrayList<>();
    private static List<Content> partialSeenList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        Map<Character, String> preference = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            preference.put((char) (i + 'A'), split[1]);
        }

        int n, m;

        split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        char[][] board = new char[2 * n][m];

        init(br, n, m, board);
        calc(board, n, m);

        sort(preference, neverSeenList);
        sort(preference, partialSeenList);

        for (Content content : neverSeenList) {
            System.out.println(content.genre + " "+preference.get(content.genre)+" " + content.row + " " + content.col);
        }

        for (Content content : partialSeenList) {
            System.out.println(content.genre + " "+preference.get(content.genre)+" " + content.row + " " + content.col);
        }

    }

    private static void sort(Map<Character, String> preference, List<Content> list) {
        list.sort((o1, o2) -> {
            if (o1.genre == o2.genre) {
                if (o1.row == o2.row) {
                    return o1.col - o2.col;
                }
                return o1.row - o2.row;
            }
            return preference.get(o2.genre).compareTo(preference.get(o1.genre));
        });
    }

    private static void calc(char[][] board, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = board[i][j];
                if (c == 'Y') { // 열람 X
                    neverSeenList.add(new Content(i, j, board[i + n][j]));
                } else if (c == 'O') {  // 일부 열람
                    partialSeenList.add(new Content(i, j, board[i + n][j]));
                }
            }
        }
    }

    private static void init(BufferedReader br, int n, int m, char[][] board) throws IOException {
        for (int i = 0; i < n * 2; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
    }

    static class Content {
        int row;
        int col;
        char genre;

        Content(int row, int col, char genre) {
            this.row = row;
            this.col = col;
            this.genre = genre;
        }
    }

}
