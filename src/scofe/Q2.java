package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input);

        int[] path = new int[n];
        long[] dp = new long[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            path[i] = s.charAt(i) - '0';
        }

        dp[0] = 1;
        dp[1] = path[1] == 0 ? 0 : 1;

        for (int i = 2; i < n; i++) {
            if (path[i] == 0) {
                continue;
            }

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n-1]);
    }
}
