package smasung_type_a_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParenthesisAddition {

    private static int max = Integer.MIN_VALUE;
    private static List<Integer> numberList;
    private static List<Character> operatorList;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        numberList = new ArrayList<>(n / 2 + 1);
        operatorList = new ArrayList<>(n / 2);

        String s = bufferedReader.readLine();

        initNumberListAndOperatorList(n, numberList, operatorList, s);

        dfs(0, n / 2, numberList.get(0));

        System.out.println(max);
    }

    private static void dfs(int now, int targetDepth, int sum) {
        if (now == targetDepth) {
            max = Math.max(max, sum);
            return;
        }

        // 다음 연산이 괄호가 아니다.
        // sum + next
        int temp = calcOne(sum, numberList.get(now + 1), operatorList.get(now));
        dfs(now + 1, targetDepth, temp);

        // 다음 연산이 괄호
        // sum + (next1 + next2)
        if (now + 1 < targetDepth) {
            temp = calcOne(numberList.get(now + 1), numberList.get(now + 2), operatorList.get(now + 1));
            temp = calcOne(sum, temp, operatorList.get(now));
            dfs(now + 2, targetDepth, temp);
        }

    }

    private static int calcOne(Integer a, Integer b, Character operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }


    private static void initNumberListAndOperatorList(int n, List<Integer> numberList, List<Character> operatorList, String s) {
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numberList.add(s.charAt(i) - '0');
            } else {
                operatorList.add(s.charAt(i));
            }
        }
    }
}
