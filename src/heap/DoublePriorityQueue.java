package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class DoublePriorityQueue {

    public int[] solution(String[] operations) {
        int[] answer = {};

        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            String[] split = operation.split(" ");
            Integer temp = Integer.valueOf(split[1]);
            if(split[0].equals("I")){
                minQueue.add(temp);
                maxQueue.add(temp);
            } else {
                if(temp == 1){
                    minQueue.remove(maxQueue.poll());
                } else {
                    maxQueue.remove(minQueue.poll());
                }
            }
        }

        if(!minQueue.isEmpty()){
            answer = new int[]{maxQueue.poll(), minQueue.poll()};
        } else {
            answer = new int[]{0,0};
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] solution = new DoublePriorityQueue().solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}
