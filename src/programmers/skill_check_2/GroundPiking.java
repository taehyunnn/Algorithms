package programmers.skill_check_2;

public class GroundPiking {
    int solution(int[][] land) {
        int answer = 0;

        int[][] dp = new int[land.length][4];

        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = Math.max( Math.max(dp[i-1][(j+1)%4], dp[i-1][(j+2)%4]), dp[i-1][(j+3)%4]) + land[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[dp.length-1][i]);
        }

        return answer;
    }
}
