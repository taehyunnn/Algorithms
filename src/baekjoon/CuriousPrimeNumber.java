package baekjoon;

import java.util.Scanner;

public class CuriousPrimeNumber {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 2; i < 10; i++) {
            switch (i) {
                case 2:
                case 3:
                case 5:
                case 7:
                    dfs(1, n, i);
            }
        }
    }

    private static void dfs(int depth, int n, int currentNumber) {
        if (depth == n) {
            System.out.println(currentNumber);
            return;
        }

        for (int i = 1; i < 10; i+=2) {
            int number = currentNumber*10 + i;
            if (isPrime(number)) {
                dfs(depth + 1, n, number);
            }
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 3; i <= Math.sqrt(number); i+=2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
