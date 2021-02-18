package skill_check_2;

import java.util.*;

public class RankSearch {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        Map<String, List<Integer>> infos = new HashMap<>();

        createInfos(info, infos);

        for (List<Integer> value : infos.values()) {
            value.sort(null);
        }

        checkCondition(query, answer, infos);

        return answer;
    }

    private void createInfos(String[] info, Map<String, List<Integer>> infos) {
        for (String s : info) {
            String[] s1 = s.split(" ");
            int score = Integer.parseInt(s1[4]);
            for (int i = 0; i < 16; i++) {
                StringBuilder key = new StringBuilder();

                for (int j = 0; j < 4; j++) {
                    if ((i & 1 << j) > 0) {
                        key.append(s1[j]);
                    }
                }
                infos.computeIfAbsent(key.toString(), o-> new ArrayList<Integer>()).add(score);
            }
        }
    }

    private void checkCondition(String[] query, int[] answer, Map<String, List<Integer>> infos) {
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].replaceAll("-", "").split(" and | ");
            String join = String.join("",split[0], split[1], split[2], split[3]);
            int conditionScore = Integer.parseInt(split[4]);
            List<Integer> list = infos.getOrDefault(join, new ArrayList<>());

            int conditionPass = binarySearch(conditionScore, list);

            answer[i] = list.size() - conditionPass;
        }
    }

    private int binarySearch(int conditionScore, List<Integer> list) {
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = (left + right) /2;
            if( list.get(mid) < conditionScore){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }



    public static void main(String[] args) {
        int[] solution = new RankSearch().solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}, new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}
