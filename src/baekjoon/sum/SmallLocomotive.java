package baekjoon.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SmallLocomotive {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[] numOfPeople = new int[n];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numOfPeople[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int locomotive = Integer.parseInt(stringTokenizer.nextToken());

        int[] dp = new int[n];

        int temp = 0;
        for (int i = 0; i < locomotive * 3; i++) {
            temp += numOfPeople[i];
            dp[i] = temp;
        }

        for (int i = 3 * locomotive; i < n; i++) {
            temp = 0;
            for (int j = n - 1; j >= n - locomotive; j--) {
                temp += numOfPeople[j];
            }
            dp[i] = Math.max(dp[i - 1], dp[i - locomotive] + temp);
        }

        System.out.println(dp[dp.length - 1]);
    }
}
