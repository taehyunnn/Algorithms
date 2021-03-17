package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberCard {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        boolean[] cards = new boolean[20000001];

        for (int i = 0; i < n; i++) {
            cards[Integer.parseInt(stringTokenizer.nextToken()) + 10000000] = true;
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int m = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < m; i++) {
            if (cards[Integer.parseInt(stringTokenizer.nextToken()) + 10000000]) {
                System.out.print(1+" ");
            } else {
                System.out.print(0+" ");
            }
        }
    }
}
