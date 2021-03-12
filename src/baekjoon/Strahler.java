package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Strahler {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int testCnt = Integer.parseInt(stringTokenizer.nextToken());
        int testNum;
        int nodeCnt;
        int edgeCnt;

        int[][] board;

        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            testNum = Integer.parseInt(stringTokenizer.nextToken());
            nodeCnt = Integer.parseInt(stringTokenizer.nextToken());
            edgeCnt = Integer.parseInt(stringTokenizer.nextToken());

            board = new int[nodeCnt + 1][nodeCnt + 1];  // 0번 노드는 없다.

            for (int j = 0; j < edgeCnt; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                board[Integer.parseInt(stringTokenizer.nextToken())][Integer.parseInt(stringTokenizer.nextToken())] = 1;
            }

            System.out.println(testNum + " " + start(board));
        }
    }

    private static int start(int[][] board) {
        int[] orders = new int[board.length];
        Queue<Node> queue = new LinkedList<>();

        // 레벨 1 찾기
        for (int i = 1; i < board.length; i++) {
            boolean flag = true;
            for (int j = 1; j < board.length; j++) {
                if (board[j][i] == 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                orders[i] = 1;
                queue.add(new Node(i, 1));
            }
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            List<Node> temp = new ArrayList<>();

            // current에서 outgoing 노드 다 찾기
            for (int i = 1; i < board.length; i++) {
                if (board[current.num][i] == 1) {
                    temp.add(new Node(i, current.order));
                }
            }

            // 찾은 노드들의 order 계산
            for (Node node : temp) {
                int maxOrder = 0;
                int maxOrderCnt = 0;

                for (int i = 1; i < board.length; i++) {
                    if (board[i][node.num] == 1) {
                        if (orders[i] > maxOrder) {
                            maxOrder = orders[i];
                            maxOrderCnt = 1;
                        } else if (orders[i] == maxOrder) {
                            maxOrderCnt++;
                        }
                    }
                }

                if (maxOrderCnt > 1) {
                    node.order = maxOrder + 1;
                } else {
                    node.order = maxOrder;
                }

                orders[node.num] = node.order;
                queue.add(node);
            }
        }

        int max = 0;
        for (int i = 1; i < orders.length; i++) {
            max = Math.max(max, orders[i]);
        }

        return max;
    }

    static class Node {
        int num;
        int order;

        Node(int num, int order) {
            this.num = num;
            this.order = order;
        }
    }
}
