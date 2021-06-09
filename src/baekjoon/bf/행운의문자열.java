package baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 행운의문자열 {

    private static char[] chars, temp;
    private static int[] cnt = new int[26];
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        chars = bufferedReader.readLine().toCharArray();
        temp = new char[chars.length];
        for (char aChar : chars) {
            cnt[aChar - 'a']++;
        }

        bf(0, chars.length, 0);
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 1) {
                result /= factorial(cnt[i]);
            }
        }
        System.out.println(result);
    }

    private static int factorial(int i) {
        int result = 1;
        for (int j = 1; j <= i; j++) {
            result *= j;
        }
        return result;
    }

    private static void bf(int index, int length, int usedBitMask) {
        if (index == length) {
            result++;
            return;
        }

        for (int i = 0; i < length; i++) {
            if (((1 << i) & usedBitMask) != 0) {
                continue;
            }

            temp[index] = chars[i];

            if (index > 0 && temp[index] == temp[index - 1]) {
                continue;
            }

            bf(index + 1, length, usedBitMask | (1 << i));
        }
    }
}
