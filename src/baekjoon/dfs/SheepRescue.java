package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SheepRescue {
    private static int[] sheep;
    private static int[] wolfs;
    private static List<List<Integer>> links = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int cntIsland = Integer.parseInt(stringTokenizer.nextToken());

        sheep = new int[cntIsland + 1];
        wolfs = new int[cntIsland + 1];

        for (int i = 0; i <= cntIsland; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 2; i <= cntIsland; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            if ("S".equals(stringTokenizer.nextToken())) {
                sheep[i] = Integer.parseInt(stringTokenizer.nextToken());
            } else {
                wolfs[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            links.get(Integer.parseInt(stringTokenizer.nextToken())).add(i);
        }

        long max = dfs(1);
        System.out.println(max);
    }

    private static long dfs(int island) {
        long sNum = sheep[island];
        for (Integer next : links.get(island)) {
            sNum += dfs(next);
        }
        return Math.max(sNum- wolfs[island] ,0);
    }
}
