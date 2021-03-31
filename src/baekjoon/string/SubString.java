package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubString {

    private static int[] pi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String pattern = br.readLine();

        pi = new int[pattern.length()];
        getPi(pattern);
        System.out.println(kmp(s, pattern) ? 1 : 0);
    }

    private static void getPi(String pattern) {
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }


    private static boolean kmp(String s, String pattern) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }

            if (s.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    return true;
                } else {
                    j++;
                }
            }
        }

        return false;
    }
}
