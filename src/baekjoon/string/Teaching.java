package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Teaching {

    private static boolean[] candidate;
    private static boolean[] used;
    private static String[] words;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        used = new boolean[26];
        candidate = new boolean[26];
        // a,n,t,i,c 는 무조건 사용. k-5개만 따로 확인
        used[0] = used[2] = used['n' - 'a'] = used['t' - 'a'] = used['i' - 'a'] = true;

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        words = new String[n];

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String temp = bufferedReader.readLine();
            words[i] = temp.substring(4, temp.length() - 4);
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == 'a' || words[i].charAt(j) == 'c' || words[i].charAt(j) == 't' || words[i].charAt(j) == 'i' || words[i].charAt(j) == 'n') {
                    continue;
                }
                set.add(words[i].charAt(j));
            }
        }

        // 가르칠 수 있는 글자의 수가 후보보다 많으면 모든 단어를 읽을 수 있다.
        if (set.size() <= k - 5) {
            System.out.println(words.length);
            return;
        }

        Character[] characters = set.toArray(new Character[0]);
        for (Character character : characters) {
            candidate[character - 'a'] = true;
        }

        bf(-1, 0, k - 5);
        System.out.println(result);
    }

    private static void bf(int index, int depth, int targetDepth) {
        if (depth == targetDepth) {
            checkWords();
            return;
        }

        for (int i = index + 1; i < 26; i++) {
            if (!candidate[i] || used[i]) {
                continue;
            }

            used[i] = true;
            bf(i, depth + 1, targetDepth);
            used[i] = false;
        }
    }

    private static void checkWords() {
        int cnt = 0;
        for (String word : words) {
            boolean[] realUsed = new boolean[26];

            for (int i = 0; i < word.length(); i++) {
                realUsed[word.charAt(i) - 'a'] = true;
            }

            if (isSame(realUsed)) {
                cnt++;
            }
        }
        result = Math.max(result, cnt);
    }

    private static boolean isSame(boolean[] realUsed) {
        for (int i = 0; i < 26; i++) {
            if ((!used[i]) && (realUsed[i])) {
                return false;
            }
        }
        return true;
    }
}
