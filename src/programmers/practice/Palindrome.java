package programmers.practice;

public class Palindrome {
    public int solution(String s) {
        int answer = 1;

        int length = s.length();
        boolean[][] dp = new boolean[length+1][length+1];

        for (int i = 0; i < length-1; i++) {
            dp[i][i] = true;
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i+1] = true;
                answer = 2;
            }
        }
        dp[length-1][length-1] = true;

        // i는 단어 사이의 거리
        for (int i = 2; i < length ; i++) {
            for (int j = 0; j + i < length; j++) {
                dp[j][j + i] = (s.charAt(j) == s.charAt(j + i)) && dp[j + 1][j + i - 1];
                if (dp[j][j + i]) {
                    answer = Math.max(answer, i+1);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Palindrome().solution("aa"));
    }
}
