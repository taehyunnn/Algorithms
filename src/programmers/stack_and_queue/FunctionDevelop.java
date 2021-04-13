package programmers.stack_and_queue;

import java.util.ArrayList;
import java.util.List;
public class FunctionDevelop {

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        List<Integer> list = new ArrayList<>();
        int nextIndex = 0;

        while(nextIndex != progresses.length){
            // 하루 작업 진행
            for (int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            // 업데이트 된 진행 상황으로 배포 가능 여부 확인
            int count = 0;
            if(progresses[nextIndex] >= 100){
                nextIndex++;
                count = 1;

                while( nextIndex < progresses.length && progresses[nextIndex] >= 100 ){
                    nextIndex++;
                    count ++;
                }
            }
            if(count != 0){
                list.add(count);
            }
        }
        answer = list.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int[] solution = new FunctionDevelop().solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}
