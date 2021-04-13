package programmers.kakao_2018;

import java.util.Arrays;

public class Friends4Block {

    int answer = 0;

    public int solution(int m, int n, String[] board) {

        char[][] map = new char[m][n];
        boolean[][] conditionSatisfied = new boolean[m][n];
        for (int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
        }


        startGame(map, conditionSatisfied);


        return answer;
    }

    private void startGame(char[][] map, boolean[][] conditionSatisfied) {
        if(!check22block(map, conditionSatisfied))
            return;

        moveBlockDown(map, conditionSatisfied);

        for (boolean[] booleans : conditionSatisfied) {
            Arrays.fill(booleans, false);
        }
        startGame(map,conditionSatisfied);
    }

    private void moveBlockDown(char[][] map, boolean[][] conditionSatisfied) {
        for (int i = 0; i < map[0].length; i++) {
            int k = map.length - 1;
            for (int j = map.length -1; j >= 0 ; j--) {
                if(!conditionSatisfied[j][i]){
                    map[k--][i] = map[j][i];
                }
            }

            while (k >= 0) {
                map[k--][i] = '0';
            }
        }
    }

    private boolean check22block(char[][] map, boolean[][] conditionSatisfied) {
        boolean flag = false;
        // 이번 단계 깨뜨릴 블록 찾기
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length - 1; j++) {
                if (map[i][j] == '0') {
                    continue;
                }

                if(map[i][j] == map[i][j+1] && map[i][j] == map[i+1][j] && map[i][j] == map[i+1][j+1]){
                    flag = true;
                    markCondition(conditionSatisfied,i,j);
                }
            }
        }
        return flag;
    }

    private void markCondition(boolean[][] conditionSatisfied, int i, int j) {
        for (int k = i; k <= i+1; k++) {
            for (int l = j; l <= j+1 ; l++) {
                if(!conditionSatisfied[k][l]){
                    answer++;
                    conditionSatisfied[k][l] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Friends4Block().solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }
}
