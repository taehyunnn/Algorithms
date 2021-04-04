package kakao_2021;

public class GiftCard {
    public int solution(int[] gift_cards, int[] wants) {
        int answer = 0;

        int[] giftCnt = new int[100001];

        for (int i : gift_cards) {
            giftCnt[i]++;
        }

        for (int want : wants) {
            if (giftCnt[want] > 0) {
                giftCnt[want]--;
            } else {
                answer++;
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        new GiftCard().solution(new int[]{4, 5, 3, 2, 1}, new int[]{2, 4, 4, 5, 1});
        new GiftCard().solution(new int[]{5, 4, 5, 4, 5}, new int[]{1, 2, 3, 4, 5});
    }
}
