package baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 방번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] result = new int[51];
        int[] cost = new int[n];

        int minCost = Integer.MAX_VALUE;
        int minIndex = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(stringTokenizer.nextToken());

            if (minCost >= cost[i]) {
                minCost = cost[i];
                minIndex = i;
            }
        }

        int money = Integer.parseInt(br.readLine());
        int cnt = 0;    // 구매한 번호의 수
        // 가장 싼 번호로 세팅
        while (money >= minCost) {
            result[cnt++] = minIndex;
            money -= minCost;
        }

        int start = 0;  // 방 번호 시작 위치
        for (int i = 0; i < cnt; i++) {
            // 번호 하나를 팔아서 추가된 돈으로 더 큰 번호를 살 수 있는지 확인
            for (int j = n - 1; j > minIndex; j--) {
                if (money + minCost >= cost[j]) {
                    money += minCost - cost[j];
                    result[i] = j;
                    break;
                }
            }

            // 시작이 0 이면, 0 하나를 환불한다.
            if (result[start] == 0) {
                start++;
                money += minCost;
            }
        }

        // 0을 제외하곤 살 수 없다
        if (start == cnt) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < cnt; i++) {
            sb.append(result[i]);
        }

        System.out.println(sb.toString());
    }

}
