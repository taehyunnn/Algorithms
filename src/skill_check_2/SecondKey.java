package skill_check_2;

import java.util.*;


public class SecondKey {

    private int answer = 0;
    private List<Set<Integer>> candidateList = new ArrayList<>();

    public int solution(String[][] relation) {
        for (int i = 1; i <= relation[0].length; i++) {
            makeKeySet(-1, 0, i, relation, new HashSet<>());
        }

        return answer;
    }

    private void makeKeySet(int index, int currentCount, int targetDepth, String[][] relation, Set<Integer> keySet) {
        if (currentCount == targetDepth) {
            if (isMinimal(keySet) && isUnique(keySet, relation)) {
                candidateList.add(keySet);
                answer++;
            }
            return;
        }

        for (int i = index+1 ; i < relation[0].length; i++) {
            Set<Integer> newKeySet = new HashSet<>(keySet);
            newKeySet.add(i);
            makeKeySet(i, currentCount+1, targetDepth, relation, newKeySet);
        }
    }

    private boolean isMinimal(Set<Integer> keySet) {
        for (Set<Integer> candidateSet : candidateList) {
            if (keySet.containsAll(candidateSet)) {
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


    public static void main(String[] args) {
        System.out.println(new SecondKey().solution(new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}}));
    }
}

