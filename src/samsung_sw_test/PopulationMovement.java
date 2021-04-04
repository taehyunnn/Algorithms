package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopulationMovement {

    private static int[][] countries;
    private static int[][] union;
    private static int[] dRow = new int[]{0, 0, 1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    private static int L;
    private static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        countries = new int[n][n];
        union = new int[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                countries[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int result = 0;
        List<List<int[]>> countriesWithSameUnion = new ArrayList<>();
        List<Integer> sumOfPopulationList = new ArrayList<>();
        while (searchUnion(countriesWithSameUnion, sumOfPopulationList) > 0) {
            result++;

            for (int i = 1; i < countriesWithSameUnion.size(); i++) {
                int avg = sumOfPopulationList.get(i) / countriesWithSameUnion.get(i).size();

                for (int[] ints : countriesWithSameUnion.get(i)) {
                    countries[ints[0]][ints[1]] = avg;
                }
            }

            countriesWithSameUnion = new ArrayList<>();
            sumOfPopulationList = new ArrayList<>();
            for (int[] ints : union) {
                Arrays.fill(ints, 0);
            }
        }

        System.out.println(result);
    }

    private static int searchUnion(List<List<int[]>> countriesWithSameUnion, List<Integer> sumOfPopulationList) {
        int unionNumber = 0;
        sumOfPopulationList.add(0);

        countriesWithSameUnion.add(new ArrayList<>());

        for (int i = 0; i < union.length; i++) {
            for (int j = 0; j < union[0].length; j++) {
                if (union[i][j] != 0) { // 이미 연합이 있으면 건너 뛰기
                    continue;
                }

                if (isMovableToAnotherCountry(i, j)) {
                    unionNumber++;

                    countriesWithSameUnion.add(new ArrayList<>());
                    sumOfPopulationList.add(markUnionNumber(unionNumber, i, j, countriesWithSameUnion.get(unionNumber)));
                }
            }
        }

        return unionNumber;
    }

    private static int markUnionNumber(int unionNumber, int i, int j, List<int[]> countriesWithSameUnion) {
        int sunOfPopulation = countries[i][j];

        union[i][j] = unionNumber;
        countriesWithSameUnion.add(new int[]{i, j});
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int k = 0; k < 4; k++) {

                // 주변 나라중에 차이가 L 이상 R 이하인 나라가 있으면 연합하고 큐에 삽입
                int nextRow = current[0] + dRow[k];
                int nextCol = current[1] + dCol[k];

                // 전체 맵을 벗어나거나 이미 같은 연합이면 건너뛰기
                if (!isRange(nextRow, nextCol) || union[nextRow][nextCol] == unionNumber) {
                    continue;
                }

                int gap = Math.abs(countries[nextRow][nextCol] - countries[current[0]][current[1]]);
                if (gap >= L && gap <= R) {
                    union[nextRow][nextCol] = unionNumber;
                    queue.add(new int[]{nextRow, nextCol});
                    sunOfPopulation += countries[nextRow][nextCol];
                    countriesWithSameUnion.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return sunOfPopulation;
    }

    private static boolean isMovableToAnotherCountry(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (isRange(nextRow, nextCol)) {
                int gap = Math.abs(countries[nextRow][nextCol] - countries[row][col]);
                if (gap >= L && gap <= R) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < union.length && nextCol < union[0].length;
    }
}
