package programmers.monthly_code_challenge;

public class BalloonPop {
    public int solution(int[] a) {
        int answer = 2;

        int length = a.length;
        int[] left = new int[length];
        int[] right = new int[length];

        left[0] = a[0];
        for (int i = 1; i < length; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
        }

        right[length -1] = a[length -1];
        for (int i = length -2; i >=0 ; i--) {
            right[i] = Math.min(right[i + 1], a[i]);
        }

        for (int i = 1; i < length-1 ; i++) {
            if (a[i] < left[i - 1] || a[i] < right[i + 1]) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new BalloonPop().solution(new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}));
//        System.out.println(new BalloonPop().solution(new int[]{9, -1, -5, 6}));
    }
}
