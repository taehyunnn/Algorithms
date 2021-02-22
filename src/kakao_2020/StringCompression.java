package kakao_2020;

import java.util.ArrayList;
import java.util.List;

public class StringCompression {
    public int solution(String s) {
        int length = s.length();

        // i : 몇 개 단위로 자를건지
        for (int i = 1; i <= s.length()/2 ; i++) {
            String newS = s;
            List<String> list = new ArrayList<>();

            while (newS.length() >= i) {
                list.add(newS.substring(0,i));
                newS = newS.substring(i);
            }

            int currentLen = 0;
            int sameWordCount = 1;
            for (int j = 0; j < list.size() -1; j++) {
                if (list.get(j).equals(list.get(j + 1))) {
                    sameWordCount ++;
                    list.remove(j--);
                } else {
                    if(sameWordCount != 1){
                        currentLen += (int)Math.log10(sameWordCount) +1;
                    }
                    sameWordCount = 1;
                }
            }
            if(sameWordCount != 1){
                currentLen += (int)Math.log10(sameWordCount) +1;
            }


            currentLen += i * list.size() + newS.length();
            length = Math.min(length, currentLen);
        }

        return length;
    }

    public static void main(String[] args) {
        System.out.println(new StringCompression().solution("aabbaccc"));
    }

}
