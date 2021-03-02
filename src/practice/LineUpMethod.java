package practice;

import java.util.ArrayList;
import java.util.List;

public class LineUpMethod {

    public int[] solution(int n, long k) {

        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        k--;
        int index = 0;

        while (n != 0) {
            long currentDivision =  factorial(n-1);
            long currentOrder =  (k / currentDivision);

            answer[index++] = list.get((int) currentOrder);
            list.remove((int)currentOrder);

            k %= currentDivision;
            n--;
        }

        return answer;
    }

    private long factorial(int i) {
        long result = 1;
        for (int j = 1; j <= i ; j++) {
            result  *= j;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] solution = new LineUpMethod().solution(1, 1);
        for (int i : solution) {
            System.out.println(i);
        }
    }
}
