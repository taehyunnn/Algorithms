package baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoodSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());

        int[] result = new int[n];
        bf(0, n, -1, result);
    }

    private static boolean bf(int cuerrentDepth, int n, int pre, int[] result) {
        if (cuerrentDepth == n) {
            for (int i : result) {
                System.out.print(i);
            }
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            if (i == pre) {
                continue;
            }
            result[cuerrentDepth] = i;
            if (isGood(result, cuerrentDepth)) {
                if (bf(cuerrentDepth + 1, n, i, result)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isGood(int[] result, int currentIndex) {
        for (int i = 2; i <= (currentIndex + 1) / 2; i++) {  // i : 몇개씩 묶을지
            int j;
            int a = 0, b = 0;
            for (j = currentIndex; j > currentIndex - i; j--) {
                a += result[j];
                a *= 10;
            }
            for (; j > currentIndex - 2 * i; j--) {
                b += result[j];
                b *= 10;
            }
            if (a == b) {
                return false;
            }
        }
        return true;
    }
}
