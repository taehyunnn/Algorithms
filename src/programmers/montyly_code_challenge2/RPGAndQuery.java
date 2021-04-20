package programmers.montyly_code_challenge2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RPGAndQuery {

    private List<List<int[]>> links;

    public long[] solution(int n, int z, int[][] roads, long[] queries) {
        long[] answer = new long[queries.length];

        initLinks(n, roads);

        for (int i = 0; i < queries.length; i++) {

            answer[i] = start(z, queries[i]);
        }

        return answer;
    }

    private long start(int z, long target) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0,false));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.weight == target) {
                return node.moveCnt;
            }

            if (node.weight > target) {
                continue;
            }

            // 다음 노드로 이동 : 가중치만큼 획득
            for (int[] nextNode : links.get(node.num)) {
                int weightToNextNode = nextNode[1];
                int nextNodeNum = nextNode[0];
                queue.add(new Node(nextNodeNum, node.weight + weightToNextNode, node.moveCnt + 1, false));
            }

            // 그 자리에 대기하고 z만큼 획득
            queue.add(new Node(node.num, node.weight+ z, node.moveCnt + 1, false));

            // 텔레포트
            if (!node.teleport) {
                teleport(queue, node);
            }
        }

        return -1;
    }

    private void teleport(Queue<Node> queue, Node node) {
        for (int i = 0; i < links.size(); i++) {
            if (node.num == i) {
                continue;
            }

            queue.add(new Node(i, node.weight, node.moveCnt + 1, true));
        }
    }

    private void initLinks(int n, int[][] roads) {
        links = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            links.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            links.get(road[0]).add(new int[]{road[1], road[2]});
        }
    }

    static class Node {
        int num;
        int weight;
        int moveCnt;
        boolean teleport;

        public Node(int num, int weight, int moveCnt, boolean teleport) {
            this.num = num;
            this.weight = weight;
            this.moveCnt = moveCnt;
            this.teleport = teleport;
        }
    }
}
