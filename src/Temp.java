import java.util.Arrays;
import java.util.Comparator;

public class Temp {
    public int[] solution(int[] array) {
        int[] answer = new int[array.length];
        Arrays.fill(answer, -1);
        // 해당 인덱스보다 큰 값이면서 가장 가까운 인덱스의 값을 구하라
        // 오른쪽에서부터 왼쪽으로 탐색한다
        // 왼쪽이 값이 크면 , 정답
        // 오른쪽이 값이 크면, 보류

        Num[] nums = new Num[array.length];

        for (int i = 0; i < array.length; i++) {
            nums[i] = new Num(array[i], i);
        }

        Arrays.sort(nums, Comparator.comparingInt(o -> o.num));




        return answer;
    }

    static class Num {
        int num;
        int index;

        Num(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}
