package programmers.kakao_2018;

import java.util.PriorityQueue;
import java.util.Queue;

public class ShuttleBus {
    /**
     * @param n         셔틀 운행 횟수
     * @param t         셔틀 운행 간격
     * @param m         한 셔틀에 태울 수 있는 사람의 수
     * @param timetable 셔틀타러 오는 사람들의 시간
     */
    private Queue<Integer> times;

    // n: 운행 횟수
    // t: 운행 간격
    // m: 한 셔틀에 탈 수 있는 최대 크루 수
    // timetable : 크루가 대기열에 도착하는 시각
    public String solution(int n, int t, int m, String[] timetable) {
        init(timetable);

        // n-1 번까지 셔틀을 보낸다.
        int STARTTIME = 540;
        for(int i = 0; i<n-1; i++){
            // 한번에 m명 가능
            for(int j=0; j<m; j++){
                if(!times.isEmpty() && times.peek() > STARTTIME + i*t){// 이 시각에 탈 사람이 없다
                    break;
                }
                times.poll();
            }
        }

        // n번째 셔틀에 m번째 들어가는 애보다 -1분 빨리 도착한다.
        int temp=0;
        for(int i=0; i<m-1; i++){
            if(!times.isEmpty() && times.peek() > STARTTIME + (n-1)*t){
                break;
            }
            times.poll();
        }

        if(times.isEmpty()){
            temp = STARTTIME + (n-1)*t;
        } else {
            temp = Math.min(STARTTIME + (n-1)*t, times.poll()-1);
        }

        return minToTime(temp);
    }

    public String minToTime(int min){
        int hour = min/60;
        min = min%60;
        return String.format("%02d:%02d",hour,min);
    }

    public void init(String[] timetable){
        times = new PriorityQueue<>();

        for(String s : timetable){
            times.add(timeToMin(s));
        }
    }

    public int timeToMin(String s){
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        return hour*60 + min;
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
