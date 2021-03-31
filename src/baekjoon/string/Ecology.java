package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ecology {

    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new TreeMap<>();

        double cnt = 0;
        while (true) {
            String s = br.readLine();
            if ("".equals(s) || s == null) {
                break;
            }
            cnt++;
            map.put(s, map.getOrDefault(s, 0) + 1);
        }


        for (Map.Entry<String, Integer> s : map.entrySet()) {
            System.out.printf("%s %.4f\n",s.getKey(), s.getValue()/cnt*100);
        }
    }
}
