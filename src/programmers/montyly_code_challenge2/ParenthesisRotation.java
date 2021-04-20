package programmers.montyly_code_challenge2;

import java.util.Stack;

public class ParenthesisRotation {
    public int solution(String s) {
        int answer = 0;

        int length = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            rotate(chars);

            if (isRight(chars)) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isRight(char[] chars) {
        Stack<Character> stack = new Stack<>();

        for (char aChar : chars) {
            if (aChar == '[' || aChar == '(' || aChar == '{') {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                Character pop = stack.pop();
                if (!isPair(aChar, pop)) {
                    return false;
                }

            }
        }

        return stack.isEmpty();
    }

    private boolean isPair(char aChar, Character pop) {
        if (aChar == ']') {
            return pop == '[';
        } else if (aChar == ')') {
            return pop == '(';
        } else if (aChar == '}') {
            return pop == '{';
        }
        return false;
    }

    private void rotate(char[] chars) {
        char temp = chars[0];
        System.arraycopy(chars, 1, chars, 0, chars.length - 1);
        chars[chars.length - 1] = temp;
    }
}
