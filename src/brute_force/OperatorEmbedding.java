package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OperatorEmbedding {

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int[] numbers;
    private static int[] operators;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        numbers = new int[n];
        operators = new int[4];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        dfs(1, n, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int currentDepth, int numOfNumbers, int num) {
        if (currentDepth == numOfNumbers) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == 0) {
                continue;
            }
            operators[i]--;
            switch (i) {
                case 0:
                    dfs(currentDepth + 1, numOfNumbers, num + numbers[currentDepth]);
                    break;
                case 1:
                    dfs(currentDepth + 1, numOfNumbers, num - numbers[currentDepth]);
                    break;
                case 2:
                    dfs(currentDepth + 1, numOfNumbers, num * numbers[currentDepth]);
                    break;
                case 3:
                    dfs(currentDepth + 1, numOfNumbers, num / numbers[currentDepth]);
                    break;
            }
            operators[i]++;
        }
    }
}
