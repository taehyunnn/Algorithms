package programmers.dp;

public class TheWayGoToSchool {

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n+1][m+1];   // 인덱스는 (1,1) 부터 시작

        dp[0][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(checkPuddles(i,j,puddles)){
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
        }

        answer = (dp[n][m] % 1000000007);
        return answer;
    }

    private boolean checkPuddles(int i, int j, int[][] puddles) {
        for (int k = 0; k < puddles.length; k++) {
            if(puddles[k][0] == j && puddles[k][1] == i){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new TheWayGoToSchool().solution(4,3, new int[][]{{2,2}}));
    }
}
