package li.test.intern;

import java.util.Stack;

public class Q1 {

    private Stack<Character> stack = new Stack<>();
    private int numOfParenthesis;

    public int solution(String inputString) {

        // 첫번재 문자가 닫는 괄호거나 괄호가 아니면
        char firstCh = inputString.charAt(0);
        if (isCloseParenthesis(firstCh)) {
            return 0;
        }

        // 여닫는 괄호의 짝이 맞지 않으면 닫는 괄호의 인덱스를 음수로 반환
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (isOpenParenthesis(c)) {
                stack.push(c);
                numOfParenthesis++;
            } else if (isCloseParenthesis(c)) {
                if (stack.isEmpty()) {
                    return -i;
                }

                Character peek = stack.peek();
                if (isPair(peek, c)) {
                    stack.pop();
                } else {
                    return -i;
                }
            }
        }

        if (stack.isEmpty()) {
            return numOfParenthesis;
        } else {
            return -(inputString.length()-1);
        }

    }

    private boolean isPair(Character peek, char c) {
        if (peek == '{') {
            return c == '}';
        }
        if (peek == '[') {
            return c == ']';
        }

        if (peek == '(') {
            return c == ')';
        }

        if (peek == '<') {
            return c == '>';
        }

        return false;
    }

    private boolean isCloseParenthesis(char c) {
        return c == ']' || c == '}' || c == ')' || c == '>';
    }

    private boolean isOpenParenthesis(char firstCh) {
        return firstCh == '[' || firstCh == '{' || firstCh == '(' || firstCh == '<';
    }

    public static void main(String[] args) {
        System.out.println(new Q1().solution("line [({<plus>)}]"));
    }
}
