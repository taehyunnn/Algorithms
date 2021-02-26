package kakao_2021;

import java.util.Arrays;
import java.util.Comparator;

public class AdInsertion {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "00:00:00";

        int playTime = getSecondTime(play_time.split(":"));
        int adTime = getSecondTime(adv_time.split(":"));


        int[][] logTime = new int[logs.length+2][2];

        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split("-");

            logTime[i][0] = getSecondTime(split[0].split(":"));
            logTime[i][1] = getSecondTime(split[1].split(":"));
        }

        Arrays.sort(logTime, (o1, o2) -> o2[1] - o1[1]);
        int fromEnd = fromEndTime(adTime, logTime);

        Arrays.sort(logTime, Comparator.comparingInt(o -> o[0]));
        int fromStart = fromStartTime(adTime, logTime);

        int min = Math.min(fromEnd,fromStart);

        return getTimeWithString(min);
    }

    private int fromStartTime(int adTime, int[][] logTime) {
        int answer = -1 ;
        int maximumTime = 0;
        for (int i = 0; i < logTime.length; i++) {
            int startTime = logTime[i][0];
            int totalTime = 0;
            int endTime = startTime + adTime;

            for (int j = 0; j < logTime.length; j++) {
                if (logTime[j][1] < startTime) {
                    continue;
                }

                if (logTime[j][0] > endTime) {
                    break;
                }

                int min = Math.max(logTime[j][0], startTime);
                int max = Math.min(logTime[j][1], endTime);

                totalTime += max - min;
            }
            if (maximumTime < totalTime) {
                maximumTime = totalTime;
                answer = endTime;
            }
        }
        return answer;
    }

    private int fromEndTime(int adTime, int[][] logTime) {
        int answer = -1 ;
        int maximumTime = 0;
        for (int i = 0; i < logTime.length; i++) {
            int endTime = logTime[i][1];
            int totalTime = 0;
            int startTime = Math.max(endTime - adTime, 0);

            for (int j = 0; j < logTime.length; j++) {
                if (logTime[j][1] < startTime) {
                    break;
                }

                if (logTime[j][0] > endTime) {
                    continue;
                }

                int max = Math.min(logTime[j][1], endTime);
                int min = Math.max(logTime[j][0], startTime);

                totalTime += max - min;
            }
            if (maximumTime <= totalTime) {
                maximumTime = totalTime;
                answer = startTime;
            }
        }
        return answer;
    }

    private String getTimeWithString(int startTime) {
        int hour = startTime / 3600;
        int min = (startTime % 3600) / 60;
        int sec = startTime % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private int getSecondTime(String[] split1) {
        return Integer.parseInt(split1[0]) * 3600 + Integer.parseInt(split1[1]) * 60 + Integer.parseInt(split1[2]);
    }

    public static void main(String[] args) {
//        System.out.println(new AdInsertion().getTimeWithString(3902));
//        System.out.println(new AdInsertion().getSecondTime(new String[]{"01","05","02"}));
//        System.out.println(new AdInsertion().solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"}));
        System.out.println(new AdInsertion().solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
    }
}
