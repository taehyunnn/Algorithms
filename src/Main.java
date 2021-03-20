import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"5.0","2.1","3.3","4.5","1.9"};

        Arrays.sort(strings);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}