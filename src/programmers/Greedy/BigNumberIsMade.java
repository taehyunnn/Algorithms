package programmers.Greedy;

import java.util.Stack;

public class BigNumberIsMade {

    public String solution(String number, int k) {

        String prefix = "";
        int resultLen = number.length() - k;
        while (k != 0) {
            int maxDigit = 0;
            int maxIndex = 0;

            for (int i = 0; i <= k && i < number.length(); i++) {
                if (maxDigit < number.charAt(i)) {
                    maxDigit = number.charAt(i);
                    maxIndex = i;
                }
            }

            prefix += number.charAt(maxIndex);
            number = number.substring(maxIndex + 1);
            k -= maxIndex;

            if(prefix.length() == resultLen){
                return prefix;
            }
        }
        prefix += number;

        return prefix;
    }

    // 프로그래머스에 있던 베스트 코드.. 대단하다
    public String solution2(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }


    public static void main(String[] args) {
        System.out.println(new BigNumberIsMade().solution("987", 2));
    }
}
