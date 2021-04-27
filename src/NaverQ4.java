import java.util.*;

public class NaverQ4 {

    private boolean[][] visit;

    private int[] dRow = new int[]{1, -1, 0, 0};
    private int[] dCol = new int[]{0, 0, 1, -1};
    private int answer;

    private Map<Block, Integer> map;    // 도형 담는 배열

    public int solution(int[][] blocks, int[][] table) {
        visit = new boolean[table.length][table[0].length];
        map = new HashMap<>();

        scanBlocks(blocks); // 홈 모양 파악

        for (int i = 0; i < 4; i++) {
            scanTableAndMatchBlock(table);  // 테이블에 있는 블록 모양 파악
            table = rotateTable(table, i);  // 회전
        }

        return answer;
    }

    private void scanTableAndMatchBlock(int[][] table) {
        clearVisit();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == 1 && !visit[i][j]) {
                    Block block = bfsTable(table, i, j, visit);
                    fitCheckBetweenTableAndBlock(block,i,j,table);
                }
            }
        }
    }

    private void fitCheckBetweenTableAndBlock(Block block, int row, int col, int[][] table) {
        if (isFit(block)) {
            // table에서 블록 제거
            for (int i = 0; i < block.size(); i++) {
                int[] location = block.get(i);
                int originalRow = location[0] + row;
                int originalCol = location[1] + col;
                table[originalRow][originalCol] = 0;
            }
        }
    }

    private Block bfsTable(int[][] table, int row, int col, boolean[][] visit) {
        Block block = new Block();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visit[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            block.add(new int[]{poll[0] - row, poll[1] - col});

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = poll[0] + dRow[i];
                int nextCol = poll[1] + dCol[i];

                if (!isRange(nextRow, nextCol, table) || visit[nextRow][nextCol] || table[nextRow][nextCol] == 0) {
                    continue;
                }

                visit[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }
        }

        return block;
    }

    private boolean isFit(Block block) {
        Integer value = map.getOrDefault(block, 0);
        if (value == 1) {
            map.remove(block);
            answer += block.size();
            return true;
        } else if (value > 1) {
            map.put(block, value - 1);
            answer += block.size();
            return true;
        }
        return false;
    }

    private void scanBlocks(int[][] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] == 0 && !visit[i][j]) {
                    bfs(blocks, i, j, visit);
                }
            }
        }
    }

    private void bfs(int[][] blocks, int row, int col, boolean[][] visit) {
        Block block = new Block();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visit[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            block.add(new int[]{poll[0] - row, poll[1] - col});

            for (int i = 0; i < dRow.length; i++) {
                int nextRow = poll[0] + dRow[i];
                int nextCol = poll[1] + dCol[i];

                if (!isRange(nextRow, nextCol, blocks) || visit[nextRow][nextCol] || blocks[nextRow][nextCol] == 1) {
                    continue;
                }

                visit[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }
        }

        map.put(block, map.getOrDefault(block, 0) + 1);
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

    public static void main(String[] args) {
//        System.out.println(new Temp().solution(new int[][]{{0, 1, 0}, {1, 1, 1}, {1, 0, 0}}, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}}));  //1
//        System.out.println(new Temp().solution(new int[][]{{0, 1, 0}, {0, 1, 1}, {1, 0, 0}}, new int[][]{{1, 1, 0}, {0, 0, 1}, {0, 1, 1}}));    // 2
//        System.out.println(new Temp().solution(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 0, 1}}, new int[][]{{1, 1, 0}, {0, 0, 1}, {0, 1, 1}}));    // 5
//        System.out.println(new Temp().solution(new int[][]{{1, 0}, {1, 0}}, new int[][]{{0, 1,}, {0, 1}}));

        System.out.println(new NaverQ4().solution(new int[][]{{1, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 0, 1, 1, 1}, {0, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0, 0}},
                new int[][]{{1, 1, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0}, {1, 1, 0, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 0, 1}}));
    }

    static class Block {
        List<int[]> locations;

        Block() {
            locations = new LinkedList<>();
        }

        void add(int[] location) {
            locations.add(location);
        }

        int size() {
            return locations.size();
        }

        int[] get(int index) {
            return locations.get(index);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Block block = (Block) o;

            if (this.size() != block.size()) return false;

            for (int i = 0; i < locations.size(); i++) {
                if (this.get(i)[0] != block.get(i)[0] || this.get(i)[1] != block.get(i)[1]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(locations.size());
        }
    }
}