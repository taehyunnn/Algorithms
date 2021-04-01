package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ParenthesesRemoval {

    private static Set<String> result;
    private static Stack<Integer> stack;    // 괄호 짝 이어주기 용도
    private static int[] index; // 괄호 인덱스 짝 번호 저장용도
    private static boolean[] erase; // 자기 짝 괄호를 지웠는지 체크 용도

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        result = new HashSet<>();
        stack = new Stack<>();
        index = new int[200];
        erase = new boolean[200];

        pairParentheses(s);

        deleteParentheses(0, s.length(), s, "");
        result.remove(s);
        result.stream().sorted().forEach(System.out::println);
    }

    private static void deleteParentheses(int currentIndex, int length, String s, String currentStr) {
        if (currentIndex == length) {
            result.add(currentStr);
            return;
        }

        if (s.charAt(currentIndex) == '(') {
            erase[index[currentIndex]] = true;
            deleteParentheses(currentIndex + 1, length, s, currentStr);
            erase[index[currentIndex]] = false;
        }

        if (s.charAt(currentIndex) == ')' && erase[currentIndex]) {
            deleteParentheses(currentIndex + 1, length, s, currentStr);
        } else {
            deleteParentheses(currentIndex + 1, length, s, currentStr + s.charAt(currentIndex));
        }
    }

    private static void pairParentheses(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                Integer pop = stack.pop();
                index[i] = pop;
                index[pop] = i;
            }
        }
    }
}
