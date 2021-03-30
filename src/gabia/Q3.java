package gabia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q3 {
    public static void main(String[] args) {
//        solution(3, new int[]{4, 2, 2, 5, 3});
        solution(1, new int[]{100,1,50,1,1});
    }

    private static void solution(int n, int[] coffee_times) {
        List<Coffee> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>(coffee_times.length);

        for (int i = 0; i < coffee_times.length; i++) {
            list.add(new Coffee(i + 1, coffee_times[i]));
        }

        Queue<Coffee> queue = new PriorityQueue<>();
        int i = 0;
        for (; i < n; i++) {
            queue.add(list.get(i));
        }

        while (!queue.isEmpty()) {
            Coffee currentCoffee = queue.poll();
            result.add(currentCoffee.num);

            if (i < coffee_times.length) {
                queue.add(new Coffee(i+1, currentCoffee.time + coffee_times[i]));
                i++;
            }
        }

        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

    static class Coffee implements Comparable<Coffee> {
        int num;
        int time;

        Coffee(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Coffee o) {
            if (time == o.time) {
                return num - o.num;
            }
            return time - o.time;
        }
    }
}
