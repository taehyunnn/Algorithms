package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 비밀번호만들기 {

    private static int[][] dp;
    private static char[] firstArr;
    private static char[] secondArr;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String first = bufferedReader.readLine();
        String second = bufferedReader.readLine();


        dp = new int[first.length() + 1][second.length() + 1];
        firstArr = first.toCharArray();
        secondArr = second.toCharArray();

        makePasswordProcess();

        System.out.println(result.reverse().toString());
    }

    private static void makePasswordProcess() {
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (firstArr[i - 1] == secondArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        findLongPassword(firstArr.length - 1, secondArr.length - 1);
    }

    private static void findLongPassword(int len1, int len2) {
        if (len1 == -1 || len2 == -1) {
            return;
        }

        if (firstArr[len1] == secondArr[len2]) {
            result.append(firstArr[len1]);
            findLongPassword(len1 - 1, len2 - 1);
        } else {
            if (dp[len1][len2 + 1] <= dp[len1 + 1][len2]) {
                findLongPassword(len1, len2 - 1);
            } else {
                findLongPassword(len1 - 1, len2);
            }
        }
    }

    private static void lcs(int x, int y) {
        if (x == -1 || y == -1) {
            return;
        }

        if (firstArr[x] == secondArr[y]) {
            result.append(firstArr[x]);
            lcs(x - 1, y - 1);
        } else {
            lcs(x - 1, y);
            lcs(x, y - 1);
        }
    }
}
