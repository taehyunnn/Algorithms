package stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingTheBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> bridgeQueue = new LinkedList<>();
        Queue<Integer> timeQueue = new LinkedList<>();

        int curWeight = 0;
        int curTime = 0;

        // 다리 위에 모든 차량 올리기
        for (int truck_weight : truck_weights) {

            // 무게 초과인 경우
            while(curWeight + truck_weight > weight){
                curTime = bridge_length + timeQueue.poll() - 1;
                curWeight -= bridgeQueue.poll();
            }

            // 무게 초과 없이 추가하는 경우
            curTime++;
            curWeight += truck_weight;
            bridgeQueue.add(truck_weight);
            timeQueue.add(curTime);

            if(!timeQueue.isEmpty()){
                if(curTime - timeQueue.peek() == bridge_length){
                    timeQueue.poll();
                    curWeight -= bridgeQueue.poll();
                }
            }
        }

        while(!timeQueue.isEmpty()){
            curTime = bridge_length + timeQueue.poll();
        }

        return curTime;
    }

    public static void main(String[] args) {
        System.out.println(new TruckPassingTheBridge().solution(5,5,new int[]{2,2,2,2,1,1,1,1,1}));
    }
}
