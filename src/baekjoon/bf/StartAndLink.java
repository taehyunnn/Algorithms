package baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StartAndLink {

    private static int[][] board;
    private static boolean[] combine;
    private static int[] nums;
    private static int n;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());

        board = new int[n][n];
        combine = new boolean[n];
        nums = new int[n/2];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        bf(-1, 0);
        System.out.println(min);
    }

    private static void bf(int index, int depth) {
        if (depth == n / 2) {
            for (int num : nums) {
                combine[num] = true;
            }
            int sum1 = sum();

            for (int i = 0; i < combine.length; i++) {
                combine[i] = !combine[i];
            }

            int sum2 = sum();

            int gap = Math.abs(sum1 - sum2);
            min = Math.min(min, gap);

            Arrays.fill(combine, false);
            return;
        }

        for (int i = index + 1; i < n; i++) {
            nums[depth] = i;
            bf(i, depth + 1);
        }
    }

    private static int sum() {
        int sum = 0;
        for (int i = 0; i < combine.length - 1; i++) {
            if (!combine[i]) {
                continue;
            }

            for (int j = i + 1; j < combine.length; j++) {
                if (!combine[j]) {
                    continue;
                }

                sum += board[i][j];
                sum += board[j][i];
            }
        }
        return sum;
    }

}
