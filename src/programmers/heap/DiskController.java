package programmers.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskController {
    public int solution(int[][] jobs) {
        int answer = 0;

        int currentTime = 0; // 현재 시간
        int resultMinTime = 0;  // 최종적으로 구해야할 시간
        int taskCount = 0; // 처리한 작업의 수

        List<int[]> jobList = new ArrayList<>(Arrays.asList(jobs));

        while(taskCount != jobs.length){
            // 준비된 작업들 중에서 소요시간이 가장 짧은 작업 찾기
            int minProcessTime = Integer.MAX_VALUE;
            int minProcessIndex = 0;
            for (int i = 0; i < jobList.size(); i++) {
                if(jobList.get(i)[0] <= currentTime) {  // 요청이 들어 왔으면  소요시간 체크
                    if(minProcessTime > jobList.get(i)[1]){
                        minProcessTime = jobList.get(i)[1];
                        minProcessIndex = i;
                    }
                }
            }

            // 준비된 작업이 없으면 1초 증가하고 다시 반복
            if(minProcessTime == Integer.MAX_VALUE){
                currentTime++;
                continue;
            }

            //작업 실행
            currentTime += minProcessTime;  //현재 시간 업데이트
            resultMinTime += currentTime - jobList.get(minProcessIndex)[0] ; // 요청 시간부터 완료 시간 차이 업데이트
            jobList.remove(minProcessIndex); // 실행한 작업은 삭제
            taskCount++;

        }

        answer = resultMinTime / jobs.length;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new DiskController().solution(new int[][]{{100, 100}, {1000, 1000}}));
    }
}
