package li.test.intern;

import java.util.Stack;

public class Q2 {

    public int[] solution(int[] array) {
        int[] leftAnswer = new int[array.length];
        int[] rightAnswer = new int[array.length];

        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();

        // 해당 인덱스보다 큰 값이면서 가장 가까운 인덱스의 값을 구하라
        // 오른쪽에서부터 왼쪽으로 탐색한다
        // 왼쪽이 값이 크면 , 정답
        // 오른쪽이 값이 크면, 보류

        rightProcess(array, rightAnswer, rightStack);
        leftProcess(array, leftAnswer, leftStack);
        mergeLeftAndRight(leftAnswer, rightAnswer);

        for (Integer integer : leftAnswer) {
            System.out.print(integer+" ");
        }
        System.out.println();

        return null;
    }

    private void mergeLeftAndRight(int[] leftAnswer, int[] rightAnswer) {
        for (int i = 0; i < leftAnswer.length; i++) {
            if (leftAnswer[i] == -1) {
                leftAnswer[i] = rightAnswer[i];
                continue;
            }

            if (rightAnswer[i] == -1) {
                continue;
            }

            // 오른쪽까지의 거리가 더 가까울때
            if ((i - leftAnswer[i]) > (rightAnswer[i] - i)) {
                leftAnswer[i] = rightAnswer[i];
            }

        }
    }

    private void leftProcess(int[] array, int[] leftAnswer, Stack<Integer> leftStack) {
        leftStack.push(array.length-1);
        for (int i = array.length - 2; i >= 0; i--) {
            while (!leftStack.isEmpty() && array[leftStack.peek()] < array[i]) {
                leftAnswer[leftStack.pop()] = i;
            }
            leftStack.push(i);
        }

        popStackAll(leftAnswer, leftStack);
    }

    private void popStackAll(int[] answer, Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            answer[pop] = -1;
        }
    }

    private void rightProcess(int[] array, int[] rightAnswer, Stack<Integer> rightStack) {
        rightStack.push(0);
        for (int i = 1; i < array.length; i++) {
            while (!rightStack.isEmpty() && array[rightStack.peek()] < array[i]) {
                rightAnswer[rightStack.pop()] = i;
            }

            rightStack.push(i);
        }

        popStackAll(rightAnswer, rightStack);
    }

    public static void main(String[] args) {
        new li.test.intern.Q2().solution(new int[]{3, 5, 4, 1, 2});
        new li.test.intern.Q2().solution(new int[]{6, 2, 2, 2, 3});
    }
}
