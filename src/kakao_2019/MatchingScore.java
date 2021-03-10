package kakao_2019;

import java.util.HashMap;
import java.util.Map;

public class MatchingScore {
    public int solution(String word, String[] pages) {
        int answer = 0;
        int[] matchingScore = new int[pages.length];
        int[] basicScore = new int[pages.length];
        int[] outLinkCnt = new int[pages.length];
        boolean[][] outLinkCheck = new boolean[pages.length][pages.length];

        Map<String, Integer> map = new HashMap<>();  // 페이지 url에 해당하는 index


        word = word.toLowerCase();
        for (String page : pages) {
            page = page.toLowerCase();  // 소문자 변환

        }


        return answer;
    }
}
