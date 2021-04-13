package programmers.kakao_2018;

import java.util.ArrayList;
import java.util.List;

public class Compression {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();

        List<String> dictionary = new ArrayList<>();

        dictionary.add("");

        for (int i = 0; i < 26; i++) {
            dictionary.add(String.valueOf((char) (i + 'A')));
        }

        while (!"".equals(msg)) {
            msg = startCompression(msg, answer, dictionary);
        }

        return answer.stream().mapToInt(i ->i).toArray();
    }

    private String startCompression(String msg, List<Integer> answer, List<String> dictionary) {
        int longestLength = 0;
        int longestIndex = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            if(msg.startsWith(dictionary.get(i)) && longestLength <= dictionary.get(i).length()){
                longestLength = dictionary.get(i).length();
                longestIndex = i;
            }
        }
        msg = msg.replaceFirst(dictionary.get(longestIndex), "");
        if (!"".equals(msg)) {
            dictionary.add(dictionary.get(longestIndex) + msg.charAt(0));
        }
        answer.add(longestIndex);
        return msg;
    }

    public static void main(String[] args) {
        int[] kakaos = new Compression().solution("KAKAO");
        for (int kakao : kakaos) {
            System.out.println(kakao);
        }
    }
}
