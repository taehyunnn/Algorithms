import java.io.*;
import java.util.*;

public class Main {

    public static int[] solution(int n, int[] passengers, int[][] train) {
        int[] dist = new int[n + 1];
        List<List<int[]>> links = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        dist[1] = passengers[1];

        for (int i = 0; i < train.length; i++) {
            int[] ints = train[i];
            int from = ints[0];
            int to = ints[1];
            int cost = passengers[i];

            links.get(from).add(new int[]{to, passengers[to-1]});
            links.get(to).add(new int[]{from, passengers[from-1]});
        }

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o2[1] - o1[1];
        });

        queue.add(new int[]{1, passengers[0]});

        boolean[] visit = new boolean[n + 1];
        visit[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentNode = current[0];

            for (int[] ints : links.get(currentNode)) {

                int nextNode = ints[0];
                if (nextNode == 5) {
                    System.out.println();
                }
                int nextPassenger = ints[1];

                if (visit[nextNode]) {
                    continue;
                }

                if (dist[nextNode] < dist[currentNode] + nextPassenger) {
                    visit[nextNode] = true;
                    dist[nextNode] = dist[currentNode] + nextPassenger;
                    queue.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        int maxPassengers = 0;
        int maxIndex = 0;
        for (int i = 1; i < dist.length; i++) {
            if (maxPassengers <= dist[i]) {
                maxPassengers = dist[i];
                maxIndex = i;
            }
        }

        return new int[]{maxIndex, maxPassengers};
    }

    public static void main(String[] args) throws IOException {
        int[] solution = solution(6, new int[]{1, 1, 1, 1, 1, 1}, new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 5}, {3, 6}});
        for (int i : solution) {
            System.out.println(i + " ");
        }
        System.out.println();
        int[] solution1 = solution(4, new int[]{2, 1, 2, 2}, new int[][]{{1, 2}, {1, 3}, {2, 4}});
        for (int i : solution1) {
            System.out.println(i + " ");
        }
        System.out.println();
        int[] solution2 = solution(5, new int[]{1, 1, 2, 3, 4}, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}});
        for (int i : solution2) {
            System.out.println(i + " ");
        }
        System.out.println();
    }
}