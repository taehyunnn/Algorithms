package programmers.skill_check_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecondKeyByBitmasking {

    // 비트 마스킹을 이용한 풀이
    public int solution(String[][] relation) {
        int answer = 0;

        List<Set<Integer>> candidateKeyList = new ArrayList<>();

        int colLen = relation[0].length;

        for (int i = 0; i < (1<<colLen); i++) {
            Set<Integer> keySet = makePossibleKey(colLen, i);

            if(isMinimal(candidateKeyList, keySet) && isUnique(keySet,relation)){
                candidateKeyList.add(keySet);
                answer++;
            }
        }

        return answer;
    }

    private boolean isMinimal(List<Set<Integer>> candidateKeyList, Set<Integer> keySet) {
        for (Set<Integer> integers : candidateKeyList) {
            if(keySet.containsAll(integers)){
                return false;
            }
        }
        return true;
    }

    private boolean isUnique(Set<Integer> keySet, String[][] relation) {
        Set<String> uniqueCheckSet = new HashSet<>();

        for (int i = 0; i < relation.length; i++) {
            StringBuilder key = new StringBuilder();

            for (Integer integer : keySet) {
                key.append(relation[i][integer]);
            }

            if (uniqueCheckSet.contains(key.toString())) {
                return false;
            }
            uniqueCheckSet.add(key.toString());
        }

        return true;
    }


    private Set<Integer> makePossibleKey(int colLen, int i) {
        Set<Integer> keySet = new HashSet<>();
        for (int j = 0; j < colLen; j++) {
            if((i & (1<<j)) > 0){
                keySet.add(j);
            }
        }
        return keySet;
    }

    public static void main(String[] args) {
        System.out.println(new SecondKeyByBitmasking().solution(new String[][]{{"a","b","c"},{"a","b","cc"},{"q","bb","ff"}}));
    }
}
