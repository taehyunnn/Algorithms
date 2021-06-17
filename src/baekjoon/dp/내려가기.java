package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기 {

    private static int[][] map;
    private static int[] minDp;
    private static int[] maxDp;
    private static int n;

    public static void main(String[] args) throws IOException {
        init();

        // 첫 째줄 초기화
        for (int i = 0; i < 3; i++) {
            minDp[i] = maxDp[i] = map[0][i];
        }

        // 2번째 줄 부터 n번 째 줄까지 dp 점화식 이용한 풀이
        for (int i = 1; i < n; i++) {
            int preValue0 = minDp[0], preValue2 = minDp[2];
            minDp[0] = Math.min(minDp[0], minDp[1]) + map[i][0];
            minDp[2] = Math.min(minDp[1], minDp[2]) + map[i][2];
            minDp[1] = Math.min(Math.min(preValue0, preValue2), minDp[1]) + map[i][1];

            preValue0 = maxDp[0]; preValue2 = maxDp[2];
            maxDp[0] = Math.max(maxDp[0], maxDp[1]) + map[i][0];
            maxDp[2] = Math.max(maxDp[1], maxDp[2]) + map[i][2];
            maxDp[1] = Math.max(Math.max(preValue0, preValue2), maxDp[1]) + map[i][1];
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[i]);
            max = Math.max(max, maxDp[i]);
        }

        System.out.println(max + " " + min);
    }


    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][3];
        minDp = new int[3];
        maxDp = new int[3];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }
}
