package brute_force;

import java.util.HashSet;
import java.util.Set;

public class PrimeNumber {
    public int solution(String numbers) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();
        permutation("",numbers,set);

        for (Integer integer : set) {
            if(isPrime(integer)){
                answer++;
            }
        }

        return answer;
    }

    //순열방식으로 각각의 자리를 만들기
    public void permutation(String prefix, String str, Set<Integer> set) {
        int length = str.length();
        if(!prefix.equals("")) {
            set.add(Integer.valueOf(prefix)); //스트링을 Interger로 변환
        }

        for (int i = 0; i < length; i++){
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, length), set);
        }

    }

    //소수찾기
    public boolean isPrime(int n){
        if(n==0 || n==1) return false;
        if(n==2) return true;
        if(n%2 == 0) return false;
        for(int i=3; i<=(int)Math.sqrt(n); i+=2){
            if(n%i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PrimeNumber().solution("1"));
    }
}
