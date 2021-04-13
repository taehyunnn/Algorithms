package programmers.skill_check_2;

public class TimeCheck {
    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length-1; i++) {
            int cnt = 0;
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] <= prices[j]) {
                    cnt++;
                } else{
                    cnt++;
                    break;
                }
            }
            answer[i] = cnt;
        }
        answer[answer.length-1] = 0;

        return answer;
    }
}
