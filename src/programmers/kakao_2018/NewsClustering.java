package programmers.kakao_2018;

import java.util.ArrayList;
import java.util.List;

public class NewsClustering {
    public int solution(String str1, String str2) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        splitStringByTwoWords(str1, list1);
        splitStringByTwoWords(str2, list2);

        if (list1.size() == 0 && list2.size() == 0) {
            return 1 * 65536;
        }


        int min = 0;
        int max = 0;

        int index1 = 0;
        int index2 = 0;

        list1.sort(null);
        list2.sort(null);

        while (index1 < list1.size() && index2 < list2.size()) {
            max++;

            int compareResult = list1.get(index1).compareTo(list2.get(index2));

            if (compareResult > 0) {
                index2++;
            } else if (compareResult < 0) {
                index1++;
            } else {
                index1++;
                index2++;
                min++;
            }
        }

        max += list1.size() + list2.size() - index1 - index2;

        return (int) (((double)min / max) * 65536);
    }

    private void splitStringByTwoWords(String str1, List<String> list1) {
        for (int i = 0; i < str1.length() - 1; i++) {
            String temp = str1.substring(i, i + 2);

            boolean flag = true;
            for (int j = 0; j < temp.length(); j++) {
                if (!(temp.charAt(j) >= 'a' && temp.charAt(j) <= 'z')) {
                    flag = false;
                }
            }

            if (flag) {
                list1.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new NewsClustering().solution("FRANCE", "french"));
    }
}
