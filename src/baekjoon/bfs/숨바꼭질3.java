package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질3 {

    private static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        visit = new int[100001];
        Arrays.fill(visit, 100000);

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(n, 0));
        visit[n] = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            int num = poll.num;
            int moveCnt = poll.moveCnt;

//
//            if (num == k) {
//                System.out.println(moveCnt);
//                break;
//            }

            checkNextNode(queue, num * 2, moveCnt);
            checkNextNode(queue, num - 1, moveCnt + 1);
            checkNextNode(queue, num + 1, moveCnt + 1);
        }

        System.out.println(visit[k]);
    }

    private static void checkNextNode(Queue<Node> queue, int num, int moveCnt) {
        if (num >= 0 && num < visit.length && visit[num] > moveCnt) {
            visit[num] = moveCnt;
            queue.add(new Node(num, moveCnt));
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        int moveCnt;

        public Node(int num, int moveCnt) {
            this.num = num;
            this.moveCnt = moveCnt;
        }

        @Override
        public int compareTo(Node o) {
            return moveCnt - o.moveCnt;
        }
    }
}
