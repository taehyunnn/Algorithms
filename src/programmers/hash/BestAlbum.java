package programmers.hash;

import java.util.*;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        Map<String, List<Album>> map = new HashMap<>();
        Map<String, Integer> playsMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], new ArrayList());
            }
            playsMap.put(genres[i], playsMap.getOrDefault(genres[i], 0) + plays[i]);
            map.get(genres[i]).add(new Album(i, plays[i]));
        }

        while(playsMap.size() !=0 ){
            String maxGenre = searchMaxGenre(playsMap);
            playsMap.remove(maxGenre);

            List<Album> maxGenreList = map.get(maxGenre);

            Collections.sort(maxGenreList);

            for (int i = 0; i < 2 && i < maxGenreList.size(); i++) {
                answer.add(maxGenreList.get(i).getNumber());
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    private String searchMaxGenre(Map<String, Integer> playsMap) {
        int max = 0;
        String maxGenre = "";
        for (Map.Entry<String, Integer> stringIntegerEntry : playsMap.entrySet()) {
            if(max < stringIntegerEntry.getValue()){
                max = stringIntegerEntry.getValue();
                maxGenre = stringIntegerEntry.getKey();
            }
        }
        return maxGenre;
    }

    static class Album implements Comparable {
        private int number;
        private int play;

        public Album(int number, int play) {
            this.number = number;
            this.play = play;
        }

        public int getNumber() {
            return number;
        }

        public int getPlay() {
            return play;
        }


        @Override
        public int compareTo(Object o) {
            int compare = play - ((Album) o).getPlay();
            if (compare == 0) {
                return number - ((Album) o).getNumber();
            }
            return -compare;
        }
    }

    public static void main(String[] args) {
        System.out.println(new BestAlbum().solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }
}
