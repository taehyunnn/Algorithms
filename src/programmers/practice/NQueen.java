package programmers.practice;

public class NQueen {
    private int answer = 0;
    private boolean[] col;
    private boolean[] diagonalPlus;
    private boolean[] diagonalMinus;


    public int solution(int n) {
        col = new boolean[n];
        diagonalPlus = new boolean[2*n];
        diagonalMinus = new boolean[2*n];

        dfs(0, n,0);

        return answer;
    }

    private void dfs(int current, int n, int rowNum) {
        if (current == n) {
            answer++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if (col[j] || diagonalPlus[rowNum + j] || diagonalMinus[n + rowNum - j]) {
                continue;
            }
            col[j] = diagonalPlus[rowNum+j] = diagonalMinus[n +rowNum -j] = true;
            dfs(current + 1, n, rowNum+1);
            col[j] = diagonalPlus[rowNum+j] = diagonalMinus[n +rowNum -j] = false;
        }
    }


    public static void main(String[] args) {
        System.out.println(new NQueen().solution(4));
    }
}
