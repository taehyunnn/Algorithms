package baekjoon;

import java.util.*;

public class SNS {
    private static List<List<Integer>> links;
    public static int[][] dp;
    public static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        links = new ArrayList<>();
        dp = new int[n +1][2];

        // 정점 N개의 인접 리스트 생성
        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for(int i = 0; i < n -1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            links.get(a).add(b);
            links.get(b).add(a);
        }

        // 1번을 root 노드로, 루트 노드는 부모가 없으니까 임시로 -1
        dfs(1,-1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int current, int parent) {
        dp[current][0] = 0; // 현재 노드가 얼리 어답터가 아니면 0
        dp[current][1] = 1; // 현재 노드가 얼리 어답터면 1


        // 자식 노드 재귀로 검사
        for (Integer child : links.get(current)) {
            if (child == parent) {  // 부모는 검사하지 않는다. (양방향이기 때문에)
                continue;
            }

            dfs(child,current);
            dp[current][0] += dp[child][1] ;    // 현재 노드가 얼리어답터가 아니므로 자식들은 모두 얼리어답터여야 한다.
            dp[current][1] += Math.min(dp[child][0], dp[child][1]); // 현재가 얼리어답터면 자식은 얼리어답터여도 되고 아니어도 된다. 따라서 그 중 적은거 더해줌
        }
    }

}
