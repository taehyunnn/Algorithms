package baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 나는친구가적다 {

    private static int[] pi;

    private static void getPi(String keyword) {
        int j = 0;
        for (int i = 1; i < keyword.length(); i++) {
            while (j > 0 && keyword.charAt(i) != keyword.charAt(j)) {
                j = pi[j - 1];
            }

            if (keyword.charAt(i) == keyword.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = bufferedReader.readLine();
        String keyword = bufferedReader.readLine();

        string = string.replaceAll("[0-9]", "");

        pi = new int[keyword.length()];
        getPi(keyword);
        kmp(string, keyword);
    }

    private static void kmp(String string, String keyword) {
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            while (j > 0 && string.charAt(i) != keyword.charAt(j)) {
                j = pi[j - 1];
            }

            if (string.charAt(i) == keyword.charAt(j)) {
                ++j;

                if (j == keyword.length()) {
                    System.out.println(1);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
