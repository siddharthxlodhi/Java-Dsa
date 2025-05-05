package Pattern;

public class Square {
    public static void printSquare(int size) {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }

    public static void reverse(int n) {
        while (n > 0) {
            int last = n % 10;
            System.out.print(last);
            n = n / 10;
        }
    }

    public static void reverseN(int n) {
        int reverse=0;
        while (n > 0) {
            int last = n % 10;
            reverse = reverse *10;
            reverse=reverse+last;
            n = n / 10;
        }
        System.out.println(reverse);
    }
    }
