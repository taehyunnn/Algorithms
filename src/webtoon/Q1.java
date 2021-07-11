package webtoon;

import java.util.Arrays;

class Q1 {
    public int solution(int[] prices, int[] discounts) {
        int answer = 0;

        Arrays.sort(prices);
        Arrays.sort(discounts);

        int discountRate = 1;
        for (int i = prices.length - 1, disIndex = discounts.length - 1; i >= 0; i--) {
            discountRate = disIndex >= 0 ? 100 - discounts[disIndex--] : 100;
            answer += prices[i] * discountRate / 100;
        }

        return answer;
    }

    public static void main(String[] args) {
        new Q1().solution(new int[]{13000, 88000, 10000}, new int[]{30, 20});
    }
}
