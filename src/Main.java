import java.util.ArrayList;
class Main {
    int min = Integer.MAX_VALUE;
    int[] weak;
    int[] permutationTemp;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = -1;

        this.weak = weak;
        permutationTemp = new int[dist.length];

        // 배열 shift
        for (int i = 0; i < weak.length; i++) {
            permute(0, dist.length, dist, new boolean[dist.length]);
            shift(weak, n);
        }

        if (min != Integer.MAX_VALUE) {
            answer = min;
        }

        return answer;
    }

    private void calc(int[] dist) {
        int checkWeak = 0;
        int currentUseCnt = 0;
        int index = 0;

        // 섞인 친구 순서대로 계산
        for (int j = 0; j < dist.length; j++) {
            int current = 0;

            for (int k = index; k < weak.length; k++) {
                if (weak[index] + dist[j] >= weak[k]) {
                    current++;
                } else {
                    index = k;
                    break;
                }
            }
            checkWeak += current;
            if (checkWeak == weak.length) {
                currentUseCnt = j + 1;
                break;
            }
        }

        if (checkWeak == weak.length) {
            min = Math.min(min, currentUseCnt);
        }
    }

    private void permute(int current, int target, int[] dist, boolean[] visit) {
        if (current == target) {
            calc(permutationTemp);
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visit[i]) {
                continue;
            }

            visit[i] = true;
            permutationTemp[current] = dist[i];
            permute(current + 1, target, dist, visit);
            visit[i] = false;
        }
    }

    private void shift(int[] weak, int n) {
        int temp = weak[0];
        System.arraycopy(weak, 1, weak, 0, weak.length - 1);
        weak[weak.length - 1] = temp + n;
    }
}