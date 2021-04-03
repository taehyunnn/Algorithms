package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 아호코라식 알고리즘 적용
public class DeterminingStringSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine());
        }

        trie.initFail();

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            if (trie.contains(br.readLine())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

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

        boolean contains(String string) {
            TrieNode currentNode = rootNode;

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                // 현재 노드에서 갈 수 없으면 fail을 계속 따라감
                while (currentNode != rootNode && !currentNode.getChildNode().containsKey(c)) {
                    currentNode = currentNode.getFail();
                }

                // 알파벳을 가지고 있으면 다음 노드로 넘어간다.
                if (currentNode.getChildNode().containsKey(c)) {
                    currentNode = currentNode.getChildNode().get(c);
                }

                //  현재 노드에 ouput이 있으면 찾은 것이다.
                if (currentNode.isEndChar()) {
                    return true;
                }
            }

            return false;
        }

        void initFail(){
            rootNode.setFail(rootNode);

            Queue<TrieNode> queue = new LinkedList<>();
            queue.add(rootNode);
            while (!queue.isEmpty()) {
                TrieNode currentTrieNode = queue.poll();

                // 모든 자식들에 대해 처리한다.
                for (Map.Entry<Character, TrieNode> next : currentTrieNode.getChildNode().entrySet()) {
                    TrieNode nextTrieNode= next.getValue();

                    TrieNode failDestination = currentTrieNode.getFail();

                    // 루트 자식들의 fail은 루트다.
                    if (currentTrieNode == rootNode) {
                        nextTrieNode.setFail(rootNode);
                    } else {

                        // fail을 참조할 가장 가까운 조상을 찾아간다.
                        while (failDestination != rootNode && !failDestination.getChildNode().containsKey(next.getKey())) {
                            failDestination = failDestination.getFail();
                        }

                        // fail(px) = go(fail(p) , x)
                        if (failDestination.getChildNode().containsKey(next.getKey())) {
                            failDestination = failDestination.getChildNode().get(next.getKey());
                        }

                        nextTrieNode.setFail(failDestination);
                    }

                    // fail(x) = y 일 때, output(y) 는 output(x) 에 포함된다.
                    if (failDestination.isEndChar()) {
                        nextTrieNode.setEndChar(true);
                    }

                    queue.add(nextTrieNode);
                }
            }
        }

    }

    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        TrieNode fail;
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

        public TrieNode getFail() {
            return fail;
        }

        public void setFail(TrieNode fail) {
            this.fail = fail;
        }
    }

}
