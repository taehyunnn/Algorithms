package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JewelryPicking {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[] valueOfJewelry = new int[n + 1];
        int[] sumOfJewelryValue = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            valueOfJewelry[i] = Integer.parseInt(stringTokenizer.nextToken());
            sumOfJewelryValue[i] = sumOfJewelryValue[i - 1] + valueOfJewelry[i];
        }

        dp[m] = sumOfJewelryValue[m];

        int endIndex = 0;

        for (int i = m + 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            int temp = -200000000;
            for (int j = endIndex; j <= i - m; j++) {
                if (temp < sumOfJewelryValue[i] - sumOfJewelryValue[j]) {
                    temp = sumOfJewelryValue[i] - sumOfJewelryValue[j];
                    endIndex = j;
                }
            }
            dp[i] = Math.max(dp[i], temp);
        }


        System.out.println(Math.max(dp[n], 0));
    }
}
