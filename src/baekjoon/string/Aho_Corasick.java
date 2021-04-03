package baekjoon.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Aho_Corasick {

    static class Trie {
        TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        void insert(String string) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);

                currentNode = currentNode.getChildNode().computeIfAbsent(c, character -> new TrieNode());
            }
            currentNode.setEndChar(true);
        }

        void initFail() {
            rootNode.setFailNode(rootNode);

            Queue<TrieNode> queue = new LinkedList<>();
            queue.add(rootNode);

            while (!queue.isEmpty()) {
                TrieNode currentNode = queue.poll();

                for (Map.Entry<Character, TrieNode> nextEntrySet : currentNode.getChildNode().entrySet()) {
                    TrieNode nextTrieNode = nextEntrySet.getValue();
                    Character key = nextEntrySet.getKey();

                    // next노드의 fail노드로 설정할 destination 변수. 임시로 currentNode의 fail노드를 담아놓는다.
                    TrieNode failDestination = currentNode.getFailNode();

                    // 루트 자식들의 fail은 루트
                    if (currentNode == rootNode) {
                        nextTrieNode.setFailNode(rootNode);
                    } else {

                        // fail을 참조할 가장 가까운 노드를 찾아간다.
                        while (failDestination != rootNode && !failDestination.getChildNode().containsKey(key)) {
                            failDestination = failDestination.getFailNode();
                        }

                        if (failDestination.getChildNode().containsKey(key)) {
                            failDestination = failDestination.getChildNode().get(key);
                        }

                        nextTrieNode.setFailNode(failDestination);
                    }

                    if (failDestination.isEndChar()) {
                        nextTrieNode.setEndChar(true);
                    }

                    queue.add(nextTrieNode);
                }
            }
        }

        boolean contains(String string) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);

                // 현재 노드에서 다음 노드로 갈 수 없으면 fail을 따라간다.
                while (currentNode != rootNode && !currentNode.getChildNode().containsKey(c)) {
                    currentNode = currentNode.getFailNode();
                }

                // 알파벳을 가지고 있으면 다음 노드로 넘어감
                if (currentNode.getChildNode().containsKey(c)) {
                    currentNode = currentNode.getChildNode().get(c);
                }

                // 현재 노드에 output이 있으면 찾은 것
                if (currentNode.isEndChar()) {
                    return true;
                }
            }

            return false;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        TrieNode failNode;
        boolean endChar;

        public Map<Character, TrieNode> getChildNode() {
            return childNode;
        }

        public boolean isEndChar() {
            return endChar;
        }

        public void setEndChar(boolean endChar) {
            this.endChar = endChar;
        }

        public TrieNode getFailNode() {
            return failNode;
        }

        public void setFailNode(TrieNode failNode) {
            this.failNode = failNode;
        }
    }

}
