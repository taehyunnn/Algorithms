import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public int solution(int n) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();
        while(n !=0){
            list.add(n%3);
            n/=3;
        }

        int temp = 1;
        for (int i = 0; i < list.size(); i++) {
            answer += list.get(list.size() -i -1) * temp;
            temp *= 3;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q1().solution(125));
    }
}
