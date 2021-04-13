package programmers.kakao_2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FormulaMaximization {

    private List<Long> numList = new ArrayList<>();
    private List<Integer> operatorList = new ArrayList<>();
    private int[] order = new int[3];
    private final int MUL = 1;
    private final int ADD = 2;
    private final int SUB = 3;
    private long answer = 0;

    public long solution(String expression) {


        init(expression);   // 숫자와 연산자 분리

        permute(0, 3, 0);

        return answer;
    }

    private void permute(int currentDepth, int targetDepth, int bitMasking) {
        if (currentDepth == targetDepth) {
            answer = Math.max(answer, Math.abs(calc()));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (((1 << i) & bitMasking) != 0) {
                continue;
            }

            order[currentDepth] = i + 1;
            permute(currentDepth + 1, targetDepth, bitMasking | (1 << i));
        }
    }

    private long calc() {
        List<Long> tempNumList = new LinkedList<>(numList);
        List<Integer> tempOperatorList = new LinkedList<>(operatorList);

        for (int i = 0; i < 3; i++) {
            int currentOperator = order[i];

            for (int j = 0; j <tempOperatorList.size(); j++) {
                if (tempOperatorList.get(j) == currentOperator) {
                    long num = operatorAtoB(tempNumList.get(j), tempNumList.get(j + 1), currentOperator);
                    tempNumList.set(j, num);
                    tempNumList.remove(j + 1);
                    tempOperatorList.remove(j);
                    j--;
                }
            }
        }

        return tempNumList.get(0);
    }

    private long operatorAtoB(Long a, Long b, int currentOperator) {
        switch (currentOperator) {
            case MUL:
                return a * b;
            case ADD:
                return a + b;
            case SUB:
                return a - b;
        }
        return -1;
    }

    private void init(String expression) {
        int lastIndex = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            int operator = isOperator(c);
            if (operator != -1) {
                operatorList.add(operator);

                String subString = expression.substring(lastIndex, i);
                numList.add(Long.valueOf(subString));
                lastIndex = i + 1;
            }
        }

        numList.add(Long.valueOf(expression.substring(lastIndex)));
    }

    private int isOperator(char c) {
        if (c == '*') {
            return MUL;
        }
        if (c == '+') {
            return ADD;
        }
        if (c == '-') {
            return SUB;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FormulaMaximization().solution("100-200*300-500+20"));
    }

}
