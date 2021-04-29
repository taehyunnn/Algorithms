package programmers.kakao_2019;

import java.util.Arrays;

public class 실패율 {
    public int[] solution(int N, int[] stages) {

        Stage[] stageArray = new Stage[N];

        for(int i=0; i< N; i++){
            stageArray[i] = new Stage(i+1);
        }

        for(int stage : stages){
            for(int i=1; i<stage; i++){
                stageArray[i-1].addSumOfUser();
            }
            if(stage != N+1){
                stageArray[stage-1].addCurrentUser();
            }
        }

        Arrays.sort(stageArray);

        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = stageArray[i].num;
        }

        return answer;
    }

    static class Stage implements Comparable<Stage>{
        int num;
        int currentUserNum;
        int sumOfUser;

        Stage(int num){
            this.num = num;
        }

        public void addCurrentUser(){
            currentUserNum ++;
            sumOfUser ++;
        }

        public void addSumOfUser(){
            sumOfUser++;
        }

        public double failRate(){
            if(sumOfUser == 0){
                return 0;
            }

            return (double)currentUserNum / sumOfUser;
        }

        public int compareTo(Stage obj){
            if(this.failRate() == obj.failRate()){
                return this.num - obj.num;
            }

            return Double.compare(obj.failRate(), this.failRate());
        }
    }
}
