package programmers.kakao_2020;

import java.util.HashMap;
import java.util.Map;

public class 가사검색 {

    private Trie[] tries;
    private Trie[] reverseTries;

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        tries = new Trie[10000];
        reverseTries = new Trie[10000];
        for (int i = 0; i < tries.length; i++) {
            tries[i] = new Trie();
            reverseTries[i] = new Trie();
        }

        for (String word : words) {
            int length = word.length();
            tries[length - 1].insert(word);
            reverseTries[length - 1].insert(reverseString(word));
        }

        for (int i = 0, queriesLength = queries.length; i < queriesLength; i++) {
            String query = queries[i];
            int length = query.length();
            if (query.charAt(0) == '?') {
                answer[i] = reverseTries[length - 1].contains(reverseString(query));
            } else {
                answer[i] = tries[length - 1].contains(query);
            }
        }

//        print(answer);

        return answer;
    }

    private void print(int[] answer) {
        for (int i : answer) {
            System.out.print(i);
        }
        System.out.println();
    }

    private String reverseString(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[length - i - 1];
            chars[length - 1 - i] = temp;
        }

        return String.valueOf(chars);
    }

    static class Trie {
        private TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }


        public void insert(String word) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                currentNode.addCount();
                currentNode = currentNode.getNode().computeIfAbsent(c, character -> new TrieNode());
            }
            currentNode.addCount();
        }

        public int contains(String query) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < query.length(); i++) {
                char c = query.charAt(i);

                if (c == '?') {
                    return currentNode.getCount();
                } else if (currentNode.getNode().containsKey(c)) {
                    currentNode = currentNode.getNode().get(c);
                } else {
                    return 0;
                }
            }

            return currentNode.getCount();
        }
    }

    static class TrieNode {
        private Map<Character, TrieNode> node = new HashMap<>();
        int count;

        public Map<Character, TrieNode> getNode() {
            return node;
        }

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        new 가사검색().solution(new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"});
        new 가사검색().solution(new String[]{"a","b","c"}, new String[]{"a"});
        new 가사검색().solution(new String[]{"fdo", "fzen"}, new String[]{"f???"});
        new 가사검색().solution(new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"frodo", "forno", "frost", "frozen", "prof"});
    }
}
