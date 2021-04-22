package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 게리맨더링 {

    private static boolean[] groups;
    private static int[] peopleNums;
    private static List<List<Integer>> links;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        // combine으로 2그룹으로 나눈다.
        for (int i = 1; i <= groups.length / 2; i++) {
            combine(0, 0, i);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void combine(int index, int currentDepth, int targetDepth) {
        if (currentDepth == targetDepth) {
            divideNodeToGroup();

            return;
        }
        for (int i = index + 1; i < groups.length; i++) {
            groups[i] = true;
            combine(i, currentDepth + 1, targetDepth);
            groups[i] = false;
        }
    }

    private static void divideNodeToGroup() {
        List<Integer> groupA = new ArrayList<>();
        List<Integer> groupB = new ArrayList<>();

        for (int i = 1; i < groups.length; i++) {
            if (groups[i]) {
                groupA.add(i);
            } else {
                groupB.add(i);
            }
        }

        if (isConnected(groupA) && isConnected(groupB)) {
            min = Math.min(min, calcGapBetweenGroupAAndGroupB(groupA, groupB));
        }
    }

    private static boolean isConnected(List<Integer> group) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));

        boolean[] visit = new boolean[groups.length];
        visit[group.get(0)] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (Integer nextNode : links.get(poll)) {
                if (!group.contains(nextNode)) {
                    continue;
                }

                if (visit[nextNode]) {
                    continue;
                }

                visit[nextNode] = true;
                cnt++;
                queue.add(nextNode);
            }
        }

        return cnt == group.size();
    }

    private static int calcGapBetweenGroupAAndGroupB(List<Integer> groupA, List<Integer> groupB) {
        int a = 0, b = 0;
        for (Integer integer : groupA) {
            a += peopleNums[integer];
        }
        for (Integer integer : groupB) {
            b += peopleNums[integer];
        }

        return Math.abs(a - b);
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        peopleNums = new int[n + 1];
        links = new ArrayList<>(n + 1);
        groups = new boolean[n + 1];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= n; i++) {
            peopleNums[i] = Integer.parseInt(stringTokenizer.nextToken());
            links.add(new ArrayList<>());
        }
        links.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int cnt = Integer.parseInt(stringTokenizer.nextToken());

            for (int j = 0; j < cnt; j++) {
                links.get(i).add(Integer.valueOf(stringTokenizer.nextToken()));
            }
        }
    }
}
