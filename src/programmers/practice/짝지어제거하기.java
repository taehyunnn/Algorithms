package programmers.practice;

import java.util.Stack;

public class 짝지어제거하기 {
    private Stack<Character> stack;

    public int solution(String s)
    {
        int answer = 0;

        stack = new Stack<>();

        stack.push(s.charAt(0));

        for(int i=1; i<s.length(); i++){
            char c = s.charAt(i);

            if(stack.isEmpty()){
                stack.push(c);
            } else if(stack.peek() == c){
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
