package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ColoredPaper {

    private static final int LENGTH = 10;
    private static int[][] paper = new int[LENGTH][LENGTH];
    private static boolean[][] used = new boolean[LENGTH][LENGTH];
    private static int[] paperCnt = new int[6];
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        start(0, 0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void start(int row, int col, int cnt) {
        if (row == LENGTH) {
            min = Math.min(min, cnt);
            return;
        }

        // 최소값보다 크면 계산할 필요 없다
        if (cnt >= min) {
            return;
        }

        if (col == LENGTH) {
            start(row + 1, 0, cnt);
            return;
        }

        if (paper[row][col] == 1 && !used[row][col]) {

            for (int k = 5; k >= 1; k--) {
                if (paperCnt[k] == 0 || !isAttachable(row, col, k)) {
                    continue;
                }

                attachOrDetach(row, col, k, true);
                paperCnt[k]--;

                start(row, col + 1, cnt + 1);

                attachOrDetach(row, col, k, false);
                paperCnt[k]++;
            }
        } else {
            start(row, col + 1, cnt);
        }

    }

    private static boolean isAttachable(int row, int col, int k) {
        for (int i = row; i < row + k; i++) {
            for (int j = col; j < col + k; j++) {
                if (i == LENGTH || j == LENGTH) {
                    return false;
                }

                if (paper[i][j] == 0) {
                    return false;
                }

                if (used[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attachOrDetach(int row, int col, int size, boolean attachFlag) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                used[i][j] = attachFlag;
            }
        }
    }


    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        for (int i = 0; i < paper.length; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < paper[0].length; j++) {
                paper[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        Arrays.fill(paperCnt, 5);
    }
}
