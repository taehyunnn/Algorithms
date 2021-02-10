package dfs_bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordConversion {
    public int solution(String begin, String target, String[] words) {
        Queue<Words> queue = new LinkedList<>();
        Set<String> check = new HashSet<>();

        queue.add(new Words(begin, 0));
        check.add(begin);

        while (!queue.isEmpty()) {
            Words current = queue.poll();
            String curWord = current.getWords();
            int curDepth = current.getDepth();

            for (int i = 0; i < words.length; i++) {
                if(check.contains(words[i])){
                    continue;
                }

                int cnt = 0;

                for (int j = 0; j < curWord.length(); j++) {
                    if(curWord.charAt(j) != words[i].charAt(j)){
                        cnt++;
                    }
                }
                if(cnt == 1){
                    if(words[i].equals(target)){
                        return curDepth+1;
                    }

                    check.add(words[i]);
                    queue.add(new Words(words[i], curDepth + 1));
                }
            }
        }
        return 0;
    }

    static class Words{
        private String words;
        private int depth;

        public Words(String words, int depth) {
            this.words = words;
            this.depth = depth;
        }

        public String getWords() {
            return words;
        }

        public int getDepth(){
            return depth;
        }
    }
}
