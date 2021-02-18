package skill_check_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SecondKey {

    public int solution(String[][] relation) {
        int answer = 0;

        Set<Integer> result = new HashSet<>();

        Set<String> key = new HashSet<>();

        for (int i = 0; i < relation[0].length; i++) {
            if (isPossibleKey(relation, key, i)){
                result.add(i);
            }
        }

        return answer;
    }

    private boolean isPossibleKey(String[][] relation, Set<String> key, int i) {
        for (int j = 0; j < relation.length; j++) {
            if(key.contains(relation[j][i])){
                return false;
            }
            key.add(relation[j][i]);
        }
        return true;
    }
}
