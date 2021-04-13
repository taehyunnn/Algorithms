package programmers.kakao_2021;

public class Robot {

    private int[][] needs;
    private boolean[] buy;
    private int result;

    public int solution(int[][] needs, int r) {
        int answer = 0;

        this.needs = needs;
        buy = new boolean[needs[0].length];

        combine(-1, 0, r);

        System.out.println(result);
        return result;
    }

    private void combine(int index, int depth, int targetDepth) {
        if (depth == targetDepth) {
            result = Math.max(result, calc());
            return;
        }

        for (int i = index + 1; i < buy.length; i++) {
            buy[i] = true;
            combine(i, depth + 1, targetDepth);
            buy[i] = false;
        }
    }

    private int calc() {
        int result = 0;

        // 완제품 별로 완성이 가능한지 체크
        for (int i = 0; i < needs.length; i++) {
            // 제품의 부품별로 필요한 부품을 생산할 수 있는 기계가 모두 있으면 생산 가능
            if (canBeMade(needs[i])) {
                result++;
            }
        }

        return result;
    }

    private boolean canBeMade(int[] need) {
        for (int j = 0; j < need.length; j++) {
            if (need[j] == 1 && !buy[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Robot().solution(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1}}, 2);
    }
}
