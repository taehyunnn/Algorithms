package summer_winter;

import java.util.Arrays;

public class NumberGame {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0, j = 0; j < B.length; j++) {
            if (A[i] < B[j]) {
                i++;
                answer++;
            }
        }

        return answer;
    }
}
