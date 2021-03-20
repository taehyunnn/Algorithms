package scofe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int num = Integer.parseInt(input);

        int startTime = 0;
        int endTime = 23 * 60 + 59;
        for (int i = 0; i < num; i++) {
            String s = br.readLine();

            String[] split = s.split("~");
            int start = changeTimeToMin(split[0].trim());
            int end = changeTimeToMin(split[1].trim());

            startTime = Math.max(start, startTime);
            endTime = Math.min(end, endTime);
        }

        if (startTime <= endTime) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(changeMinToTime(startTime));
            stringBuilder.append(" ~ ");
            stringBuilder.append(changeMinToTime(endTime));
            System.out.println(stringBuilder.toString());
        } else {
            System.out.println(-1);
        }
    }

    private static String changeMinToTime(int time) {
        return String.format("%02d:%02d", time / 60, time%60);
    }

    private static int changeTimeToMin(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
