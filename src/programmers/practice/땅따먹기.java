package programmers.practice;

public class 땅따먹기 {
    private int[][] dp;

    int solution(int[][] land) {
        dp = new int[land.length][land[0].length];

        dp[0] = land[0];

        for(int i=1; i<land.length; i++){
            int[] oneLand  = land[i];

            int temp =0;
            for(int j=0; j<land[0].length; j++){
                dp[i][j] = Math.max(dp[i-1][(j+1)%4] , Math.max(dp[i-1][(j+2)%4], dp[i-1][(j+3)%4]));
                dp[i][j] += land[i][j];
            }

        }

        int temp =0;
        for(int i=0; i<land[0].length; i++){
            temp = Math.max(temp, dp[dp.length-1][i]);
        }

        return temp;
    }
}
