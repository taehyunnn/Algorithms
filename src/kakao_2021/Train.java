package kakao_2021;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private int max = 0;
    private int index = 0;
    private List<List<Integer>> links;
    private int[] passenger;

    public int[] solution(int n, int[] passenger, int[][] train) {
        this.passenger = passenger;
        links = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }

        for (int[] ints : train) {
            int from = ints[0];
            int to = ints[1];

            links.get(from).add(to);
            links.get(to).add(from);
        }

        start(1, -1, passenger[0]);

        return new int[]{index, max};
    }

    private void start(int currentStation, int preStation, int currentPassenger) {
        for (Integer next : links.get(currentStation)) {
            if (next == preStation) {
                continue;
            }

            if (max <= passenger[next - 1] + currentPassenger) {
                max = passenger[next - 1] + currentPassenger;
                if (index < next) {
                    index = next;
                }
            }

            start(next, currentStation, currentPassenger + passenger[next - 1]);
        }
    }

    public static void main(String[] args) {

        Train train = new Train();
        int[] solution = train.solution(6, new int[]{1, 1, 1, 1, 1, 1}, new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 5}, {3, 6}});
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
        solution = train.solution(4, new int[]{2, 1, 2, 2}, new int[][]{{1, 2}, {1, 3}, {2, 4}});
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
        solution = train.solution(5, new int[]{1, 1, 2, 3, 4}, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}});
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
