package scofe.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q4 {

    private static List<String> indexList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        indexList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            indexList.add(s);
        }

        int questionCnt = Integer.parseInt(br.readLine());
        String[] questions = new String[questionCnt];
        for (int i = 0; i < questionCnt; i++) {
            questions[i] = br.readLine();
        }

        for (int i = 0; i < questionCnt; i++) {
            System.out.println(search(questions[i]));
        }
    }

    private static int search(String question) {
        int cnt = 0;

        for (String s : indexList) {
            if (s.contains(question)) {
                cnt++;
            }
        }

        return cnt;
    }
}
