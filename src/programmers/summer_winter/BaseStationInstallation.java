package programmers.summer_winter;

public class BaseStationInstallation {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // start : 전파가 통하는 첫번째 위치, end : 전파가 통하는 마지막 위치
        int end = 0, start = 0;
        for (int station : stations) {

            start = station - w;
            if (start > end + 1) {
                int cnt = start - end - 1;
                answer += (cnt - 1) / (1 + 2 * w) + 1;
            }
            end = station + w;
        }

        if (end < n) {
            answer += (n - end - 1) / (1 + 2 * w) + 1;
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(new BaseStationInstallation().solution(11,new int[]{4,11},1));
        System.out.println(new BaseStationInstallation().solution(10, new int[]{1, 10}, 1));
    }
}
