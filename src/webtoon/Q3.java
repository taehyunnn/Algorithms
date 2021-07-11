package webtoon;

public class Q3 {
    public int solution(String s, String t) {
        int result = 0;

        char[] chars = new char[s.length()];

        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            chars[idx] = c;
            if (isSame(chars, idx, t)) {
                idx -= t.length();
                result++;
            }
            idx++;
        }

        return result;
    }

    private boolean isSame(char[] chars, int idx, String t) {
        if (idx < t.length() - 1) {
            return false;
        }

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != chars[i + idx - t.length() + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Q3().solution("aabcbcd", "abc");
    }
}
