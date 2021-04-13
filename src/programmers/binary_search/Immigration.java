package programmers.binary_search;

public class Immigration {

    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        long minTime = 1;
        long maxTime = (long) n* (long) times[times.length-1];
        long midTime = 0;

        while(minTime <= maxTime) {
            midTime = (minTime + maxTime) / 2;
            long midTimePeople = 0;

            for (int i = 0; i < times.length; i++) {
                midTimePeople += midTime / times[i] ;
            }

            if(midTimePeople >= n ){
                maxTime = midTime -1 ;
                answer = Math.min(answer, midTime);
            } else {
                minTime = midTime + 1;
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        System.out.println(new Immigration().solution(6, new int[]{7, 10}));
    }
}
