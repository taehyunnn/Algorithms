package programmers.kakao_2019;

import java.util.*;

public class 무지의먹방라이브 {
    public int solution(int[] food_times, long k) {

        List<Food> foods = new LinkedList<>();

        for (int i = 0; i < food_times.length; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        Collections.sort(foods);

        Iterator<Food> iterator = foods.iterator();
        int preTime = 0;
        // 최소 남은 시간 * size 가 K보다 크면 정답 찾기 가능
        while (iterator.hasNext()) {
            Food next = iterator.next();
            int time = next.time - preTime;
            k -= time * foods.size();
            if (k < 0) {
                foods.sort(Comparator.comparingInt(o -> o.num));
                k += (long)time * foods.size();
                int index = (int) (k % foods.size());
                return foods.get(index).num;
            }
            preTime = next.time;
            iterator.remove();
        }

        return -1;
    }

    static class Food implements Comparable<Food> {
        int num;
        int time;

        Food(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Food o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        System.out.println(new 무지의먹방라이브().solution(new int[]{3, 1, 2}, 5));
    }
}
