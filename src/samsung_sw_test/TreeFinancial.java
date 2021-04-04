package samsung_sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeFinancial {

    private static int[][] ground;  // 현재 토양의 양분 상태
    private static int[][] nutrients;   // 추가해야 하는 양분 양
    private static List<Tree> treeList; // 나무 리스트
    private static int[] dRow = new int[]{0, 0, 1, 1, 1, -1, -1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 1, -1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // n : 땅 크기, m : 심은 나무의 정보 , k : k년
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        ground = new int[n + 1][n + 1];
        nutrients = new int[n + 1][n + 1];
        treeList = new LinkedList<>();

        // 땅 정보 입력
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                ground[i][j] = 5;
            }
        }

        // 심을 나무 정보 입력
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());
            int age = Integer.parseInt(stringTokenizer.nextToken());

            treeList.add(new Tree(row, col, age));
        }

        // k년 동안 반복
        Queue<Tree> deadList = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            // 봄
            spring(deadList);

            // 여름
            summer(deadList);

            // 가을
            fall();

            // 겨울
            winter();

            deadList = new LinkedList<>();
        }

        System.out.println(treeList.size());
    }

    private static void winter() {
        for (int i = 1; i < ground.length; i++) {
            for (int j = 1; j < ground[0].length; j++) {
                ground[i][j] += nutrients[i][j];
            }
        }
    }

    private static void fall() {
        List<Tree> newTreeList = new LinkedList<>();
        for (Tree tree : treeList) {
            if (tree.age % 5 == 0) {    // 나무의 나이가 5의 배수일 때 나무가 번식한다.
                breeding(tree, newTreeList);
            }
        }
        treeList.addAll(0, newTreeList);
    }

    private static void breeding(Tree tree, List<Tree> newTreeList) {
        for (int i = 0; i < 8; i++) {
            int nextRow = tree.row + dRow[i];
            int nextCol = tree.col + dCol[i];

            if (isRange(nextRow, nextCol)) {
                newTreeList.add(new Tree(nextRow, nextCol, 1));
            }
        }
    }

    private static boolean isRange(int nextRow, int nextCol) {
        return nextRow > 0 && nextCol > 0 && nextRow < ground.length && nextCol < ground[0].length;
    }

    private static void summer(Queue<Tree> deadList) {
        while (!deadList.isEmpty()) {
            Tree deadTree = deadList.poll();
            ground[deadTree.row][deadTree.col] += deadTree.age / 2;
        }
    }

    private static void spring(Queue<Tree> deadList) {
        Iterator<Tree> iterator = treeList.iterator();
        while (iterator.hasNext()) {
            Tree tree = iterator.next();
            if (tree.age <= ground[tree.row][tree.col]) {
                ground[tree.row][tree.col] -= tree.age;
                tree.age++;
            } else {
                iterator.remove();
                deadList.add(tree);
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int row;
        int col;
        int age;

        Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }
}
