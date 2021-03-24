import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
    static int[] sum;
    static int[][] dp;
    static boolean [][]check;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1];
        dp = new int[N + 1][N + 1];
        check = new boolean[N + 1][N + 1];

        int table[] = new int[N + 1];
        for (int i = 0; i < N; i++)
            table[i] = Integer.parseInt(bf.readLine());
        for (int i = 1; i <= N; i++)
            sum[i] = sum[i - 1] + table[i - 1];
        for (int i = 0 ; i<=N;i++) {
            for(int j = 0 ; j<=N;j++)
                dp[i][j]=Integer.MIN_VALUE/2;
        }
        System.out.println(d_dp(N, M));
    }
    public static int d_dp(int x, int y) {
        if(y==0) return 0;

        if(x<0) return Integer.MIN_VALUE/2;
        if (check[x][y]) return dp[x][y];
        check[x][y]=true;
        dp[x][y]= d_dp(x-1,y);
        for (int i = x; i > 0; i--) {
            dp[x][y] = Math.max(dp[x][y], d_dp(i - 2, y - 1) + sum[x] - sum[i - 1]);
        }
        return dp[x][y];
    }

}