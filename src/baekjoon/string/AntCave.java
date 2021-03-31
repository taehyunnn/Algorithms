package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class AntCave {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();

            String[] replace = temp.split(" ");
            String[] t = Arrays.copyOfRange(replace, 1, replace.length);
            trie.insert(t);
        }

        trie.print();
    }

    static class Trie {
        TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        void insert(String[] strings) {

            TrieNode currentNode = rootNode;
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i];
                currentNode = currentNode.getChildNode().computeIfAbsent(string, s -> new TrieNode());
            }

            currentNode.setEndCheck(true);
        }

        boolean contains(String[] strings) {

            TrieNode currentNode = rootNode;
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i];

                if (!currentNode.getChildNode().containsKey(string)) {
                    return false;
                }

                currentNode = currentNode.getChildNode().get(string);
            }
            return currentNode.isEndCheck();
        }

        void delete(String[] strings) {
            delete(rootNode, strings, 0);
        }

        private void delete(TrieNode parentNode, String[] strings, int index) {
            String string = strings[index];
            if (!parentNode.getChildNode().containsKey(string)) {
                return;
            }

            index++;
            TrieNode currentNode = parentNode.getChildNode().get(string);

            if (index == strings.length) { // 마지막에 도착. 단어가 존재하면서 다른 자식이 없으면 삭제
                if (!currentNode.isEndCheck()) {
                    return;
                }

                currentNode.setEndCheck(false);
                if (currentNode.getChildNode().isEmpty()) {
                    parentNode.getChildNode().remove(string);
                }

            } else {
                delete(currentNode,strings,index);

                // 다른 자식이 없으면서 현재 노드로 끝나는 단어가 없을때만 삭제
                if (currentNode.getChildNode().isEmpty() && !currentNode.isEndCheck()) {
                    parentNode.getChildNode().remove(string);
                }
            }
        }

        void print(){
            print(rootNode, 0);
        }

        private void print(TrieNode parentNode, int depth) {
            for (String string : parentNode.getChildNode().keySet()) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("--");
                }
                System.out.println(string);

                TrieNode currentNode = parentNode.getChildNode().get(string);
                print(currentNode, depth + 1);
            }
        }

        boolean isEmpty(){
            return rootNode.getChildNode().isEmpty();
        }
    }

    static class TrieNode {
        Map<String, TrieNode> childNode = new TreeMap<>();
        boolean endCheck;

        public Map<String, TrieNode> getChildNode() {
            return childNode;
        }

        public boolean isEndCheck() {
            return endCheck;
        }

        public void setEndCheck(boolean endCheck) {
            this.endCheck = endCheck;
        }
    }
}
