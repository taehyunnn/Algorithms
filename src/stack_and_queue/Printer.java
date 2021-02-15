package stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Printer {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();

        for (int priority : priorities) {
            queue.add(priority);
        }



        return answer;
    }
}
