package programmers.kakao_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColumnsAndBeams {
    private final int COLUMN = 0;
    private final int BEAM = 1;
    private boolean[][][] map;
    private List<int[]> list = new ArrayList<>();

    public int[][] solution(int n, int[][] build_frame) {

        map = new boolean[n + 3][n + 3][2];   // [2]에서 [0] : 기둥유무 [1] : 보 유무

        for (int[] ints : build_frame) {
            if (ints[3] == 1) {
                if (ints[2] == BEAM) {
                    if (canBeInstalledBeam(ints[0] + 1, ints[1] + 1)) {
                        list.add(new int[]{ints[0], ints[1], BEAM});
                        map[ints[1] + 1][ints[0] + 1][BEAM] = true;
                    }
                } else {
                    if (canBeInstalledColumn(ints[0] + 1, ints[1] + 1)) {
                        list.add(new int[]{ints[0], ints[1], COLUMN});
                        map[ints[1] + 1][ints[0] + 1][COLUMN] = true;
                    }
                }
            } else {
                if (ints[2] == BEAM) {
                    removeBeam(ints[0] + 1, ints[1] + 1);
                } else {
                    removeColumn(ints[0] + 1, ints[1] + 1);
                }
            }
        }


        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        Arrays.sort(answer, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        return answer;
    }

    private boolean canBeInstalledColumn(int x, int y) {
        return y == 1 || map[y - 1][x][COLUMN] || map[y][x - 1][BEAM] || map[y][x][BEAM];
    }

    private boolean canBeInstalledBeam(int x, int y) {
        return (map[y][x - 1][BEAM] && map[y][x + 1][BEAM]) || map[y - 1][x][COLUMN] || map[y - 1][x + 1][COLUMN];
    }


    private void removeColumn(int x, int y) {
        map[y][x][COLUMN] = false;

        if (map[y + 1][x][COLUMN] && !canBeInstalledColumn(x, y + 1)) {
            map[y][x][COLUMN] = true;
            return;
        }
        if (map[y + 1][x - 1][BEAM] && !canBeInstalledBeam(x - 1, y + 1)) {
            map[y][x][COLUMN] = true;
            return;
        }
        if (map[y + 1][x][BEAM] && !canBeInstalledBeam(x, y + 1)) {
            map[y][x][COLUMN] = true;
            return;
        }

        removeStructureInList(x, y, COLUMN);
    }

    private void removeBeam(int x, int y) {
        map[y][x][BEAM] = false;

        if (map[y][x][COLUMN] && !canBeInstalledColumn(x, y)) {
            map[y][x][BEAM] = true;
            return;
        }
        if (map[y][x + 1][COLUMN] && !canBeInstalledColumn(x + 1, y)) {
            map[y][x][BEAM] = true;
            return;
        }
        if (map[y][x - 1][BEAM] && !canBeInstalledBeam(x - 1, y)) {
            map[y][x][BEAM] = true;
            return;
        }
        if (map[y][x + 1][BEAM] && !canBeInstalledBeam(x + 1, y)) {
            map[y][x][BEAM] = true;
            return;
        }

        removeStructureInList(x, y, BEAM);

    }

    private void removeStructureInList(int x, int y, int type) {
        for (int[] ints : list) {
            if (ints[0] == x - 1 && ints[1] == y - 1 && ints[2] == type) {
                list.remove(ints);
                return;
            }
        }
    }


    public static void main(String[] args) {
//        int[][] solution = new ColumnsAndBeams().solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}});
        int[][] solution = new ColumnsAndBeams().solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});
        for (int[] ints : solution) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
