import java.util.HashMap;
import java.util.Map;

public class Tuple {

    public int[] solution(String s) {
        s = s.replaceAll("\\}\\,\\{", "#");
        s = s.substring(2, s.length() - 2);

        String[] split = s.split("#");

        Map<String, Integer> map = new HashMap<>();

        for (String s1 : split) {
            String[] splitTemp = s1.split(",");
            for (String s2 : splitTemp) {
                map.put(s2, map.getOrDefault(s2, 0) +1);
            }
        }

        int[] answer = new int[map.size()];
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            answer[answer.length - stringIntegerEntry.getValue()] = Integer.parseInt(stringIntegerEntry.getKey());
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] solution = new Tuple().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");

        for (int i : solution) {
            System.out.println(i);
        }
    }

}
