package devcourse;

import java.util.ArrayList;
import java.util.List;

public class Q3 {

    private int[] childNodeNums;
    private List<List<Integer>> links;

    public int solution(int n, int[][] wires) {
        int answer = n;

        init(n, wires);

        makeChildNodeNumArray(1, -1);

        for (int i = 2; i < childNodeNums.length; i++) {
            answer = Math.min(answer, Math.abs(n - 2 * childNodeNums[i]));
        }

        return answer;
    }

    private void init(int n, int[][] wires) {
        childNodeNums = new int[n + 1];
        links = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            links.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            links.get(a).add(b);
            links.get(b).add(a);
        }
    }

    private int makeChildNodeNumArray(int currentNode, int preNode) {
        childNodeNums[currentNode] = 1;

        for (Integer nextNode : links.get(currentNode)) {
            if (nextNode == preNode) {
                continue;
            }

            childNodeNums[currentNode] += makeChildNodeNumArray(nextNode, currentNode);
        }

        return childNodeNums[currentNode];
    }

    public static void main(String[] args) {
//        new Q3().solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}});
        new Q3().solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}});

    }
}
