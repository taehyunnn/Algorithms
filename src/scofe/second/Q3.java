package scofe.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q3 {

    private static List<List<Integer>> links;
    private static int[] entry;
    private static int[] breakaway;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int questionCnt = Integer.parseInt(stringTokenizer.nextToken());

        links = new ArrayList<>(n + 1);
        entry = new int[n + 1];
        breakaway = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(stringTokenizer.nextToken());
            int down = Integer.parseInt(stringTokenizer.nextToken());

            links.get(up).add(down);
        }

        start(1, 0);

        for (int i = 0; i < questionCnt; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(stringTokenizer.nextToken());
            int down = Integer.parseInt(stringTokenizer.nextToken());

            if (entry[up] < entry[down] && breakaway[up] > breakaway[down]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static int start(int currentNode, int index) {
        entry[currentNode] = ++index;

        for (Integer next : links.get(currentNode)) {
            index = start(next, index);
        }

        breakaway[currentNode] = ++index;

        return index;
    }
}
