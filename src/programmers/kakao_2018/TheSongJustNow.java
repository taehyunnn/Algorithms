package programmers.kakao_2018;

public class TheSongJustNow {
    public String solution(String m, String[] musicinfos) {

        String answer = "(Nope)";
        int runningTime = 0;

        m = changeMelody(m);

        for (String musicinfo : musicinfos) {
            Music music = getMusic(musicinfo);
            if (music.contains(m) && runningTime < music.runningTime) {
                answer = music.title;
                runningTime = music.runningTime;
            }
        }

        return answer;
    }

    private String changeMelody(String melody) {
        StringBuilder tempMelody = new StringBuilder();
        for (int i = 0, builderIndex = 0; i < melody.length(); i++) {
            char c = melody.charAt(i);

            if (melody.charAt(i) == '#') {
                char ch = melody.charAt(i - 1);
                tempMelody.deleteCharAt(builderIndex - 1);
                tempMelody.append((char) (ch - ('A' - 'a')));
            } else {
                tempMelody.append(c);
                builderIndex++;
            }
        }
        return tempMelody.toString();
    }

    private Music getMusic(String musicinfo) {

        String[] split = musicinfo.split(",");
        int startTime = getTime(split[0]);
        int endTime = getTime(split[1]);
        String title = split[2];
        String melody = changeMelody(split[3]);

        return Music.of(endTime - startTime, title, melody);
    }

    private int getTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]) * 60;
        int min = Integer.parseInt(split[1]);
        return hour + min;
    }

    static class Music {
        int runningTime;
        String title;
        String melody;
        String playMelody;

        static Music of(int runningTime, String title, String melody) {
            return new Music(runningTime, title, melody);
        }

        private Music(int runningTime, String title, String melody) {
            this.runningTime = runningTime;
            this.title = title;
            this.melody = melody;
            playMelody = getPlayMelody(runningTime, melody);
        }

        private String getPlayMelody(int runningTime, String melody) {
            StringBuilder tempPlayMelody = new StringBuilder();
            for (int i = 0; i < runningTime; i++) {
                int length = melody.length();
                tempPlayMelody.append(melody.charAt(i % length));
            }
            return tempPlayMelody.toString();
        }

        boolean contains(String melody) {
            return playMelody.contains(melody);
        }
    }

    public static void main(String[] args) {
//        System.out.println("result : " + new TheSongJustNow().solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));  // WORLD
        System.out.println("result : " + new TheSongJustNow().solution("A#", new String[]{"13:00,13:02,HAPPY,B#A#"}));  // HAPPY
//        System.out.println("result : " +new TheSongJustNow().solution("ABC", new String[]{"00:00,00:06,HI,ABC#ABC"}));
//        System.out.println("result : " +new TheSongJustNow().solution("ABC", new String[]{"00:00,00:06,HI,ABC#ABC"}));

    }
}
