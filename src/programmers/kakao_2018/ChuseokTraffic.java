package programmers.kakao_2018;

import java.util.Arrays;
import java.util.Comparator;

public class ChuseokTraffic {
    public int solution(String[] lines) {
        int answer = 0;

        // 공백으로 split, [0]은 버리고 [1]은 시, 분, 초를 초로 변환 (double)
        // [1]의 초를 기준으로 오름차순 정렬
        // [2]에서 s를 떼어내고, [1]의 초와 연산

        double[][] times = new double[lines.length][2];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] split = line.split(" ");
            double endTime  = changeTime(split[1].split(":"));
            double processTime = Double.parseDouble(split[2].substring(0,split[2].length()-1));
            times[i][1] = endTime;
            times[i][0] = Double.parseDouble(String.format("%.3f",endTime - processTime + 0.001));
        }

        Arrays.sort(times, Comparator.comparingDouble(t -> t[1]));

        if(times.length == 1){
            return 1;
        }

        for (int i = 0; i < times.length-1; i++) {
            int cnt = 1;
            for (int j = i+1; j < times.length; j++) {
                if(Double.parseDouble(String.format("%.3f",times[i][1] + 1)) > times[j][0]){
                    cnt++;
                }
            }
            answer = Math.max(answer,cnt);
        }

        return answer;
    }

    private double changeTime(String[] split) {
        double result = 0;

        result += Double.parseDouble(split[0]) *3600;
        result += Double.parseDouble(split[1]) * 60;
        result += Double.parseDouble(split[2]);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ChuseokTraffic().solution(new String[]{"2016-09-15 01:00:04.000 3.0s", "2016-09-15 01:00:02.500 0.001s"}));
    }
}
