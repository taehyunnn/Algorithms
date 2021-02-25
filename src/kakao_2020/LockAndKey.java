package kakao_2020;

public class LockAndKey {
    public boolean solution(int[][] key, int[][] lock) {
        int lockLength = lock.length;
        int keyLength = key.length;

        int[][] newLock = new int[lockLength + 2 * keyLength - 2][lockLength + 2 * keyLength - 2];
        // newLock 초기화
        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                newLock[i + keyLength - 1][j + keyLength - 1] = lock[i][j];
            }
        }

        // key 회전
        for (int i = 0; i < 4; i++) {
            // 회전된 키 구하고
            key = rotateKey(key);

            for (int keyRow = 0; keyRow < keyLength + lockLength - 1; keyRow++) {
                for (int keyCol = 0; keyCol < keyLength + lockLength - 1; keyCol++) {
                    int[][] temp = new int[newLock.length][newLock[0].length];
                    for (int j = 0; j < temp.length; j++) {
                        temp[j] = newLock[j].clone();
                    }

                    for (int r = keyRow; r < keyRow + keyLength; r++) {
                        for (int c = keyCol; c < keyCol + keyLength; c++) {
                            temp[r][c] += key[r - keyRow][c - keyCol];
                        }
                    }
                    if (isSolved(temp, lockLength, keyLength)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isSolved(int[][] temp, int lockLength, int keyLength) {
        for (int i = keyLength - 1; i < lockLength + keyLength - 1; i++) {
            for (int j = keyLength - 1; j < lockLength + keyLength - 1; j++) {
                if (temp[i][j] != 1)
                    return false;
            }
        }

        return true;
    }

    private int[][] rotateKey(int[][] key) {
        int temp;
        int[][] newKey = new int[key[0].length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                newKey[j][newKey[0].length - i - 1] = key[i][j];
            }
        }
        return newKey;
    }

    public static void main(String[] args) {
        new LockAndKey().solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
    }
}
