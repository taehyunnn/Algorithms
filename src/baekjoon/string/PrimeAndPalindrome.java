package baekjoon.string;

import java.util.Scanner;

public class PrimeAndPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        boolean[] primeCheck = new boolean[1010001];    // 소수가 아니면 true, 소수면 false
        primeCheck[1] = true;
        for (int i = 2; i <= 1100; i++) {
            if (primeCheck[i]) {
                continue;
            }
            for (int j = i + i; j <= 1010000; j += i) {
                primeCheck[j] = true;
            }
        }

        for (int i = n; i <= 1010000; i++) {
            if (!primeCheck[i] && isPalindrome(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    private static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
