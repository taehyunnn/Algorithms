package baekjoon.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GameDevelopment {

    private static List<List<Integer>> links = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());

        int[] inDegree = new int[n + 1];
        int[] constructTimes = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        // 초기값 세팅
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            constructTimes[i] = Integer.parseInt(stringTokenizer.nextToken());
            while (true) {
                int node = Integer.parseInt(stringTokenizer.nextToken());
                if (node == -1) {
                    break;
                }
                inDegree[i]++;
                links.get(node).add(i);
            }
        }

        // 시작노드(루트노드 탐색)
        int start = 1;
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                start = i;
            }
        }

        // 루트노드부터 건물 순서대로 건설
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int[] sumOfConstructTime = new int[n + 1]; // 누적 건설 시간
        sumOfConstructTime[start] = constructTimes[start];

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (Integer next : links.get(current)) {
                inDegree[next]--;
                sumOfConstructTime[next] = Math.max(sumOfConstructTime[next], sumOfConstructTime[current]);
                if (inDegree[next] == 0) {
                    sumOfConstructTime[next] += constructTimes[next];
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(sumOfConstructTime[i]);
        }
    }
}

