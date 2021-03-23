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
        int[] numOfPeople = new int[n + 1]; // 객차 별 인원 수
        int[] sumOfPeople = new int[n + 1]; // 1~n 객차까지의 구간 합
        int[][] dp = new int[4][n + 1]; // dp[i][j] : i대의 기관차로 1~n번의 객차를 끌 때 최대값

        int temp = 0;

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            numOfPeople[i] = Integer.parseInt(stringTokenizer.nextToken());
            temp += numOfPeople[i];
            sumOfPeople[i] = temp;
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int locomotive = Integer.parseInt(stringTokenizer.nextToken());

        // dp 실행
        for (int i = 1; i <= 3; i++) {
            for (int j = i * locomotive; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - locomotive] + sumOfPeople[j] - sumOfPeople[j - locomotive]);
            }
        }

        System.out.println(dp[3][n]);
    }
}
