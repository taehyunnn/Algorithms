package li.test;

import java.util.*;

public class Q3 {

    private Set<Integer> room = new HashSet<>();

    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length + 1];

        int enterIndex = 0;
        int leaveIndex = 0;

        while (leaveIndex != leave.length) {
            // 다음 차례로 나갈 사람이 방에 있으면 나간다.
            while (leaveIndex != leave.length && room.contains(leave[leaveIndex])) {
                room.remove(leave[leaveIndex]);
                leaveIndex++;
            }

            if (enterIndex == enter.length) {
                continue;
            }

            // 1명 추가
            int nextEnterNum = enter[enterIndex];
            for (Integer next : room) {
                answer[next]++;
                answer[nextEnterNum]++;
            }
            room.add(nextEnterNum);
            enterIndex++;
        }

        for (int i = 1; i < answer.length; i++) {
            int num = answer[i];
            System.out.print(num + " ");
        }
        System.out.println();

        return answer;
    }

    public static void main(String[] args) {
        new Q3().solution(new int[]{1, 3, 2}, new int[]{1, 2, 3});
        new Q3().solution(new int[]{1, 4, 2, 3}, new int[]{ 2,1,3,4});
    }
}
