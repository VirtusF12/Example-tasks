package Task1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.stream.*;

public class Main {

    /*
        Реализуйте метод для замены заданного символа в строке (простой аналог метода replace класса String в Java).
        На вход метод получает строку и символы, возвращает строку.
     */

    private static String getStringChar(String currentStr, char oldChar, char newChar) {

        if (currentStr.equals(String.valueOf(oldChar)))
            return String.valueOf(newChar);
        else
            return currentStr;
    }

    public static @NotNull
    String replaceStreamAPI(String str, char oldChar, char newChar) {

        if (str != null) {
            return Arrays.asList(str.split("")).stream().map(s -> s = (getStringChar(s, oldChar, newChar))).collect(Collectors.joining());
        } else {
            return new NullPointerException().toString();
        }
    }

    public static String replaceBase(String str, char oldChar, char newChar) {

        if (str != null) {

            StringBuilder sb = new StringBuilder();
            for(char s : str.toCharArray()) {
                if (s == oldChar)
                    sb.append(newChar);
                else
                    sb.append(s);
            }
            return sb.toString();
        } else {
            return new NullPointerException().toString();
        }
    }

    public static void main(String[] args) {

        String str = "privet1111kak11dela11";
        // str = null;
        System.out.println("base = " + str.replace('1', ' '));
        System.out.println("example 1 = " + replaceBase(str, '1', ' '));
        System.out.println("example 2  = " + replaceStreamAPI(str,'1', ' '));
    }
}
