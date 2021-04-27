package programmers.practice;

public class 가장큰정사각형찾기 {
    private int[][] dp;

    public int solution(int [][]board)
    {
        int answer = 1234;

        dp = new int[board.length][board[0].length];

        for(int i=0; i<board.length; i++){
            dp[i][0] = board[i][0];
        }

        for(int i=0; i<board[0].length; i++){
            dp[0][i] = board[0][i];
        }

        for(int i=1; i<board.length; i++){
            for(int j=1; j<board[0].length; j++){


                if(board[i][j] == 1){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                    dp[i][j] ++;
                }

            }
        }
        answer = findMaxLength();
        return answer * answer;
    }

    public int findMaxLength(){
        int max = 0;

        for(int[] ints : dp){
            for(int a : ints){
                max = Math.max(max, a);
            }
        }

        return max;
    }
}
