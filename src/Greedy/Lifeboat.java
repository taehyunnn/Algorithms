package Greedy;

import java.util.Arrays;

public class Lifeboat {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
                answer ++;
            } else {
                right--;
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Lifeboat().solution(new int[]{70,50,80,50}, 100));
    }
}
