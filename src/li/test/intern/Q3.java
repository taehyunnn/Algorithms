package li.test.intern;

import java.util.*;

public class Q3 {

    Queue<Ad> queue = new PriorityQueue<>();
    List<Ad> list = new ArrayList<>();

    public int solution(int[][] ads) {
        int answer = 0;

        for (int[] ad : ads) {
            list.add(new Ad(ad[0], ad[1]));
        }

        list.sort(Comparator.comparingInt(o -> o.possibleStartTime));
        int listIndex = 0;

        int currentTime = 0;
        int cnt = 0;

        while (cnt != ads.length) {
            // q에 삽입할 광고 있으면 삽입 없으면 1초 증가
            while (listIndex < list.size() && list.get(listIndex).possibleStartTime <= currentTime) {
                queue.add(list.get(listIndex++));
            }

            if (queue.isEmpty()) {
                currentTime = list.get(listIndex).possibleStartTime;
                continue;
            }

            Ad poll = queue.poll();
            answer += (currentTime - poll.possibleStartTime) * poll.lossCost;
            currentTime += 5;
            cnt++;
        }

        return answer;
    }

    static class Ad implements Comparable<Ad> {
        int possibleStartTime;
        int lossCost;

        Ad(int possibleStartTime, int lossCost) {
            this.possibleStartTime = possibleStartTime;
            this.lossCost = lossCost;
        }

        @Override
        public int compareTo(Ad o) {
            return -(this.lossCost - o.lossCost);
        }
    }

    public static void main(String[] args) {
    }
}
