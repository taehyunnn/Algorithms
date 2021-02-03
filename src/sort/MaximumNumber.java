package sort;


import java.util.Arrays;

public class MaximumNumber {
    public String solution(int[] numbers) {
        String answer = "";

        String[] strings = new String[numbers.length];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        // 4자리 이상으로 변환
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < 2; j++) {
                strings[i] += strings[i];
            }
        }

        Arrays.sort(strings);

        // 다시 원래 길이로 복원
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].substring(0, strings[i].length()/4);
        }

        // 역순으로 문자열 더하기
        for (int i = strings.length -1; i >= 0; i--) {
            answer += strings[i];
        }

        if(answer.charAt(0) == '0'){
            return "0";
        }

        return answer;
    }


    public static void main(String[] args) {
        System.out.println(new MaximumNumber().solution(new int[]{0, 0, 0, 0, 0}));
    }
}
