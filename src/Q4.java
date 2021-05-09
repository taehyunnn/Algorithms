import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q4 {

    private final int SIZE = 1001;

    private int n;
    private boolean[] trapCheck;
    private int[][] links;
    private int[] visit;

    private int answer;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        init(n, traps, roads);

        maze(start, end, 0);

        return answer;
    }

    private boolean maze(int start, int end, int distance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visit[start]++;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNum = current[0];
            int currentDistance = current[1];

            for (int i = 1; i <= n; i++) {
                if (links[currentNum][i] > 0) {

                }
            }
        }

        return false;
    }

    private void init(int n, int[] traps, int[][] roads) {
        this.n = n;

        visit = new int[n + 1];
        trapCheck = new boolean[SIZE];
        for (int trap : traps) {
            trapCheck[trap] = true;
        }

        // links 초기화
        links = new int[n + 1][n + 1];

        for (int[] road : roads) {
            links[road[0]][road[1]] = road[2];
        }
    }
}
