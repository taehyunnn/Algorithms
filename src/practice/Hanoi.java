package practice;

import java.util.*;

public class Hanoi {

    public int[][] solution(int n) {
        List<int[]> list = new ArrayList<>();

        hanoi(n, list, 1, 3, 2);

        int[][] answer=  new int[list.size()][2];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).clone();
        }

        return answer;
    }

    private void hanoi(int n, List<int[]> list, int start, int end, int temp) {
        if (n == 1) {
            list.add(new int[]{start, end});
            return;
        }
        hanoi(n - 1, list, start, temp, end);
        hanoi(1, list, start, end, temp);
        hanoi(n - 1, list, temp, end, start);
    }

}
