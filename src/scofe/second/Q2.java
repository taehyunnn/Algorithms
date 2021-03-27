package scofe.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2 {

    private static Map<String, Integer> map;
    private static Queue<int[]> queue;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());

        queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        map = new HashMap<>();

        init(br, n, map);

        start();
    }

    private static void start() {
        int cityCnt = map.size();
        int[] visit = new int[cityCnt];  // 제일 작은 숫자를 대표로 선정
        for (int i = 0; i < visit.length; i++) {
            visit[i] = i;
        }

        int min = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();   // 현재 제일 짧은 cost의 간선

            int from = current[0];
            int to = current[1];
            int cost = current[2];

            if (visit[from] == visit[to]) {
                continue;
            }

            if (from < to) {
                changeVisit(visit[from], visit[to], visit);
            } else {
                changeVisit(visit[to], visit[from], visit);
            }
            min += cost;
        }
        System.out.println(min);
    }

    private static void changeVisit(int to, int from, int[] visit) {
        for (int i = 0; i < visit.length; i++) {
            if (visit[i] == from) {
                visit[i] = to;
            }
        }
    }

    private static void init(BufferedReader br, int n, Map<String, Integer> map) throws IOException {
        StringTokenizer stringTokenizer;
        int index = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            String start = stringTokenizer.nextToken();
            String end = stringTokenizer.nextToken();
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            int a, b;

            if (map.containsKey(start)) {
                a = map.get(start);
            } else {
                a = index;
                map.put(start, index++);
            }

            if (map.containsKey(end)) {
                b = map.get(end);
            } else {
                b = index;
                map.put(end, index++);
            }

            queue.add(new int[]{a, b, cost});
        }
    }
}
