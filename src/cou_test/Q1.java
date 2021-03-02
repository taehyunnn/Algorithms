package cou_test;

public class Q1 {
    public int[] solution(int n) {
        int[] answer = new int[2];

        // 2~9진법
        for (int i = 2; i < 10; i++) {

            int temp = n;
            int sum = 1;

            // n진법으로 변환
            while (temp != 0) {
                sum *= temp % i == 0 ? 1 : temp % i;
                temp /= i;
            }

            if (sum >= answer[1]) {
                answer[1] = sum;
                answer[0] = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] solution = new Q1().solution(14);
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}
