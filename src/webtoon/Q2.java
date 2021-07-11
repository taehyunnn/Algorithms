package webtoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q2 {

    private List<Integer> indexList = new ArrayList<>();

    public String[] solution(String s) {
        List<String> answer = new LinkedList<>();
        List<String> temp = new LinkedList<>();

        int index = 0;
        int strLength = s.length();
        int length = 1;

        while (index < s.length() / 2) {
            String substring = s.substring(index, index + length);
            String endSubstring = s.substring(strLength - index - length, strLength - index);
            if (substring.equals(endSubstring)) {
                answer.add(substring);

                // 중간 경계값
                if (index + length <= s.length() / 2) {
                    temp.add(0, substring);
                }

                index += length;
                length = 1;
            } else {
                length++;
            }
        }
        answer.addAll(temp);

        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] abcxyasdfasdfxyabcs = new Q2().solution("abcxyasdfasdfxyabc");
        for (String abcxyasdfasdfxyabc : abcxyasdfasdfxyabcs) {
            System.out.println(abcxyasdfasdfxyabc);
        }
        String[] abcdefs = new Q2().solution("abcdef");
        for (String abcdef : abcdefs) {
            System.out.println(abcdef);
        }
    }
}
