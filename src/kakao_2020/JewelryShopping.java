package kakao_2020;

import java.util.*;

public class JewelryShopping {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> map = new HashMap<>();

        int minIndex = 0;
        int minLength = Integer.MAX_VALUE;

        int startIndex = 0;
        int endIndex = 0;

        while (startIndex < gems.length) {
            // 보석 제거
            if (map.size() == set.size() || endIndex == gems.length) {
                if (map.get(gems[startIndex]) > 1) {
                    map.put(gems[startIndex], map.get(gems[startIndex]) - 1);
                } else {
                    map.remove(gems[startIndex]);
                }
                startIndex++;
            } else {    // 보석 추가
                map.put(gems[endIndex], map.getOrDefault(gems[endIndex], 0) + 1);
                endIndex++;
            }

            // 최소 인덱스, 길이 계산
            if (map.size() == set.size()) {
                int length = endIndex - startIndex;
                if (minLength > length) {
                    minLength = length;
                    minIndex = startIndex;
                }
            }
        }

        answer[0] = minIndex ;
        answer[1] = minIndex + minLength;

        return answer;
    }


    public static void main(String[] args) {
        new JewelryShopping().solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }
}
