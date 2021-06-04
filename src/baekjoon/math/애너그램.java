package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 애너그램 {

    private static char[] duplicationArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < cnt; i++) {
            String s = bufferedReader.readLine();
            printAllWord(s);
        }
    }

    private static void printAllWord(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        duplicationArr = new char[chars.length];
        permute(new char[chars.length], chars, 0, chars.length, new boolean[chars.length]);
    }

    private static void permute(char[] temp, char[] chars, int depth, int length, boolean[] visit) {
        if (depth == length) {
            String s = String.valueOf(temp);
            System.out.println(s);
            return;
        }

        duplicationArr[depth] = 0;

        for (int i = 0; i < length; i++) {
            if (visit[i]) {
                continue;
            }

            if (duplicationArr[depth] >= chars[i]) {
                continue;
            }

            duplicationArr[depth] = chars[i];

            visit[i] = true;
            temp[depth] = chars[i];
            permute(temp, chars, depth + 1, length, visit);
            visit[i] = false;
        }
    }
}
