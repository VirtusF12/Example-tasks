package Task2;

public class Main {

    /*
        Реализуйте 2 метода для преобразования строки:
        - один - в целочисленное значение элементарного типа (int)
        - другой метод - в число с плавающей запятой (double), так же элементарного типа.
        Необходимо реализовать собственные методы, без использования аналогичных методов из Java
        (если знаете аналогичные Java методы, пожалуйста, перечислите их).
     */

    public static int convertToInt(String str) {

        return parseInt(str, 10);
    }

    private static int parseInt(String s, int radix)
            throws NumberFormatException
    {

        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;

        if (len > 0) {

            char firstChar = s.charAt(0);

            if (firstChar < '0') { // Возможные ведущие "+" или "-"

                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    throw forInputString(s, radix);
                }

                if (len == 1) { // Не может быть одинокого "+" или "-"
                    throw forInputString(s, radix);
                }

                i++;
            }

            int multmin = limit / radix;
            int result = 0;

            while (i < len) {

                int digit = Character.digit(s.charAt(i++), radix);

                if (digit < 0 || result < multmin) {
                    throw forInputString(s, radix);
                }

                result *= radix;

                if (result < limit + digit) {
                    throw forInputString(s, radix);
                }
                result -= digit;
            }
            return negative ? result : -result;

        } else {

            throw forInputString(s, radix);
        }
    }

    private static NumberFormatException forInputString(String s, int radix) {
        return new NumberFormatException("For input string: \"" + s + "\"" +
                (radix == 10 ?
                        "" :
                        " under radix " + radix));
    }

    public static double convertToDouble(String str){

        return Double.parseDouble(str.replaceAll(",","").trim().replaceAll(" ", ""));
    }

    public static void main(String[] args) {

        String strNumberInt = "1223";
        String strNumberDouble = "4 55,63. 0 ";

        System.out.println(convertToInt(strNumberInt));
        System.out.println(convertToDouble(strNumberDouble));
    }

}
