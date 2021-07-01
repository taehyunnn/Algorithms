package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름2 {

    private static List<List<int[]>> links;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1, -1);

        System.out.println(answer);
    }

    private static int dfs(int node, int preNode) {
        int first = 0, second = 0;

        for (int[] next : links.get(node)) {
            int nextNode = next[0];
            int cost = next[1];

            if (nextNode == preNode) {
                continue;
            }

            int temp = dfs(nextNode, node) + cost;

            if (temp > first) {
                second = first;
                first = temp;
            } else if (temp > second) {
                second = temp;
            }

            answer = Math.max(answer, first + second);
        }

        return first;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        links = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());

            while (true) {
                int b = Integer.parseInt(stringTokenizer.nextToken());
                if (b == -1) break;

                int cost = Integer.parseInt(stringTokenizer.nextToken());
                links.get(a).add(new int[]{b, cost});
            }
        }
    }
}
