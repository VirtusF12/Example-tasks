package Task3;

public class Main {

    /*
        Напишите простую программу, которая выводит на экран числа от 1 до 100 включительно.
        При этом вместо чисел, кратных трем, программа должна выводить слово Fizz, а вместо чисел, кратных пяти — слово Buzz.
        Если число кратно и трем, и пяти, то программа должна выводить слово FizzBuzz.
     */

    public static void main(String[] args) {

        for(int i = 1; i <= 100; i++) {

            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
