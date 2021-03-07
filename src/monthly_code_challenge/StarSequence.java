package monthly_code_challenge;

public class StarSequence {
    public int solution(int[] a) {
        int answer = 0;

        int[] count = new int[a.length];
        for (int i : a) {
            count[i]++;
        }

        // 교집합 i를 기준으로 순회
        for (int i = 0; i < count.length; i++) {
            if (count[i] * 2 <= answer) {
                continue;
            }
            int star = 0;
            // i를 교집합으로 갖는 조건 확인
            for (int j = 0; j < a.length-1; j++) {
                if ((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                    star+=2;
                    j++;
                }
            }
            answer = Math.max(answer,star);
        }
        return answer;
    }
}