package programmers.stack_and_queue;

public class Stack_Queue_1 {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++;
                if (prices[i] > prices[j])
                    break;
            }
        }

        return answer;
    }
}
