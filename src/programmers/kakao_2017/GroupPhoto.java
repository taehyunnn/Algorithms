package programmers.kakao_2017;

public class GroupPhoto {

    private final int numOfFriends = 8;
    private char[] friends = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private char[] permutation = new char[numOfFriends];
    private String[] data;
    private int answer = 0;

    public int solution(int n, String[] data) {
        this.data = data;

        // 8! 경우의 수를 모두 만든다.
        // 만들어진 수 중에서 조건을 만족하지 않으면 제외한다
        permuteAll(0, numOfFriends, 0);

        return answer;
    }

    private void permuteAll(int currentDepth, int numOfFriends, int visit) {
        if (currentDepth == numOfFriends) {
            if (isSatisfied()) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < numOfFriends; i++) {
            if (isVisited(visit, i)) {
                continue;
            }

            permutation[currentDepth] = friends[i];
            permuteAll(currentDepth + 1, numOfFriends, visit | (1 << i));
        }
    }

    private boolean isVisited(int visit, int i) {
        return ((1 << i) & visit) != 0;
    }

    private boolean isSatisfied() {
        for (String conditions : data) {
            char one = conditions.charAt(0);
            char another = conditions.charAt(2);
            char condition = conditions.charAt(3);
            int num = conditions.charAt(4) - '0';

            if (!check(one, another, condition, num)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(char one, char another, char condition, int num) {
        int gap = findGap(one, another) - 1;

        return conditionCheck(gap, condition, num);
    }

    private boolean conditionCheck(int gap, char condition, int num) {
        switch (condition) {
            case '<':
                return gap < num;
            case '>':
                return gap > num;
            case '=':
                return gap == num;
        }
        return false;
    }

    private int findGap(char one, char another) {
        int oneIndex = getIndex(one);
        int anotherIndex = getIndex(another);
        return Math.abs(oneIndex - anotherIndex);
    }

    private int getIndex(char one) {
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] == one) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new GroupPhoto().solution(2, new String[]{"N~F=0", "R~T>5"}));
    }
}
