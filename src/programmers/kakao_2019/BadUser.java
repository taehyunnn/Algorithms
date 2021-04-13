package programmers.kakao_2019;

import java.util.*;

public class BadUser {
    public int solution(String[] user_id, String[] banned_id) {
        Set<Integer> set = new HashSet<>();

        String[] regex_id = new String[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            regex_id[i] = banned_id[i].replace("*", ".");
        }

        dfs(user_id, regex_id, 0, 0, set);

        return set.size();
    }

    private void dfs(String[] user_id, String[] regex_id, int currentDepth, int bitMasking, Set<Integer> set) {
        if (regex_id.length == currentDepth) {
            set.add(bitMasking);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (user_id[i].matches(regex_id[currentDepth]) && ((bitMasking >> i) & 1) == 0) {
                dfs(user_id, regex_id, currentDepth + 1, (bitMasking | (1 << i)), set);
            }
        }
    }

    public static void main(String[] args) {
        new BadUser().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"});
    }

}
