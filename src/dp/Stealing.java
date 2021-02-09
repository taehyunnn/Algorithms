package dp;

public class Stealing {
    public int solution(int[] money) {
        int answer = 0;

        int[][] dp = new int[2][money.length];

        // 0행 : 0번집 선택 안함, 1행 : 0번집 선택함
        dp[0][0] = 0;
        dp[1][0] = money[0];
        dp[0][1] = money[1];
        dp[1][1] = Math.max(dp[1][0], money[1]);

        for (int i = 2; i < money.length; i++) {
            dp[0][i] = Math.max(dp[0][i-2] + money[i] , dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-2] + money[i] , dp[1][i-1]);
        }

        answer = Math.max(dp[0][money.length-1], dp[1][money.length-2]);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Stealing().solution(new int[]{1,1,7,1,1,7,1,1,7}));
    }
}
