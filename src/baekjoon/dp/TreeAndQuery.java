package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeAndQuery {

    private static List<List<Integer>> links = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int node = Integer.parseInt(stringTokenizer.nextToken());
        int rootNum = Integer.parseInt(stringTokenizer.nextToken());
        int queryNum = Integer.parseInt(stringTokenizer.nextToken());

        dp = new int[node + 1];
        for (int i = 0; i <= node; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < node - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            links.get(a).add(b);
            links.get(b).add(a);
        }

        start(rootNum, -1);

        for (int i = 0; i < queryNum; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            System.out.println(dp[Integer.parseInt(stringTokenizer.nextToken())]);
        }
    }

    private static int start(int currentNode, int preNode) {
        dp[currentNode] = 1;

        for (Integer next : links.get(currentNode)) {
            if (next == preNode) {
                continue;
            }
            dp[currentNode] += start(next, currentNode);
        }

        return dp[currentNode];
    }


}
