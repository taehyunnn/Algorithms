package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeIsNotPalindrome {

    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if (isPalindrome(s)) {
            if (isAllSame(s)) {
                System.out.println(-1);
            } else {
                System.out.println(s.length()-1);
            }
        } else {
            System.out.println(s.length());
        }

    }

    private static boolean isAllSame(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - i-1)) {
                return false;
            }
        }
        return true;
    }
}
