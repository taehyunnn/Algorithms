package programmers.kakao_2019;

import java.util.*;

public class FindPathGame {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        // 노드의 관계를 먼저 알아내고

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        list.sort(((o1, o2) -> -(o1.y - o2.y)));

        for (int i = 1; i < list.size(); i++) {
            insertNode(list.get(0), list.get(i));
        }


        // 전위순회, 후위순회
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        preOrder(list.get(0), pre);
        postOrder(list.get(0), post);

        answer[0] = pre.stream().mapToInt(i -> i).toArray();
        answer[1] = post.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    private void postOrder(Node node, List<Integer> post) {
        if (node == null) {
            return;
        }

        postOrder(node.leftNode, post);
        postOrder(node.rightNode, post);
        post.add(node.num);
    }

    private void preOrder(Node node, List<Integer> pre) {
        if (node == null) {
            return ;
        }
        pre.add(node.num);
        preOrder(node.leftNode, pre);
        preOrder(node.rightNode, pre);
    }

    private void insertNode(Node parentNode, Node insertNode) {
        if (parentNode.x < insertNode.x) {
            if (parentNode.rightNode == null) {
                parentNode.rightNode = insertNode;
            } else {
                insertNode(parentNode.rightNode, insertNode);
            }
        } else {
            if (parentNode.leftNode == null) {
                parentNode.leftNode = insertNode;
            } else {
                insertNode(parentNode.leftNode, insertNode);
            }
        }
    }

    static class Node {
        int num;
        int x;
        int y;
        Node leftNode;
        Node rightNode;

        Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;

        }
    }
}
