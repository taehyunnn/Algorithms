package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름 {

    private static List<List<int[]>> links;
    private static int result;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1);
        System.out.println(result);
    }

    private static int dfs(int nodeNum) {
        int currentMax = 0, currentSecondMax = 0;

        int cost = 0;

        for (int[] nextNode : links.get(nodeNum)) {
            int nextNodeNum = nextNode[0];
            int nextNodeCost = nextNode[1];

            int tempCost = dfs(nextNodeNum) + nextNodeCost;

            if (currentMax < tempCost) {
                currentSecondMax = currentMax;
                currentMax = tempCost;
                cost = currentMax;
            } else if (currentSecondMax < tempCost) {
                currentSecondMax = tempCost;
            }

            result = Math.max(result, currentMax + currentSecondMax);
        }

        return cost;
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        links = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(new int[]{b, cost});
        }
    }
}
