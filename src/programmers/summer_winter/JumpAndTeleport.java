package programmers.summer_winter;

public class JumpAndTeleport {


    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                ans++;
            }
            n >>= 1;
        }
        return ans;
    }

}
