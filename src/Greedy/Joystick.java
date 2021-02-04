package Greedy;

import java.util.ArrayList;
import java.util.List;

public class Joystick {
    /**
     * 가장 길게 연속된 AAA... 문자를 제외하고 움직이는 문제
     */
    public int solution(String name) {
        int answer = 0;
        int[] diff = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (char c : name.toCharArray())
            answer += diff[c - 'A'];

        int length = name.length();
        int min = length - 1;

        for (int i = 0; i < length; i++) {
            int nextIndex = i + 1;
            while (nextIndex < length && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }
            min = Math.min(min, length - (nextIndex - i) + Math.min(i, length - nextIndex));
        }

        return answer + min;
    }

    public static void main(String[] args) {
        System.out.println(new Joystick().solution("BBBBAAAAB"));
    }
}
