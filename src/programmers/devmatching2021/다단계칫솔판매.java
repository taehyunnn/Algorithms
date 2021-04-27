package programmers.devmatching2021;

import java.util.HashMap;
import java.util.Map;

public class 다단계칫솔판매 {
    private Map<String, String> members;
    private Map<String, Integer> memberNumbers;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        members = new HashMap<>();
        memberNumbers = new HashMap<>();

        for(int i=0; i < enroll.length; i++){
            members.put(enroll[i], referral[i]);
            memberNumbers.put(enroll[i], i);
        }

        // map에서 seller를 찾고 amount를 상위에 계속 넘긴다
        for(int i=0; i<seller.length; i++){

            String nextMember = seller[i];
            int sumOfCost = 100 * amount[i];

            while(!nextMember.equals("-") && sumOfCost > 0){
                int notMyCost = sumOfCost/10;
                int myCost = sumOfCost - notMyCost;

                System.out.println(nextMember);
                answer[memberNumbers.get(nextMember)] += myCost;

                nextMember = members.get(nextMember);
                sumOfCost /= 10;
            }

        }

        return answer;
    }
}
