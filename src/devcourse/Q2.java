package devcourse;

import java.util.Stack;

public class Q2 {
    public int[] solution(int[] deposit) {
        Stack<Integer> stack = new Stack<>();

        for (int value : deposit) {
            if (value > 0) {
                stack.push(value);
            } else {

                while (value < 0) {
                    Integer pop = stack.pop();
                    value += pop;
                }

                if (value != 0) {
                    stack.push(value);
                }
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }
}
