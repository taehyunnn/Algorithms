package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PhoneNumberList {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int testCnt = Integer.parseInt(stringTokenizer.nextToken());


        for (int i = 0; i < testCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int phoneNumberCnt = Integer.parseInt(stringTokenizer.nextToken());
            List<String> list = new ArrayList<>(phoneNumberCnt);
            Trie trie = new Trie();

            for (int j = 0; j < phoneNumberCnt; j++) {
                list.add(bufferedReader.readLine());
            }

            Collections.sort(list);

            boolean flag = true;

            for (int j = 0; j < phoneNumberCnt; j++) {
                if (trie.insert(list.get(j))) {
                    flag = false;
                    break;
                }
            }

            System.out.println(flag ? "YES" : "NO");
        }
    }

    static class Trie {
        private TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        boolean insert(String word) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                currentNode = currentNode.getChildNode().computeIfAbsent(ch, character -> new TrieNode());
                if (currentNode.isEndChar()) {  // 마지막 단어면 접두어가 존재
                    return true;
                }
            }

            currentNode.setEndChar(true);
            return false;
        }

        boolean contains(String word) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (!currentNode.getChildNode().containsKey(ch)) {
                    return false;
                }

                currentNode = currentNode.getChildNode().get(ch);
            }

            return currentNode.isEndChar();
        }

        void delete(String word) {
            delete(rootNode, word, 0);
        }

        private void delete(TrieNode parentNode, String word, int index) {
            char ch = word.charAt(index);

            if (!parentNode.getChildNode().containsKey(ch)) {
                throw new Error("");
            }

            TrieNode currentNode = parentNode.childNode.get(ch);
            index++;

            if (word.length() == index) {


                // 노드는 존재하지만 삽입한 게 아닐 때
                if (!currentNode.isEndChar()) {
                    throw new Error("");
                }

                currentNode.setEndChar(false);

                // word의 마지막 문자가 자식이 없으면 삭제 진행
                if (currentNode.getChildNode().isEmpty()) {
                    parentNode.getChildNode().remove(ch);
                }
            } else {
                delete(currentNode, word, index);

                // 자식이 없으면서 현재 문자로 끝나는 단어가 없을때 삭제 진행
                if (currentNode.getChildNode().isEmpty() && !currentNode.isEndChar()) {
                    parentNode.getChildNode().remove(ch);
                }
            }
        }

        boolean isEmpty() {
            return rootNode.getChildNode().isEmpty();
        }
    }

    static class TrieNode {
        // 자식 노드 맵
        private Map<Character, TrieNode> childNode = new HashMap<>();
        // 마지막 글자인지 여부
        private boolean endChar;

        public Map<Character, TrieNode> getChildNode() {
            return childNode;
        }

        public boolean isEndChar() {
            return endChar;
        }

        public void setEndChar(boolean endChar) {
            this.endChar = endChar;
        }
    }
}
