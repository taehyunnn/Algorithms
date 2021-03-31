package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CubeEditor {

    private static int[] pi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i);
            pi = new int[substring.length()];
            max = Math.max(max, getPi(substring));
        }
        System.out.println(max);
    }

    private static int getPi(String s) {
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            // 맞는 위치가 나올 때까지 j - 1칸의 공통 부분 위치로 이동
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                // i길이 문자열의 공통 길이는 j의 위치 + 1
                pi[i] = ++j;
            }
        }

        int max = 0;
        for (int i : pi) {
            max = Math.max(max, i);
        }
        return max;
    }
}
