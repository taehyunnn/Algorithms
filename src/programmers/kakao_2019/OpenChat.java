package programmers.kakao_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChat {
    public String[] solution(String[] record) {
        String[] answer = {};

        Map<String, String> id = new HashMap<>();

        List<String> message = new ArrayList<>();

        for (String s : record) {
            String[] split = s.split(" ");
            switch (split[0]){
                case "Enter":
                case "Change":
                    id.put(split[1], split[2]);
                    break;
            }
        }

        for (String s : record) {
            String[] split = s.split(" ");
            switch (split[0]){
                case "Enter":
                    message.add(id.get(split[1]) + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    message.add(id.get(split[1]) + "님이 나갔습니다.");
                    break;
            }
        }
        return message.toArray(new String[0]);
    }
}
