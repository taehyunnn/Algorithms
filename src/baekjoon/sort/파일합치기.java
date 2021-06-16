package baekjoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파일합치기 {

    private static int[] files;
    private static int size;
    private static int[][] dp;
    private static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int cnt = Integer.parseInt(stringTokenizer.nextToken());


        for (int i = 0; i < cnt; i++) {
            size = Integer.parseInt(bufferedReader.readLine());

            dp = new int[size][size];
            sum = new int[size];
            files = new int[size];

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < size; j++) {
                files[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int[] ints : dp) {
                Arrays.fill(ints, Integer.MAX_VALUE);
            }

            calc();
            System.out.println(dp[0][size - 1]);
        }
    }

    private static void calc() {
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 0;
        }

        sum[0] = files[0];
        for (int i = 0; i < dp.length - 1; i++) {
            dp[i][i + 1] = files[i] + files[i + 1];
            sum[i + 1] = sum[i] + files[i + 1];
        }

        // i : 두 파일간의 거리 차이
        for (int i = 2; i < size; i++) {
            // j : 파일 인덱스 위치
            for (int j = 0; j + i < size; j++) {

                for (int k = j; k + 1 <= j + i; k++) {
                    dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][j + i]);
                }
                dp[j][j + i] += sum[j + i] - sum[j] + files[j];
            }
        }
    }

}
