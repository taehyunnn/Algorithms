package programmers.skill_check_2;

import java.util.Arrays;

public class Phonebook {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length-1; i++) {
            for (int j = i+1; j < phone_book.length; j++) {
                if (phone_book[j].contains(phone_book[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
