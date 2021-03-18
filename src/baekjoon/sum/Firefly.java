package baekjoon.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Firefly {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int h = Integer.parseInt(stringTokenizer.nextToken());

        int[] boardFromBottom = new int[h + 1];
        int[] boardFromTop = new int[h + 1];
        for (int i = 0; i < n / 2; i++) {
            boardFromBottom[Integer.parseInt(bufferedReader.readLine())]++;
            boardFromTop[Integer.parseInt(bufferedReader.readLine())]++;
        }

        int min = n;
        int minCount = 0;

        int[] sumFromBottom = new int[h + 1]; // 바닥부터 높이가 h 이하인 장애물의 수
        int[] sumFromTop = new int[h + 1];    // 천장부터 높이가 h 이하인 장애물의 수

        for (int i = 1; i <= h; i++) {
            sumFromBottom[i] = sumFromBottom[i - 1] + boardFromBottom[i];
            sumFromTop[i] = sumFromTop[i - 1] + boardFromTop[i];
        }

        for (int height = 1; height <= h; height++) {
            int temp = 0;

            temp += sumFromBottom[h] - sumFromBottom[height - 1];  // 전체(n/2)에서 높이가 height-1 이하의 수 빼면 장애물의 수
            temp += sumFromTop[h] - sumFromTop[h - height];

            if (min > temp) {
                min = temp;
                minCount = 1;
            } else if (min == temp) {
                minCount++;
            }
            // 전체(n/2)에서 높이가 h -  이하의 수를 빼면 장애물의 수
        }

        System.out.println(min + " " + minCount);
    }
}
