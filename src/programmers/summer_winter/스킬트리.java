package programmers.summer_winter;

public class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String s : skill_trees){
            s = s.replaceAll("[^+"+skill+"]", "");
            if(skill.indexOf(s) == 0){
                answer++;
            }
        }

        return answer;
    }
}
