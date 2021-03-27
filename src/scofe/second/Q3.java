package scofe.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q3 {

    private static List<List<Integer>> downLinks;
    private static List<List<Integer>> upLinks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int questionCnt = Integer.parseInt(stringTokenizer.nextToken());

        downLinks = new ArrayList<>(n + 1);
        upLinks = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            downLinks.add(new ArrayList<>());
            upLinks.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(stringTokenizer.nextToken());
            int down = Integer.parseInt(stringTokenizer.nextToken());

            downLinks.get(up).add(down);
            upLinks.get(down).add(up);
        }

        List<int[]> questions = new ArrayList<>(questionCnt);

        for (int i = 0; i < questionCnt; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int up = Integer.parseInt(stringTokenizer.nextToken());
            int down = Integer.parseInt(stringTokenizer.nextToken());

            questions.add(new int[]{up, down});
        }

        for (int[] question : questions) {
            if (isRight(question[0], question[1])) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }


    private static boolean isRight(int up, int down) {
        return false;
    }
}
