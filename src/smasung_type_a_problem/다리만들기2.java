package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 다리만들기2 {

    // 최소 스패닝 트리
    private static List<int[]> links;
    private static int[] groups;

    private static int[][] linkArray;

    // 전체 지도
    private static int[][] country;
    private static int[] dRow = new int[]{1, -1, 0, 0};
    private static int[] dCol = new int[]{0, 0, 1, -1};

    private static int min;

    public static void main(String[] args) throws IOException {
        init();

        int numOfIsland = markIsland();

        initSecond(numOfIsland);

        // n 개의 섬을 일직선 다리로 연결
        for (int i = 0; i < country.length; i++) {
            for (int j = 0; j < country[0].length; j++) {
                // 섬이면서 바닷가랑 붙어 있는 경우만
                construcBridgeProcess(i, j);
            }
        }

        makeLinksArrayToList();

        // links를 토대로 최소 스패닝 트리를 구하면 최소값이다.
        if (isAllConnected()) {
            calcMinimumSpanningTree();
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }

    private static void initSecond(int numOfIsland) {
        groups = new int[numOfIsland + 1];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = i;
        }
        linkArray = new int[numOfIsland + 1][numOfIsland + 1];
        for (int[] ints : linkArray) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
    }

    private static void printCountry() {
        for (int[] ints : country) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void makeLinksArrayToList() {
        for (int i = 0; i < linkArray.length; i++) {
            for (int j = 0; j < linkArray[0].length; j++) {
                if (linkArray[i][j] != Integer.MAX_VALUE) {
                    links.add(new int[]{i, j, linkArray[i][j]});
                }
            }
        }
    }

    private static void construcBridgeProcess(int i, int j) {
        if (country[i][j] != 0) {
            for (int k = 0; k < dCol.length; k++) {
                int nextRow = i + dRow[k];
                int nextCol = j + dCol[k];

                if (isRange(nextRow, nextCol) && country[nextRow][nextCol] == 0) {
                    constructBridge(country[i][j], nextRow, nextCol, k, 0);
                }
            }
        }
    }

    private static void constructBridge(int isLandNum, int row, int col, int dir, int length) {
        if (row < 0 || col < 0 || row == country.length || col == country[0].length) {
            return;
        }

        int otherIslandNum = country[row][col];
        if (otherIslandNum > 0) {
            if (isLandNum < otherIslandNum && length > 1) {
                linkArray[isLandNum][otherIslandNum] = Math.min(linkArray[isLandNum][otherIslandNum], length);
            }
            return;
        }

        constructBridge(isLandNum, row + dRow[dir], col + dCol[dir], dir, length + 1);
    }

    private static boolean isAllConnected() {
        int num = groups[1];
        for (int i = 2; i < groups.length; i++) {
            if (num != groups[i]) {
                return false;
            }
        }
        return true;
    }

    private static void calcMinimumSpanningTree() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        queue.addAll(links);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int start = poll[0];
            int end = poll[1];
            int cost = poll[2];

            if (groups[start] == groups[end]) {
                continue;
            }

            min += cost;
            unionGroup(groups[start], groups[end]);
        }
    }

    private static void unionGroup(int small, int big) {
        if (small > big) {
            int temp = small;
            small = big;
            big = temp;
        }

        for (int i = 1; i < groups.length; i++) {
            if (groups[i] == big) {
                groups[i] = small;
            }
        }
    }

    private static int markIsland() {
        int num = 1;

        boolean[][] visit = new boolean[country.length][country[0].length];

        for (int i = 0; i < country.length; i++) {
            for (int j = 0; j < country[0].length; j++) {
                if (country[i][j] == 1 && !visit[i][j]) {
                    bfs(num++, i, j, visit);
                }
            }
        }
        return num - 1;
    }

    private static void bfs(int num, int row, int col, boolean[][] visit) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        country[row][col] = num;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = poll[0] + dRow[i];
                int nextCol = poll[1] + dCol[i];

                if (!isRange(nextRow, nextCol) || visit[nextRow][nextCol] || country[nextRow][nextCol] == 0) {
                    continue;
                }

                visit[nextRow][nextCol] = true;
                country[nextRow][nextCol] = num;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < country.length && nextCol < country[0].length;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        country = new int[n][m];
        links = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                country[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }
}
