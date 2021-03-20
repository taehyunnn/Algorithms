import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
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
        Integer[][] visit = new Integer[n][m];

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
    }
}