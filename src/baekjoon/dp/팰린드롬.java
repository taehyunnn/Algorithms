package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬 {

    private static boolean[][] dp;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int length = s.length();

        dp = new boolean[length + 1][length + 1];
        result = new int[length + 1];

        makeDp(s);

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[i][j]) {
                    if (result[i] == 0 || result[i] > result[j - 1] + 1) {
                        result[i] = result[j - 1] + 1;
                    }
                }
            }
        }

        System.out.println(result[length]);
    }

    private static void makeDp(String s) {
        // 길이가 1이면 팰린드롬
        int length = dp.length;
        for (int i = 1; i < length; i++) {
            dp[i][i] = true;
        }

        // 길이가 2이면 좌우 비교
        for (int i = 1; i < length - 1; i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                dp[i][i + 1] = dp[i + 1][i] = true;
            }
        }

        // 길이가 3 이상이면 좌우 비교 and 내부가 팰린드롬인지 확인
        for (int i = 2; i < length; i++) {
            for (int j = 1; j + i < length; j++) {
                if (s.charAt(j-1) == s.charAt(j + i-1) && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = dp[j + i][j] = true;
                }
            }
        }
    }

}
