package programmers.practice;

public class ExpectedSheet {
    public int solution(int n, int a, int b) {
        int answer = 1;

        if (a > b) {
            int temp = b;
            b = a;
            a = temp;
        }

        for (int i = 0; i < 20; i++) {
            if (isMeeted(a, b)) {
                break;
            }
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(new ExpectedSheet().solution(8, 4, 7));
        System.out.println(new ExpectedSheet().solution(8, 7, 4));
    }

    private boolean isMeeted(int a, int b) {
        return a + 1 == b && a % 2 == 1;
    }
}
