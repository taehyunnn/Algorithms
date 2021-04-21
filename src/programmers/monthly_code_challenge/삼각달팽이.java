package programmers.monthly_code_challenge;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 삼각달팽이 {
    public int[] solution(int n) {
        int row = 0, col = 0;

        int[][] map = new int[n][];

        for (int i = 0; i < map.length; i++) {
            map[i] = new int[i + 1];
        }

        int num = 1;
        int endNum = findEndNum(n);

        while (true) {
            // row 증가
            while (row < n && map[row][col] == 0) {
                map[row++][col] = num++;
            }
            row--;
            col++;

            if (num == endNum + 1) {
                break;
            }

            while (col < map[row].length && map[row][col] == 0) {
                map[row][col++] = num++;
            }
            col -= 2;
            row--;

            if (num == endNum + 1) {
                break;
            }

            // row,col 감소
            while (map[row][col] == 0) {
                map[row--][col--] = num++;
            }
            col++;
            row += 2;

            if (num == endNum + 1) {
                break;
            }
        }

        return Arrays.stream(map).flatMapToInt(IntStream::of).toArray();
    }

    private int findEndNum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 삼각달팽이().solution(4)));
    }
}
