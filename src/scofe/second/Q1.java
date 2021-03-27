package scofe.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q1 {

    private static int[] playTime;
    private static int[] sumOfPlayTime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] split = input.split(" ");

        int n = Integer.parseInt(split[0]);
        int totalTime = changePracticeTimeToSec(split[1]);

        playTime = new int[n + 1];
        sumOfPlayTime = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            playTime[i] = changePlayTimeToSec(br.readLine());
            sumOfPlayTime[i] = sumOfPlayTime[i - 1] + playTime[i];
        }

        int maxCnt = 1;
        int index = 0;
        for (int i = 1; i <= n; i++) {

            int currentCnt = 1;
            int currentTime = totalTime;
            for (int j = i; j <= n; j++) {
                if (currentTime - playTime[j] > 0) {
                    currentTime -= playTime[j];
                    currentCnt++;
                    if (j == n) {   // 마지막은 다음 곡이 없기 때문에 보정해준다
                        currentCnt--;
                    }
                } else {
                    break;
                }
            }
            if (maxCnt < currentCnt) {
                maxCnt = currentCnt;
                index = i;
            }
        }

        System.out.println(maxCnt + " " + index);
    }

    private static int changePlayTimeToSec(String s) {
        String[] split = s.split(":");
        int min = Integer.parseInt(split[0]);
        int sec = Integer.parseInt(split[1]);

        return min * 60 + sec;
    }

    private static int changePracticeTimeToSec(String s) {
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        int sec = Integer.parseInt(split[2]);

        return hour * 3600 + min * 60 + sec;
    }
}
