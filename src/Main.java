import java.util.*;

public class Main {

    private boolean[][] visit;

    private int[] dRow = new int[]{1, -1, 0, 0};
    private int[] dCol = new int[]{0, 0, 1, -1};
    private int answer;

    public int solution(int[][] blocks, int[][] table) {
        visit = new boolean[table.length][table[0].length];
        // blocks의 길이를 n이라 할 때, 총 길이가 3n-2로 패딩한 새로운 배열을 만든다.
        int[][] paddingBlocks = getPaddingBlocks(blocks);

        // table을 회전하면서
        for (int i = 0; i < 4; i++) {
            // table 을 새로운 배열의 좌측 상단부터 우측 하단까지 이동시키면서 일치하는 완벽히 도형은 blocks에서 1로 바꾸고, table에서는 0으로 바꾼다. 개수를 센다

            print(paddingBlocks);
            print(table);

            checkBlocksAndTable(paddingBlocks, table);
            table = rotateTable(table, i);
        }

        return answer;
    }

    private void checkBlocksAndTable(int[][] blocks, int[][] table) {
        int length = table.length;

        // i,j 는 blocks의 시작 좌표
        for (int i = 0; i < 2 * length - 1; i++) {
            for (int j = 0; j < 2 * length - 1; j++) {
                scanBlock(blocks, table, length, i, j);
            }
        }
    }

    private void scanBlock(int[][] blocks, int[][] table, int length, int i, int j) {
        clearVisit();

        // k와 l은 table의 좌표, 따라서 blocks의 좌표는 i+k, l+j
        for (int k = 0; k < length; k++) {
            for (int l = 0; l < length; l++) {
                if (table[k][l] == 1 && blocks[i + k][l + j] == 0 && !visit[k][l]) {
                    // bfs로 table과 blocks를 동시에 탐색. 좌표 저장해놓고, 일치하면 blocks는 1로 table은 0으로 변경
                    bfs(blocks, table, i, j, k, l);
                }
            }
        }
    }

    private void bfs(int[][] blocks, int[][] table, int i, int j, int k, int l) {
        List<int[]> list = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();
        visit[k][l] = true;

        queue.add(new int[]{k, l});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            list.add(poll);

            for (int m = 0; m < dCol.length; m++) {
                if (i == 0 && j == 3 && k == 2 && l == 1) {
                    System.out.println();
                }
                int nextRow = poll[0] + dRow[m];
                int nextCol = poll[1] + dCol[m];

                if (!isRange(nextRow, nextCol, table) || visit[nextRow][nextCol]) {
                    continue;
                }

                if (table[nextRow][nextCol] == 1 && blocks[i + nextRow][j + nextCol] == 0) {
                    visit[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                } else if (table[nextRow][nextCol] == blocks[i + nextRow][j + nextCol]) {    // 블록과 빈칸이 일치하지 않는 경우
                    return;
                }
            }
        }

        // 일치하는 블록을 찾은 경우
        for (int[] ints : list) {
            int row = ints[0];
            int col = ints[1];

            table[row][col] = 0;
            blocks[row + i][col + j] = 1;
            answer++;
        }
    }

    private boolean isRange(int nextRow, int nextCol, int[][] table) {
        return nextRow >= 0 && nextCol >= 0 && nextRow < table.length && nextCol < table[0].length;
    }

    private void clearVisit() {
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
    }

    private int[][] rotateTable(int[][] table, int num) {
        int length = table.length;
        int[][] temp = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[j][i] = table[length - i - 1][j];
            }
        }

        return temp;
    }

    private void print(int[][] paddingBlocks) {
        for (int[] paddingBlock : paddingBlocks) {
            for (int i : paddingBlock) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[][] getPaddingBlocks(int[][] blocks) {
        int length = blocks.length;
        int[][] temp = new int[length * 3 - 2][length * 3 - 2];

        for (int[] ints : temp) {
            Arrays.fill(ints, 1);
        }

        for (int i = length - 1; i < 2 * length - 1; i++) {
            System.arraycopy(blocks[i - length + 1], 0, temp[i], length - 1, blocks.length);
        }

        return temp;
    }

    public static void main(String[] args) {
//        System.out.println(new Main().solution(new int[][]{{0, 1, 0}, {1, 1, 1}, {1, 0, 0}}, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}}));
//        System.out.println(new Main().solution(new int[][]{{0, 1, 0}, {0, 1, 1}, {1, 0, 0}}, new int[][]{{1, 1, 0}, {0, 0, 1}, {0, 1, 1}}));
        System.out.println(new Main().solution(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 0, 1}}, new int[][]{{1, 1, 0}, {0, 0, 1}, {0, 1, 1}}));
//        System.out.println(new Main().solution(new int[][]{{1, 0}, {1, 0}}, new int[][]{{0, 1,}, {0, 1}}));

//        System.out.println(new Main()/.solution(new int[][]{{1, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 0, 1, 1, 1}, {0, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0, 0}},
//                new int[][]{{1, 1, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0}, {1, 1, 0, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 0, 1}}));
    }

}