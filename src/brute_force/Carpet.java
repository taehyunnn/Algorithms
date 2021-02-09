package brute_force;

public class Carpet {

    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        int width;
        for (int height = 1; height <= Math.sqrt(brown + yellow); height++) {
            if( (brown + yellow) % height != 0){
                continue;
            }

            width = (brown + yellow) / height;


            if (width == ((brown - 2 * height + 4) / 2)) {
                answer = new int[]{width, height};
                break;
            }
        }
        return answer;
    }
}
