package programmers.kakao_2021;

import java.util.ArrayList;
import java.util.List;

public class 매출하락최소화 {

    private List<List<Integer>> teamLinks;
    private int[][] dp;

    public int solution(int[] sales, int[][] links) {
        init(links);

        dfs(1, sales);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int leader, int[] sales) {
        dp[leader][0] = 0;  // 팀장 안 뽑을때
        dp[leader][1] = sales[leader - 1];  // 팀장을 뽑을 때

        if (teamLinks.get(leader).size() == 0) {
            return;
        }


        int sumChild = 0;
        int gap = Integer.MAX_VALUE;
        boolean flag = false;
        for (Integer member : teamLinks.get(leader)) {
            dfs(member, sales);

            sumChild += Math.min(dp[member][0], dp[member][1]);
            gap = Math.min(gap, dp[member][1] - dp[member][0]);

            if (dp[member][0] > dp[member][1]) {
                flag = true;
            }
        }

        if (!flag) {
            dp[leader][0] += gap;
        }

        dp[leader][0] += sumChild;
        dp[leader][1] += sumChild;
    }

    private void init(int[][] links) {
        dp = new int[links.length + 2][2];
        teamLinks = new ArrayList<>(links.length + 2);
        for (int i = 0; i < links.length + 2; i++) {
            teamLinks.add(new ArrayList<>());
        }

        for (int[] link : links) {
            teamLinks.get(link[0]).add(link[1]);
        }
    }

    public static void main(String[] args) {
        new 매출하락최소화().solution(new int[]{5, 6, 5, 1, 4}, new int[][]{{2, 3}, {1, 4}, {2, 5}, {1, 2}});
    }
}
