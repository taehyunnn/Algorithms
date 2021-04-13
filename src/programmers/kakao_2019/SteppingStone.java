package programmers.kakao_2019;


public class SteppingStone {
    public int solution(int[] stones, int k) {
        int answer = stones[0];

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int stone : stones) {
            min = Math.min(stone, min);
            max = Math.max(stone, max);
        }

        while (min < max) {
            int mid = (min + max) / 2;

            if (isPossible(stones, mid, k)) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return answer;
    }

    private boolean isPossible(int[] newStones, int mid, int k) {
        int cnt = 0;
        for (int newStone : newStones) {
            if (newStone - mid < 0) {
                cnt++;
            } else {
                cnt = 0;
            }

            if (cnt >= k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SteppingStone().solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
//        System.out.println(new SteppingStone().solution(new int[]{2, 4, 5, 9}, 2));
    }
}
