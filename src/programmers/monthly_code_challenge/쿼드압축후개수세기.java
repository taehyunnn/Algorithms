package programmers.monthly_code_challenge;

public class 쿼드압축후개수세기 {

    private int[] cnt;

    public int[] solution(int[][] arr) {
        cnt = new int[2];

        // arr 배열을 4개로 쪼개는 작업 실시 (재귀)
        divide(arr);

        return cnt;
    }

    private void divide(int[][] arr) {
        if (arr.length == 1) {
            cnt[arr[0][0]]++;
            return;
        }

        if (isAllSame(arr)) {
            cnt[arr[0][0]]++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            divide(getArr(arr, i));
        }
    }

    private int[][] getArr(int[][] arr, int i) {
        int length = arr[0].length / 2;
        int[][] temp = new int[length][length];

        int row = 0, col = 0;
        if (i == 1) {
            row = length;
        } else if (i == 2) {
            col = length;
        } else if (i == 3) {
            row = length;
            col = length;
        }

        for (int j = row; j < row + length; j++) {
            System.arraycopy(arr[j], col, temp[j - row], 0, length);
        }

        return temp;
    }

    private boolean isAllSame(int[][] arr) {
        int num = arr[0][0];
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (num != ints[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
