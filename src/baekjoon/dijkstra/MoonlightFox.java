package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MoonlightFox {

    private static int n, m;
    private static List<List<int[]>> links;
    private static int[] fox;
    private static int[][] wolf;
    private static final int BIG_NUMBER = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        links = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(new int[]{b, 2 * cost});   // wolf 의 경우 *2와 /2를 해주기 때문에 int형으로 계산하기 위해 2를 미리 곱해둔다
            links.get(b).add(new int[]{a, 2 * cost});
        }

        // 여우가 2~n번 노드까지 도착하는 최소 시간 찾고
        dijkstraFox();

        // 늑대가 2~n번 노드까지 도착하는 최소 시간 찾고
        dijkstraWolf();

        // 여우가 늑대보다 빨리 도착하는 노드의 수 계산
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (fox[i] < wolf[0][i] && fox[i] < wolf[1][i]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dijkstraWolf() {   // true면 부스트 사용한 상태이므로 1/2 속도, false면 부스트 사용할 수 있으므로 2배의 속도
        wolf = new int[2][n + 1];   // wolf[0] 은 부스트 미사용, wolf[1]은 부스트 사용
        for (int[] ints : wolf) {
            Arrays.fill(ints, BIG_NUMBER);
        }

        Queue<Wolf> queue = new PriorityQueue<>();
        queue.add(new Wolf(1, 0, 0));
//        wolf[0][1] = wolf[1][1] = 0;  // 처음 문제 풀때는 아무 생각없이 1번 노드를 0으로 초기화 했지만, 늑대는 한바퀴 돌아서 1번 노드로 온 뒤에 움직이는게 더 빠를때가 있다..
        wolf[0][1] = 0;

        while (!queue.isEmpty()) {
            Wolf current = queue.poll();
            int currentNode = current.node;

            if (wolf[current.boostUsed][currentNode] < current.dist) {
                continue;
            }

            for (int[] next : links.get(currentNode)) {
                int nextNode = next[0];
                int distFromCurrentToNext = next[1];

                if (current.boostUsed == 0) {
                    distFromCurrentToNext /= 2;
                } else {
                    distFromCurrentToNext *= 2;
                }

                int nextNodeBoostUsed = 1-current.boostUsed;

                if (wolf[nextNodeBoostUsed][nextNode] > wolf[current.boostUsed][currentNode] + distFromCurrentToNext) {
                    wolf[nextNodeBoostUsed][nextNode] = wolf[current.boostUsed][currentNode] + distFromCurrentToNext;
                    queue.add(new Wolf(nextNode, wolf[nextNodeBoostUsed][nextNode], nextNodeBoostUsed));
                }
            }
        }
    }

    private static void dijkstraFox() {
        fox = new int[n + 1];

        Arrays.fill(fox, BIG_NUMBER);

        Queue<Fox> queue = new PriorityQueue<>();
        queue.add(new Fox(1,0));
        fox[1] = 0;

        while (!queue.isEmpty()) {
            Fox current = queue.poll();
            int currentNode = current.node;

            if (fox[currentNode] < current.dist) {
                continue;
            }

            for (int[] next : links.get(currentNode)) {
                int nextNode = next[0];
                int distFromCurrentToNext = next[1];

                if (fox[nextNode] > fox[currentNode] + distFromCurrentToNext) {
                    fox[nextNode] = fox[currentNode] + distFromCurrentToNext;
                    queue.add(new Fox(nextNode, fox[nextNode]));
                }
            }
        }
    }

    static class Fox implements Comparable<Fox>{
        int node;
        int dist;

        public Fox(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fox o) {
            return dist - o.dist;
        }
    }

    static class Wolf implements Comparable<Wolf>{
        int node;
        int dist;
        int boostUsed;  // 1이면 부스트 사용. 0이면 부스트 미사용

        Wolf(int node, int dist, int boostUsed) {
            this.node = node;
            this.dist = dist;
            this.boostUsed = boostUsed;
        }

        @Override
        public int compareTo(Wolf o) {
            return this.dist - o.dist;
        }
    }
}
