package cou_test;

import java.util.HashMap;
import java.util.Map;

public class Q3 {
    public int solution(int k, int[] scores) {
        int answer = 0;

        int[] gap = new int[scores.length - 1];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < scores.length - 1; i++) {
            gap[i] = scores[i] - scores[i + 1];
            map.put(gap[i], map.getOrDefault(gap[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue() >= k) {
                for (int i = 0; i < gap.length; i++) {
                    if (gap[i] == integerIntegerEntry.getKey()) {
                        scores[i] = scores[i + 1] = 0;
                    }
                }
            }
        }

        for (int score : scores) {
            if (score != 0) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q3().solution(2, new int[]{1300000000,700000000,668239490,618239490,568239490,568239486,518239486,157658638,157658634,100000000,100}));
    }
}
