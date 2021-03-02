package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OvertimeIndex {
    public long solution(int n, int[] works) {
        long answer = 0;

        int[] newWorks = new int[works.length + 1];
        for (int i = 0; i < works.length; i++) {
            newWorks[i] = works[i];
        }

        Arrays.sort(newWorks);
        int maxIndex = newWorks.length - 1;

        while (n != 0 && maxIndex > 0) {
            while (maxIndex > 0 && newWorks[maxIndex] <= newWorks[maxIndex - 1]) {
                maxIndex--;
            }
            for (int i = maxIndex; i < newWorks.length && n != 0; i++) {
                newWorks[i]--;
                n--;
            }
        }

        for (int i = 1; i < newWorks.length; i++) {
            answer += newWorks[i] * newWorks[i];
        }

        return answer;
    }

    public long solution2(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            queue.add(work);
        }

        while (n != 0 && !queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 0) {
                break;
            }
            queue.add(--cur);
            n--;
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            answer += cur * cur;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new OvertimeIndex().solution2(3,new int[] {1,1}));
    }
}
