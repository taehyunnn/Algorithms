package programmers.binary_search;

import java.util.Arrays;

public class SteppingBridge {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        int[] new_rocks = new int[rocks.length + 2];

        // 출발지점과 도착지점까지 입력한 새로운 배열 생성
        new_rocks[0] = 0;
        new_rocks[new_rocks.length - 1] = distance;
        for (int i = 0; i < rocks.length; i++) {
            new_rocks[i + 1] = rocks[i];
        }

        Arrays.sort(new_rocks);

        // 이분탐색
        int left = 0;
        int right = distance;
        while (left <= right) {
            int mid = (left + right) / 2;
            int lastIndex = 0;
            int breakNum = 0;
            for (int i = 0; i < new_rocks.length - 1; i++) {
                int gap = new_rocks[i+1] - lastIndex;

                if(gap < mid){
                    breakNum++;
                } else {
                    lastIndex = new_rocks[i+1];
                }
            }

            if (breakNum > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        System.out.println(new SteppingBridge().solution(22, new int[]{2, 14, 11, 21, 17}, 2));
    }
}
