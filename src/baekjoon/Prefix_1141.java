package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Prefix_1141 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int answer = n;
        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
        }

        Arrays.sort(strings);

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (strings[i].charAt(0) != strings[j].charAt(0)) {
                    break;
                }

                if (strings[j].startsWith(strings[i])) {
                    answer--;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
