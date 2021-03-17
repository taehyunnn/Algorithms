package baekjoon.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LineUp {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> listsForDfs = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        int[] inDegree = new int[n+1];

        for (int i = 0; i <= n; i++) {
            listsForDfs.add(new ArrayList<>());
            lists.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            listsForDfs.get(b).add(a);
            lists.get(a).add(b);
            inDegree[b]++;
        }

        // dfs 문제 풀이 시작
//        boolean[] visit = new boolean[n+1];
//
//        for (int i = 1; i < visit.length; i++) {
//            if (!visit[i]) {
//                visit[i] = true;
//                dfs(i,listsForDfs,visit);
//                System.out.print(i+" ");
//            }
//        }
        // dfs 문제 풀이 끝


        // 위상 정렬 문제 풀이 시작
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            System.out.print(current +" ");

            for (Integer next : lists.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

    }

    private static void dfs(int current, List<List<Integer>> lists, boolean[] visit) {
        if (lists.get(current).size() == 0) {
            return;
        }

        for (Integer integer : lists.get(current)) {
            if (visit[integer]) {
                continue;
            }
            dfs(integer, lists,visit);
            visit[integer] = true;
            System.out.print(integer+" ");
        }

    }
}
