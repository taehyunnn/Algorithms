package kakao_2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRenewal {
    Map<String, Integer>[] map = new HashMap[26];

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i = 0; i < course.length; i++) {
            int length = course[i];
            map[course[i]] = new HashMap<>();

            for (int j = 0; j < orders.length; j++) {
                exe(orders[i], length);
            }
        }

        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            int max = 0;
            for (Map.Entry<String, Integer> integer : map[course[i]].entrySet()) {
                if(max < integer.getValue()){
                    max = integer.getValue();
                    temp.clear();
                    temp.add(integer.getKey());
                    continue;
                }

                if(max == integer.getValue()){
                    temp.add(integer.getKey());
                }
            }
            result.addAll(temp);
        }

        answer = result.toArray(new String[result.size()]);
        return answer;
    }

    private void exe(String order, int length) {
        if (order.length() < length)
            return;

        boolean[] isUsed = new boolean[order.length()];
        char[] result = new char[length];
        subtract(order.toCharArray(), result, length, 0, isUsed);
    }

    private void subtract(char[] array, char[] result, int length, int cur, boolean[] isUsed) {
        if (length == cur) {
            String str = String.valueOf(result);
            map[length].put(str, map[length].getOrDefault(str, 0) + 1);
            return;
        }

        for (int i = cur; i < array.length - length; i++) {
            if (isUsed[i])
                continue;

            result[cur] = array[i];
            isUsed[i] = true;
            subtract(array, result, length, cur + 1, isUsed);
            isUsed[i] = false;
        }
    }

    public static void main(String[] args) {
        String[] solution = new MenuRenewal().solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        for (String s : solution) {
            System.out.println("s = " + s);
        }
    }
}
