package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TeenagerShark {

    private static final int SIZE = 4;
    private static Fish[] fishList;
    private static int[][] fishNum;
    private static int max;

    private static int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1}; //↑, ↖, ←, ↙, ↓, ↘, →, ↗
    private static int[] dCol = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishList = new Fish[17];
        fishNum = new int[SIZE][SIZE];

        for (int i = 0, j = 0; i < SIZE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int k = 0; k < SIZE; k++) {
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                fishList[a] = new Fish(a, b, i, j, true);
                fishNum[i][j++] = a;
            }
            j = 0;
        }

        System.out.println(max);
    }


    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < 4 && nextCol < 4;
    }

    static class Fish implements Comparable<Fish> {
        int num;
        int row;
        int col;
        int direction;
        boolean alive;

        public Fish(int num, int row, int col, int direction, boolean alive) {
            this.num = num;
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.alive = alive;
        }

        public Fish(Fish fish) {
            this.num = fish.num;
            this.row = fish.row;
            this.col = fish.col;
            this.direction = fish.direction;
            this.alive = fish.alive;
        }

        @Override
        public int compareTo(Fish o) {
            return num - o.num;
        }
    }
}
