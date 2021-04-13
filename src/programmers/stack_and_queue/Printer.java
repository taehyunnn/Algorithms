package programmers.stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Printer {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        int[] prioritiesCnt = new int[10];
        Queue<Documentation> queue = new LinkedList<>();
        int locationValue = priorities[location];
        int max = 0;
        int biggerCnt = 0;

        for (int i = 0; i < priorities.length; i++) {
            if (priorities[i] > locationValue) {
                biggerCnt++;
            }
            max = Math.max(max, priorities[i]);
            prioritiesCnt[priorities[i]]++;
            queue.add(new Documentation(i, priorities[i]));
        }

        while (true) {
            Documentation cur = queue.poll();

            if (cur.getPriority() == max) {
                answer++;
                prioritiesCnt[cur.getPriority()]--;
                max = searchMax(prioritiesCnt);
                if (cur.getIndex() == location) {
                    break;
                }
            }

            queue.add(cur);
        }


        return answer;
    }

    private int searchMax(int[] cnt) {
        for (int i = cnt.length - 1; i >= 0; i--) {
            if (cnt[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    static class Documentation {
        private int index;
        private int priority;

        public Documentation(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }

        public int getIndex() {
            return index;
        }

        public int getPriority() {
            return priority;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Printer().solution(new int[]{1,1,9,1,1,1,}, 0));
    }
}
