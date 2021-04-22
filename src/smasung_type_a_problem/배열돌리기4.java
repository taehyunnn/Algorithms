package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4 {

    private static int[][] array;
    private static int[][] rotationInfoArray;
    private static int[] orders;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        permute(0, orders.length, 0);
        System.out.println(min);
    }

    private static void permute(int currentDepth, int targetDepth, int bitMasking) {
        if (currentDepth == targetDepth) {
            rotateArrayProcess();
            return;
        }

        for (int i = 0; i < orders.length; i++) {
            if (((1 << i) & bitMasking) != 0) {
                continue;
            }

            orders[currentDepth] = i;
            permute(currentDepth + 1, targetDepth, bitMasking | (1 << i));
        }
    }

    private static void rotateArrayProcess() {
        int[][] tempArray = getNewArray();

        for (int order : orders) {
            rotateArray(tempArray, rotationInfoArray[order]);
        }


        min = Math.min(min, findMinInArray(tempArray));
    }

    private static void print(int[][] tempArray) {
        for (int[] ints : tempArray) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void rotateArray(int[][] tempArray, int[] rotationInfo) {
        int centerRow = rotationInfo[0] - 1;
        int centerCol = rotationInfo[1] - 1;
        int length = rotationInfo[2];
        for (int gap = 1; gap <= length; gap++) {
            rotateArrayOneStep(tempArray, centerRow, centerCol, gap);
        }
//        print(tempArray);
    }

    private static void rotateArrayOneStep(int[][] tempArray, int centerRow, int centerCol, int gap) {
        int row = centerRow - gap;
        int col = centerCol - gap;
        int temp = tempArray[row][col];

        // 좌측
        for (; row < centerRow + gap; row++) {
            tempArray[row][col] = tempArray[row + 1][col];
        }

        // 아래
        for (; col < centerCol + gap; col++) {
            tempArray[row][col] = tempArray[row][col + 1];
        }

        // 우측
        for (; row > centerRow - gap; row--) {
            tempArray[row][col] = tempArray[row - 1][col];
        }

        // 위
        for (; col > centerCol - gap; col--) {
            tempArray[row][col] = tempArray[row][col - 1];
        }

        tempArray[row][++col] = temp;
    }

    private static int[][] getNewArray() {
        int[][] tempArray = new int[array.length][array[0].length];

        for (int i = 0; i < tempArray.length; i++) {
            System.arraycopy(array[i], 0, tempArray[i], 0, array[i].length);
        }

        return tempArray;
    }

    private static int findMinInArray(int[][] tempArray) {
        int min = Integer.MAX_VALUE;

        for (int[] ints : tempArray) {
            int temp = 0;
            for (int anInt : ints) {
                temp += anInt;
            }
            min = Math.min(min, temp);
        }

        return min;
    }


    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int cnt = Integer.parseInt(stringTokenizer.nextToken());

        array = new int[n][m];
        rotationInfoArray = new int[cnt][3];
        orders = new int[cnt];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < cnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            rotationInfoArray[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            rotationInfoArray[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            rotationInfoArray[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }
    }
}
