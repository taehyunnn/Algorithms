package baekjoon.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 즐거운단어 {

    private static String s;
    private static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        s = bufferedReader.readLine();


        dfs(0, 0, 0, false, 1);

        System.out.println(result);
    }

    private static void dfs(int index, int cntOfConstant, int cntOfVowel, boolean l_Flag, long cnt) {
        if (isInvalid(cntOfConstant, cntOfVowel)) {
            return;
        }

        if (index == s.length()) {
            if (l_Flag) {
                result += cnt;
            }
            return;
        }


        char c = s.charAt(index);
        if (isUnderBar(c)) {
            dfs(index + 1, cntOfConstant + 1, 0, l_Flag, cnt * 20); // L 제외 자음 선택
            dfs(index + 1, cntOfConstant + 1, 0, true, cnt);    // L 선택
            dfs(index + 1, 0, cntOfVowel + 1, l_Flag, cnt * 5); // 모음 선택
        } else if (isVowel(c)) {
            dfs(index + 1, 0, cntOfVowel + 1, l_Flag, cnt);

        } else {
            if (isL(c)) {
                dfs(index + 1, cntOfConstant + 1, 0, true, cnt);
            } else {
                dfs(index + 1, cntOfConstant + 1, 0, l_Flag, cnt);
            }
        }

    }

    private static boolean isL(char c) {
        return c == 'L';
    }

    private static boolean isInvalid(int cntOfConstant, int cntOfVowel) {
        return cntOfConstant == 3 || cntOfVowel == 3;
    }

    private static boolean isUnderBar(char c) {
        return c == '_';
    }

    private static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
