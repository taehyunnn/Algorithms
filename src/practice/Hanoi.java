package practice;

import java.util.*;

public class Hanoi {

    boolean[] check = new boolean[3];
    private final int MAX = 30;
    List<int[]> list = new ArrayList<>();

    public int[][] solution(int n) {
        int[][] answer = {};


        int[][] map = new int[n][3];

        dfs(map, 1,-1,-1, new ArrayList<>(), n);


        return answer;
    }

    private void dfs(int[][] map, int topA, int topB, int topC, List<int[]> list, int n) {
        if (topC + 1 == n) {

            return ;
        }
    }
}
