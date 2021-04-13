package programmers.practice;

import java.util.Arrays;

public class BestSet {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (n > s) {
            return new int[]{-1};
        }

        int temp = s/n;

        Arrays.fill(answer, temp);
        int gap = s - temp * n;

        for (int i = 0; i < gap; i++) {
            answer[answer.length - 1 - i] ++;
        }

        return answer;
    }
}
