package devcourse;

public class Q1 {
    public int solution(int[] d, int m) {
        int answer = 0;
        int currentCnt = 1;

        for (int value : d) {
            answer++;

            if (value >= currentCnt) {
                m -= currentCnt;
                currentCnt *= 2;
            } else {
                currentCnt = 1;
            }

            // 전송 끝
            if (m <= 0) {
                break;
            }
        }

        if (m > 0) {
            answer = -1;
        }

        return answer;
    }
}
