package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LCS3 {

    private static int[][][] dp;
    private static char[] a;
    private static char[] b;
    private static char[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        a = bufferedReader.readLine().toCharArray();
        b = bufferedReader.readLine().toCharArray();
        c = bufferedReader.readLine().toCharArray();

        dp = new int[a.length][b.length][c.length];

        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }

        System.out.println(lcs(a.length - 1, b.length - 1, c.length - 1));
        System.out.println();
    }

    private static int lcs(int x, int y, int z) {
        if (x == -1 || y == -1 || z == -1) {
            return 0;
        }

        if (dp[x][y][z] == -1) {
            if (a[x] == b[y] && b[y] == c[z]) {
                dp[x][y][z] = lcs(x - 1, y - 1, z - 1) + 1;
            } else {
                int temp1 = Math.max(lcs(x, y - 1, z - 1), lcs(x - 1, y, z - 1));
                int temp2 = Math.max(lcs(x - 1, y - 1, z), lcs(x, y, z-1));
                int temp3 = Math.max(lcs(x, y - 1, z), lcs(x - 1, y, z));

                dp[x][y][z] = Math.max(Math.max(temp1, temp2), temp3);
            }
        }
        return dp[x][y][z];
    }
}
