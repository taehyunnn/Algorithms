package kakao_2018;

public class NumberGame {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        int currnetT = 0;
        int number = 0;
        StringBuilder currentString = new StringBuilder();
        // currentT 가 t가 될때까지 반복
        // 숫자 number를 n진수로 변환
        // p번째 순서의 m 차례마다 t++ 후 저장

        while (currnetT != t){
            while(currentString.length() < m){
                currentString.append(changeNumber(n, number++));
            }

            answer.append(currentString.charAt(p - 1));
            currnetT++;
            currentString = new StringBuilder(currentString.substring(m));
        }

        return answer.toString();
    }

    private String changeNumber(int n, int number) {
        StringBuilder result = new StringBuilder();
        do {
            int temp = number % n;
            number /= n;

            switch (temp){
                case 10:
                    result.append("A");
                    break;
                case 11:
                    result.append("B");
                    break;
                case 12:
                    result.append("C");
                    break;
                case 13:
                    result.append("D");
                    break;
                case 14:
                    result.append("E");
                    break;
                case 15:
                    result.append("F");
                    break;
                default:
                    result.append(temp);
            }
        } while (number != 0);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new NumberGame().solution(16,16,2,1));
    }
}
