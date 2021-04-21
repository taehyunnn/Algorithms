package programmers.monthly_code_challenge;

public class 이진변환반복하기 {
    public int[] solution(String s) {
        int removeCntOfZero = 0;
        int repeatCnt = 0;

        StringBuilder stringBuilder = new StringBuilder(s);
        while (!stringBuilder.toString().equals("1")) {
            repeatCnt++;

            removeCntOfZero += getZeroCnt(stringBuilder);
            int length = stringBuilder.length();
            stringBuilder = getString(length);
        }

        return new int[]{repeatCnt, removeCntOfZero};
    }

    private StringBuilder getString(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        while (length != 0) {
            int i = length % 2;
            length /= 2;
            stringBuilder.append(i);
            stringBuilder.reverse();
        }

        return stringBuilder;
    }

    private int getZeroCnt(StringBuilder stringBuilder) {
        int repeatCnt = 0;
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            if (stringBuilder.charAt(i) == '0') {
                stringBuilder.deleteCharAt(i);
                repeatCnt++;
            }
        }
        return repeatCnt;
    }
}
