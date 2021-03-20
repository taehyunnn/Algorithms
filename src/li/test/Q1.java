package li.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1 {

    public String solution(String[] table, String[] languages, int[] preference) {
        List<Map<String, Integer>> tableList = new ArrayList<>();
        List<String> jobs = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            tableList.add(new HashMap<>());
        }


        for (int i = 0; i < tableList.size(); i++) {
            int score = 5;
            String[] split = table[i].split(" ");
            jobs.add(split[0]);

            for (int j = 1; j < split.length; j++) {
                tableList.get(i).put(split[j], score--);
            }
        }

        int max = 0;
        int index = 0;
        for (int i = 0; i < tableList.size(); i++) {
            int score = 0;
            for (int j = 0; j < languages.length; j++) {
                Integer integer = tableList.get(i).get(languages[j]);
                if (integer != null) {
                    score += tableList.get(i).get(languages[j]) * preference[j];
                }
            }
            if (max < score) {
                max = score;
                index = i;
            } else if (max == score) {
                if (jobs.get(index).compareTo(jobs.get(i)) > 0) {
                    index = i;
                }
            }
        }
        return jobs.get(index);
    }

    public static void main(String[] args) {
        new Q1().solution(new String[]{"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"}, new String[]{"JAVA", "JAVASCRIPT"}, new int[]{7, 5});
    }
}
