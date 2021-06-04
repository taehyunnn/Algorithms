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

        // depth 자릿수에 처음 진입 할 때는 0으로 초기화
        duplicationArr[depth] = 0;

        for (int i = 0; i < length; i++) {
            if (visit[i]) {
                continue;
            }

            // depth 자릿수에 새로 올 수 있는 수는 점점 커져야 한다. (중복도 제거)

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
