package graph;

import java.util.*;

public class Rank {
    public int solution(int n, int[][] results) {
        int answer = 0;

        final int MAX = 999999999;
        int[][] map = new int[n+1][n+1];

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], MAX);
            map[i][i] = 0;
        }

        for (int[] result : results) {
            map[result[0]][result[1]] = 1;
        }

        for (int k = 1; k < map.length; k++) {
            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map.length; j++) {
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                }
            }
        }


        for (int i = 1; i < map.length; i++) {
            boolean flag = true;
            for (int j = 1; j < map.length; j++) {
                if(i==j) continue;

                if(!(map[i][j] == 1 || map[j][i] == 1)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer++;
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Rank().solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }
}
