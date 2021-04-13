package programmers.summer_winter;

public class CollectingStickers {
    public int solution(int[] sticker) {
        if (sticker.length == 1) {
            return sticker[0];
        }

        int[][] dp = new int[sticker.length][2];

        // 0 열 : 0번 안 찢음, 1열 : 0번 찢음
        dp[0][0] = 0;
        dp[1][0] = sticker[1];

        dp[0][1] = sticker[0];
        dp[1][1] = Math.max(sticker[0], sticker[1]);

        for (int i = 2; i < sticker.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + sticker[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + sticker[i]);
        }

        return Math.max(dp[sticker.length-1][0], dp[sticker.length-2][1]);
    }
}
