package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TwoCoins {

    private static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static boolean[][][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n+2][m+2];
        visit = new boolean[n+2][m+2][n+2][m+2];

        Coins coins = new Coins();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            String s = st.nextToken();
            for (int j = 1; j <= m; j++) {
                board[i][j] = s.charAt(j-1);
                if (board[i][j] == 'o') {
                    coins.add(new Coin(i, j));
                }
            }
        }

        int answer = getAnswer(board, n, m, coins);

        System.out.println(answer > 10 ? -1 : answer);
    }

    private static int getAnswer(char[][] board, int n, int m, Coins coins) {
        Queue<Coins> queue = new LinkedList<>();
        queue.add(coins);

        while (!queue.isEmpty()) {
            Coins current = queue.poll();

            if (current.level > 10) {
                return -1;
            }

            int cnt = current.insideCnt(n, m);
            if (cnt == 1) {
                return current.level;
            } else if (cnt > 1) {
                continue;
            }

            for (int[] move : moves) {
                Coins temp = new Coins(current);
                for (Coin coin : temp.coins) {
                    int nextRow = coin.row + move[0];
                    int nextCol = coin.col + move[1];

                    if (board[nextRow][nextCol] != '#') {
                        coin.row = nextRow;
                        coin.col = nextCol;
                    }
                }
                if (visitCheck(temp)) {
                    continue;
                }
                queue.add(temp);
            }
        }

        return -1;
    }

    private static boolean visitCheck(Coins temp) {
        int r1,c1,r2,c2;

        r1 = temp.coins.get(0).row;
        c1 = temp.coins.get(0).col;
        r2 = temp.coins.get(1).row;
        c2 = temp.coins.get(1).col;

        if (visit[r1][c1][r2][c2]) {
            return true;
        }
        visit[r1][c1][r2][c2] = true;
        return false;
    }

    static class Coins {
        List<Coin> coins;
        int level;

        Coins() {
            coins = new ArrayList<>();
        }

        Coins(Coins c){
            this();
            for (Coin coin : c.coins) {
                this.coins.add(new Coin(coin));
            }
            this.level = c.level +1;
        }

        void add(Coin coin) {
            coins.add(coin);
        }

        int insideCnt(int n, int m) {
            int cnt = 0;
            for (Coin coin : coins) {
                if (!coin.isValid(n, m)) {
                    cnt++;
                }
            }
            return cnt;
        }
    }

    static class Coin {
        int row;
        int col;

        Coin(int row, int col) {
            this.row = row;
            this.col = col;
        }

        Coin(Coin coin) {
            this.row = coin.row;
            this.col = coin.col;
        }

        boolean isValid(int rowLen, int colLen) {
            return row > 0 && col > 0 && row <= rowLen && col <= colLen;
        }
    }
}
