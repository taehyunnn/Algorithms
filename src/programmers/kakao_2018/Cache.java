package programmers.kakao_2018;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        CacheMap cacheMap = new CacheMap(cacheSize);

        for (String city : cities) {
            String s = city.toLowerCase();
            if (cacheMap.contains(s)) {
                answer++;
            } else {
                answer += 5;
            }
            cacheMap.put(s,s);
        }

        return answer;
    }

    static class CacheMap {
        private Map<String, String> map;

        CacheMap(int cacheSize) {
            // maxSize의 75퍼센트가 되면 사이즈를 두배로 늘리기 때문에 고정된 사이즈를 사용할경우 75퍼센트가 안되게 미리 사이즈를 조정해놓는다.
            map = new LinkedHashMap<>((cacheSize + 1) * 4 / 3 + 1, 0.75f, true) {
                @Override
                // put을 할 때, 주어진 조건을 만족하면 가장 오래된 entry를 삭제한다. 여기서 조건은 새 노드 삽입 후 size가 cacheSize보다 크면 안된다.
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    return size() > cacheSize;
                }
            };
        }

        void put(String key, String value) {
            map.put(key,value);
        }

        boolean contains(String s) {
            return map.containsKey(s);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Cache().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(new Cache().solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(new Cache().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
    }
}
