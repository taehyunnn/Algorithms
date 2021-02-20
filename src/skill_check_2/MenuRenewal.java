package skill_check_2;

import java.util.*;

public class MenuRenewal {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        Map<String, Integer>[] map = new HashMap[27];

        for (int i = 0; i < map.length; i++) {
            map[i] = new HashMap<>();
        }

        for (String order : orders) {
            combine( order, new StringBuilder(), 0, map);
        }

        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (int i : course) {
            int max = 0;

            for (Map.Entry<String, Integer> stringIntegerEntry : map[i].entrySet()) {
                if(max < stringIntegerEntry.getValue()){
                    max = stringIntegerEntry.getValue();
                    temp.clear();
                    temp.add(stringIntegerEntry.getKey());
                } else if (max == stringIntegerEntry.getValue()) {
                    temp.add(stringIntegerEntry.getKey());
                }
            }

            if(max <2){
                continue;
            }
            result.addAll(temp);
        }
        result.sort(null);

        return result.toArray(new String[0]);
    }

    private void combine(String instr, StringBuilder outstr, int index, Map[] map) {
        for (int i = index; i < instr.length(); i++) {
            outstr.append(instr.charAt(i));

            char[] temp = outstr.toString().toCharArray();
            Arrays.sort(temp);
            String candidate = String.valueOf(temp);

            map[outstr.length()].put(candidate, (int) map[outstr.length()].getOrDefault(candidate, 0) +1);

            combine(instr,outstr,i+1, map);
            outstr.deleteCharAt(outstr.length() -1);
        }
    }



    public static void main(String[] args) {

        String[] solution = new MenuRenewal().solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
        for (String s : solution) {
            System.out.println("s = " + s);
        }
    }
}
