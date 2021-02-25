package kakao_2017;

public class PedestrianHeaven {

    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m+2][n+2];
        int[][] newCityMap = new int[m+2][n+2];

        for (int i = 2; i < m+2 ; i++) {
            for (int j = 2; j < n+2 ; j++) {
                newCityMap[i][j] = cityMap[i-2][j-2];
            }
        }
        dp[2][2] = 1;

        for (int i = 2; i < m + 2; i++) {
            for (int j = 2; j < n + 2; j++) {
                if (newCityMap[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                if(newCityMap[i-1][j] == 0) {
                    dp[i][j] += dp[i-1][j];
                } else if(newCityMap[i-1][j] == 2){
                    dp[i][j] += findVerticalStraight(newCityMap, dp, i-2, j);
                }

                if (newCityMap[i][j - 1] == 0) {
                    dp[i][j] += dp[i][j-1];
                } else if (newCityMap[i][j - 1] == 2) {
                    dp[i][j] += findHorizontalStraight(newCityMap, dp, i,j-2);
                }
                dp[i][j] = (dp[i][j] % MOD);
            }
        }
        return dp[m+1][n+1];
    }

    private int findHorizontalStraight(int[][] newCityMap, int[][] dp, int i, int j) {
        for (int k = j; k >=0 ; k--) {
            if (newCityMap[i][k] == 0 || dp[i][k] == 0) {
                return dp[i][k];
            }
        }
        return -1;
    }

    private int findVerticalStraight(int[][] newCityMap, int[][] dp, int i, int j) {
        for (int k = i; k >=0 ; k--) {
            if (newCityMap[k][j] == 0 || dp[k][j] == 0) {
                return dp[k][j];
            }
        }
        return -1;
    }
}
