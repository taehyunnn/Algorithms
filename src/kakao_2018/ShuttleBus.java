package kakao_2018;

import java.util.Arrays;

public class ShuttleBus {
    /**
     * @param n         셔틀 운행 횟수
     * @param t         셔틀 운행 간격
     * @param m         한 셔틀에 태울 수 있는 사람의 수
     * @param timetable 셔틀타러 오는 사람들의 시간
     */
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;

        int[] minTimeTable = timeToMin(timetable);
        int START_TIME = 540;    // 9:00 에 셔틀 처음으로 시작

        Arrays.sort(minTimeTable);

        int nextPersonIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m && nextPersonIndex < timetable.length; j++) {
                if (minTimeTable[nextPersonIndex] <= START_TIME + (t * i)) {
                    nextPersonIndex++;
                } else {
                    break;
                }
            }
        }

        int lastNumOfPerson = 0;

        for (int i = 0; i < m && nextPersonIndex < timetable.length; i++) {
            if (minTimeTable[nextPersonIndex] <= START_TIME + (t * (n - 1))) {
                nextPersonIndex++;
                lastNumOfPerson++;
            }
        }


        if (lastNumOfPerson == m) {
            answer = minTimeTable[nextPersonIndex-1] -1;
        } else {
            answer = START_TIME + (t * (n - 1));
        }

        return minToTime(answer);
    }

    private String minToTime(int answer) {
        return String.format("%02d:%02d", answer / 60, answer % 60);
    }

    private int[] timeToMin(String[] timetable) {
        int[] mins = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String s = timetable[i];
            String[] split = s.split(":");

            mins[i] = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }
        return mins;
    }

    public static void main(String[] args) {
        System.out.println(new ShuttleBus().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(new ShuttleBus().solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(new ShuttleBus().solution(2, 1,2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(new ShuttleBus().solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(new ShuttleBus().solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(new ShuttleBus().solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}
