package hash;

import java.util.HashMap;
import java.util.Map;

public class Camouflage {

    public int solution(String[][] clothes) {
        int answer = 0;
        int count = 1;
        Map<String, Integer> map = new HashMap<>();

        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        for (String s : map.keySet()) {
            count *= (map.get(s)+1);
        }

        answer = count - 1;

        return answer;
    }

}
