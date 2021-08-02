package baekjoon.math;

import java.util.Scanner;

public class 피보나치수3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();

        System.out.println(fibo(n));
    }

    private static int fibo(long n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int pisano = 1500000;
        int[] arr = new int[pisano + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= n && i <= pisano; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000;
        }

        return arr[(int) (n % pisano)];
    }
}
