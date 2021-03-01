package kakao_2021;

public class AdInsertion {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "00:00:00";

        int playTime = timeToSec(play_time.split(":"));
        int adTime = timeToSec(adv_time.split(":"));


        int[] times = new int[363660];

        for (String log : logs) {
            String[] split = log.split("-");

            int startTim = timeToSec(split[0].split(":"));
            int endTime = timeToSec(split[1].split(":"));

            for (int j = startTim; j < endTime; j++) {
                times[j]++;
            }
        }

        long currentValue = 0;
        for (int i = 0; i < adTime; i++) {
            currentValue += times[i];
        }

        int resultTime = 0;
        long maxValue = currentValue;

        int i = 0;
        while (adTime + i < playTime) {
            currentValue = currentValue - times[i] + times[adTime+i];
            if (currentValue > maxValue) {
                maxValue = currentValue;
                resultTime = i+1;
            }
            i++;
        }
        return secToTime(resultTime);
    }



    private String secToTime(int time) {
        int hour = time / 3600;
        int min = (time / 60) % 60;
        int sec = time % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private int timeToSec(String[] split1) {
        return Integer.parseInt(split1[0]) * 3600 + Integer.parseInt(split1[1]) * 60 + Integer.parseInt(split1[2]);
    }

    public static void main(String[] args) {
//        System.out.println(new AdInsertion().solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"}));
        System.out.println(new AdInsertion().solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
    }
}
