package li.test;

import java.util.ArrayList;
import java.util.List;

public class Q3 {
    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length+1];

        List<Integer> room = new ArrayList<>();

        int enterIndex = 0;
        int leaveIndex = 0;

        while (enterIndex != enter.length || leaveIndex != leave.length) {
            // 방에 있던 사람 중에 1명이 떠나기 전까지 방에 계속 추가
            do {
                room.add(enter[enterIndex++]);
            } while (enterIndex+1 < enter.length && enter[enterIndex] != leave[leaveIndex]);

            // 한 명이 떠나면 떠날 때 방 안에 있던 사람들 모두 카운트 증가
            leaveIndex++;
            for (int i = enterIndex-1; i >= 0; i--) {
                answer[room.get(i)]++;
            }
            // 한 명 떠나보냄
            room.remove(enter[enterIndex - 1]);
        }

        return answer;
    }

    public static void main(String[] args) {
        new Q3().solution(new int[]{1, 3, 2}, new int[]{1, 2, 3});
    }
}
