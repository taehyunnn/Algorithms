import java.util.Arrays;

class Main {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;


        for (int i = 0; i <= stones.length - k; i++) {
            int temp = i;
            int stone = stones[i];
            for (int j = i; j < i + k; j++) {
                if (stones[j] > stone) {
                    stone = stones[j];
                    temp = j;
                }
            }
            if (answer > stone) {
                answer = stone;
            }
            i = temp;

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Main().solution(new int[]{2}, 2));
    }
}