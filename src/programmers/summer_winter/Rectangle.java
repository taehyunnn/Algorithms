package programmers.summer_winter;

public class Rectangle {
    public long solution(int w, int h) {
        long gcdNum = gcd(w, h);

        long tempW = w / gcdNum;
        long tempH = h / gcdNum;

        long unit = tempW + tempH - 1;

        long sumUnit = unit * gcdNum;

        return (long) w * h - sumUnit;
    }

    private long gcd(int w, int h) {
        long big = Math.max(w, h);
        long small = Math.min(w, h);

        long temp;

        while (small != 0) {
            temp = big % small;
            big = small;
            small = temp;
        }

        return big;
    }
}
