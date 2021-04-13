package programmers.skill_check_2;

public class PrimNumber {

    private int answer = 0;

    public int solution(int[] nums) {

        boolean[] visit = new boolean[nums.length];
        int[] result = new int[3];

        combine(nums, result, visit, -1, 0, 3);

        return answer;
    }

    private void combine(int[] nums, int[] result, boolean[] visit, int index, int current, int target) {
        if (current == target) {
            System.out.println(result[0] + " "+ result[1] +" "+ result[2]);
            int checkNum = result[0] + result[1] + result[2];
            if (isPrimeNumber(checkNum)) {
                answer++;
            }
            return;
        }

        for (int i = index+1; i < nums.length; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            result[current] = nums[i];
            combine(nums, result, visit, i, current + 1, target);
            visit[i] = false;
        }
    }

    private boolean isPrimeNumber(int checkNum) {

        if (checkNum % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(checkNum); i += 2) {
            if (checkNum % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PrimNumber().solution(new int[]{1, 2, 3, 4});
    }
}
