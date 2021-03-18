package baekjoon.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {

    private static Integer[][] dp;
    private static char[] a;
    private static char[] b;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String temp1 = bufferedReader.readLine();
        String temp2 = bufferedReader.readLine();

        a = temp1.toCharArray();
        b = temp2.toCharArray();

        dp = new Integer[a.length][b.length];

        System.out.println(lcs(a.length - 1, b.length - 1));
    }

    private static int lcs(int x, int y) {
        if (x == -1 || y == -1) {
            return 0;
        }
        if (dp[x][y] == null) {

            if (a[x] == b[y]) {
                dp[x][y] = lcs(x - 1, y - 1) + 1;
            } else {
                dp[x][y] = Math.max(lcs(x - 1, y), lcs(x, y - 1));
            }
        }

        return dp[x][y];
    }

}
