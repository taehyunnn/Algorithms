package programmers.montyly_code_challenge2;

import java.util.ArrayList;
import java.util.List;

public class 모두0으로만들기 {

    private List<List<Integer>> links;
    private int[] a;
    private long answer;

    public long solution(int[] a, int[][] edges) {
        this.a = a;
        initLinks(edges);

        // 가능하다면 최소 몇 번으로 가능한지 판별
        start(0, 0);

        if (a[0] == 0) {
            return answer;
        } else {
            return -1;
        }
    }

    private void start(int node, int preNode) {
        for (Integer childNode : links.get(node)) {
            if (childNode == preNode) {
                continue;
            }

            start(childNode, node);
        }

        a[preNode] += a[node];
        answer += Math.abs(a[node]);
    }

    private void initLinks(int[][] edges) {
        links = new ArrayList<>(edges.length);
        for (int i = 0; i < a.length; i++) {
            links.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            links.get(edge[0]).add(edge[1]);
            links.get(edge[1]).add(edge[0]);
        }
    }
}
