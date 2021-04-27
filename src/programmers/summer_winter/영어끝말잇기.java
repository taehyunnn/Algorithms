package programmers.summer_winter;

import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {
    private Set<String> set;

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        set = new HashSet<>();
        char lastChar = words[0].charAt(0);

        int count = 1;
        int orderCount=1;
        // words 반복. count가 n이 되면 1바퀴 순회한 것

        for(String s : words){
            if(set.contains(s) || s.charAt(0) != lastChar){
                answer[0] = count;
                answer[1] = orderCount;
                break;
            }

            set.add(s);
            lastChar = s.charAt(s.length() -1);

            count++;
            if(count > n){
                count = 1;
                orderCount++;
            }
        }

        return answer;
    }
}
