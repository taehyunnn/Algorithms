package kakao_2020;

public class WallInspection {

    private int[] dist, distTemp;
    private int[] weak;
    private int min = 999999;

    public int solution(int n, int[] weak, int[] dist) {
        this.dist = dist;
        distTemp = new int[dist.length];
        this.weak = weak;

        for (int i = 0; i < weak.length; i++) {
            permute(0, dist.length, new boolean[dist.length]);
            shift(weak.length, n);
        }

        return min == 999999 ? -1 : min;
    }

    private void permute(int currentNum, int targetNum, boolean[] visit) {
        if (currentNum == targetNum) {
            check();
            return;
        }
        for (int i = 0; i < targetNum; i++) {
            if (visit[i]) {
                continue;
            }

            visit[i] = true;
            distTemp[currentNum] = dist[i];
            permute(currentNum + 1, targetNum, visit);
            visit[i] = false;
        }
    }

    private void check() {
        // shift한 weak 와, permute한 temp 를 가지고 몇 명의 사람이 필요한지 계산
        int sumOfRepair = 0;
        int weakIndex = 0;

        for (int i = 0; i < distTemp.length; i++) {
            int currentRepair = 0;
            for (int j = weakIndex; j < weak.length ; j++) {
                if (weak[weakIndex] + distTemp[i] >= weak[j]) {
                    currentRepair++;
                } else {
                    weakIndex = j;
                    break;
                }
            }

            sumOfRepair += currentRepair;
            if (sumOfRepair == weak.length) {
                min = Math.min(min, i+1);
                break;
            }
        }
    }

    private void shift(int length, int n) {
        int temp = weak[0];
        System.arraycopy(weak, 1, weak, 0, length - 1);
        weak[length - 1] = temp + n;
    }

    public static void main(String[] args) {
        System.out.println(new WallInspection().solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(new WallInspection().solution(12, new int[]{0, 10}, new int[]{1, 2}));
        System.out.println(new WallInspection().solution(200, new int[]{0, 100}, new int[]{1, 1}));
        System.out.println(new WallInspection().solution(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 10, 5, 40, 30}));
    }
}
