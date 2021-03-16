package baekjoon;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int additionalMemory = scanner.nextInt();

        int[] memories = new int[n];
        int[] costs = new int[n];
        int[] dp = new int[10001];

        for (int i = 0; i < n; i++) {
            memories[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            costs[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {   // i: app의 index 번호
            for (int j = 10000; j >= costs[i]; j--) {   // j: 사용할 cost의 양
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + memories[i]);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= additionalMemory) {
                System.out.println(i);
                break;
            }
        }
    }
}
