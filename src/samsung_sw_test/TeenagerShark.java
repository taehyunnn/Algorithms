package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TeenagerShark {

    private static final int SIZE = 4;
    private static int[][] fishMap;
    private static Fish[] fishArray;
    private static int max;

    private static int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1}; //↑, ↖, ←, ↙, ↓, ↘, →, ↗
    private static int[] dCol = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishMap = new int[SIZE][SIZE];
        fishArray = new Fish[SIZE * SIZE + 1];

        for (int i = 0, j = 0; i < SIZE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int k = 0; k < SIZE; k++) {
                int num = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken());

                Fish fish = new Fish(num, i, j, direction - 1, true);
                fishArray[num] = fish;
                fishMap[i][j++] = num;
            }
            j = 0;
        }

        Fish shark = new Fish(0, 0, 0, 0, true);

        moveProcess(shark, fishMap, fishArray, 0, 0);

        System.out.println(max);
    }

    private static void moveProcess(Fish shark, int[][] fishMap, Fish[] fishArray, int row, int col) {
        eatFish(shark, fishMap[row][col], fishMap, fishArray);

        moveAllFish(shark, fishMap, fishArray);


        int nextRow = shark.row + dRow[shark.direction];
        int nextCol = shark.col + dCol[shark.direction];

        while (true) {
            // 이동 못하면 끝!
            if (!isRange(nextRow, nextCol)) {
                return;
            }

            // 이동할 위치의 물고기가 살아 있으면 잡아 먹는다.
            int feedFishNum = fishMap[nextRow][nextCol];
            if (feedFishNum != 0) {
                int[][] tempFishMap = new int[SIZE][SIZE];
                Fish[] tempFishArray = new Fish[SIZE * SIZE + 1];
                copy(fishArray, tempFishArray, fishMap, tempFishMap);

                moveProcess(new Fish(shark), tempFishMap, tempFishArray, nextRow, nextCol);
            }

            nextRow += dRow[shark.direction];
            nextCol += dCol[shark.direction];
        }
    }

    private static void eatFish(Fish shark, int currentFishNum, int[][] fishMap, Fish[] fishArray) {
        Fish currentFish = fishArray[currentFishNum];

        currentFish.alive = false;
        shark.row = currentFish.row;
        shark.col = currentFish.col;
        shark.direction = currentFish.direction;
        shark.num += currentFish.num;

        fishMap[shark.row][shark.col] = 0;

        max = Math.max(max, shark.num);

    }

    private static void copy(Fish[] fishArray, Fish[] tempFishArray, int[][] fishMap, int[][] tempFishMap) {
        for (int i = 1; i < fishArray.length; i++) {
            tempFishArray[i] = new Fish(fishArray[i]);
        }

        for (int i = 0; i < fishMap.length; i++) {
            System.arraycopy(fishMap[i], 0, tempFishMap[i], 0, fishMap[0].length);
        }
    }

    private static void moveAllFish(Fish shark, int[][] fishMap, Fish[] fishArray) {
        for (int i = 1; i < fishArray.length; i++) {
            Fish fish = fishArray[i];

            // 죽어있으면 패스
            if (!fish.alive) {
                continue;
            }


            for (int j = 0; j < dRow.length; j++) {
                int direction = (fish.direction + j) % dRow.length;

                int nextRow = fish.row + dRow[direction];
                int nextCol = fish.col + dCol[direction];

                // 벗어나거나 상어 만나면 이동 불가
                if (!isRange(nextRow, nextCol) || (nextRow == shark.row && nextCol == shark.col)) {
                    continue;
                }

                fish.direction = direction;
                int anotherFishNum = fishMap[nextRow][nextCol];
                // 기존의 물고기가 있으면 자리 교체
                if (anotherFishNum != 0) {
                    Fish anotherFish = fishArray[anotherFishNum];
                    changeFish(fishMap, fish, anotherFish);
                }
                // 빈 방이면 그냥 이동
                else {
                    fishMap[nextRow][nextCol] = fish.num;
                    fishMap[fish.row][fish.col] = 0;
                    fish.row = nextRow;
                    fish.col = nextCol;
                }

                break;
            }
        }
    }

    private static void changeFish(int[][] fishMap, Fish fish, Fish anotherFish) {
        fishMap[anotherFish.row][anotherFish.col] = fish.num;
        fishMap[fish.row][fish.col] = anotherFish.num;

        int temp = fish.row;
        fish.row = anotherFish.row;
        anotherFish.row = temp;

        temp = fish.col;
        fish.col = anotherFish.col;
        anotherFish.col = temp;
    }


    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < 4 && nextCol < 4;
    }

    static class Fish {
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
    }
}
