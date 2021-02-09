package sort;

import java.util.Arrays;

public class HIndex {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for(int i=0; i<citations.length; i++){
            int smaller = Math.min(citations[i], citations.length-i);
            answer = Math.max(answer, smaller);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new HIndex().solution(new int[]{8,0,5,10,6}));
    }
}
