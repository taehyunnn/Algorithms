package baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현 {

    private static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        set = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            set[i] = i;
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int operator = Integer.parseInt(stringTokenizer.nextToken());
            int first = Integer.parseInt(stringTokenizer.nextToken());
            int second = Integer.parseInt(stringTokenizer.nextToken());

            operate(operator, first, second);
        }
    }

    private static void operate(int operator, int first, int second) {
        switch (operator) {
            case 0:
                union(first, second);
                break;
            case 1:
                String result = isSameSet(first, second) ? "yes" : "no";
                System.out.println(result);
        }
    }

    private static boolean isSameSet(int first, int second) {
        return find(first) == find(second);
    }

    private static void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        set[secondParent] = firstParent;
    }

    private static int find(int first) {
        if (first == set[first]) {
            return first;
        }

        return set[first] = find(set[first]);
    }
}
