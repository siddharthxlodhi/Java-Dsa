package pattern1;

public class Show {

    public static void one() {

        for (int i = 1; i <= 4; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void two() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }

    }

    public static void three() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void four() {
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void five() {
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void five1(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n + 1 - i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void six(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j < 2 * i; j++) {
                System.out.print("*");
            }
            for (int j = 1; j < i * 2; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void seven(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * n) - (i * 2) + 1; j++) {
                System.out.print("*");
            }
            for (int j = 1; j < i; j++) {
                System.out.print(" ");

            }
            System.out.println();
        }

    }

    public static void eight(int n) {
        six(n);
        seven(n);
    }

    public static void nine(int n) {
        int stars;
        for (int i = 1; i <= 2 * n - 1; i++) {
            stars = i;
            if (i > n) {
                stars = 2 * n - i;
            }
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void ten(int n) {
        int num;
        for (int i = 1; i <= n; i++) {
            num = 1;
            if (i % 2 == 0) {
                num = 0;
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num = 1 - num;
            }
            System.out.println();
        }
    }

    public static void eleven(int n) {
        for (int i = 1; i <= n; i++) {
            //number
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            //spACE
            for (int j = 1; j <= 2 * n - 2 * i; j++) {
                System.out.print(" ");
            }
            //NUMBER
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void twelve(int n) {
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num);
                num++;
            }
            System.out.println();
        }
    }

    public static void thirteen(int n) {
        for (int i = 1; i <= n; i++) {
            for (char ch = 'A'; ch < 'A' + i; ch++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    public static void fourteen(int n) {
        for (int i = n; i >= 1; i--) {
            for (char ch = 'A'; ch < 'A' + i; ch++) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    public static void fifteen(int n) {
        char ch = 'A';
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(ch);
            }
            ch++;
            System.out.println();
        }
    }

    public static void sixteen(int n) {
        for (int i = 1; i <= n; i++) {
            //space
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            char ch = 'A';
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print(ch);
                if (j < i) {
                    ch++;
                } else {
                    ch--;
                }
            }
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public static void eighteen(int n) {
        int dec = n * 2 - 2;
        //Character
        for (int i = 0; i < n * 2; i++) {
            if (i < n) {
                //character
                for (int j = 0; j < n - i; j++) {
                    System.out.print("* ");
                }
                //Space
                for (int j = 1; j <= i * 2; j++) {
                    System.out.print(" ");
                }
                for (int j = i; j < n; j++) {
                    System.out.print("* ");
                }
                System.out.println();
            } else {
                for (int j = 0; j <= i - n; j++) {
                    System.out.print("* ");
                }
                for (int j = 1; j <= dec; j++) {
                    System.out.print(" ");
                }
                dec = dec - 2;
                for (int j = 0; j <= i - n; j++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        }


    }

    public static void nineteen(int n) {
        int space1 = n * 2 - 2;
        int dec2 = n - 1;
        int space2 = 2;
        for (int i = 0; i < n * 2 - 1; i++) {
            //upper
            if (i < n) {
                //star
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                //Space
                for (int j = 0; j < space1; j++) {
                    System.out.print(" ");
                }
                space1 = space1 - 2;
                //star
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            } else {
                //star
                for (int j = 0; j < dec2; j++) {
                    System.out.print("*");
                }
                //space
                for (int j = 0; j < space2; j++) {
                    System.out.print(" ");
                }
                space2 = space2 + 2;
                //star
                for (int j = 0; j < dec2; j++) {
                    System.out.print("*");
                }
                dec2 = dec2 - 1; //for stars

                System.out.println();
            }


        }
    }
    public static void nineteen1(int n) {
        int spaces = 2*n-2;

        // Outer loop for printing row.
        for(int i = 1;i<=2*n-1;i++){

            // stars for first half
            int stars = i;

            // stars for the second half.
            if(i>n) stars = 2*n - i;

            //for printing the stars
            for(int j=1;j<=stars;j++){
                System.out.print("*");
            }

            //for printing the spaces
            for(int j = 1;j<=spaces;j++){
                System.out.print(" ");
            }

            //for printing the stars
            for(int j = 1;j<=stars;j++){
                System.out.print("*");
            }

            // As soon as the stars for each iteration are printed, we move to the
            // next row and give a line break otherwise all stars
            // would get printed in 1 line.
            System.out.println();
            if(i<n) spaces -=2;
            else spaces +=2;
        }
    }

    public static void twenty(int n) {
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {
                if(i==1 || j==1||i==n||j==n) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();


        }

    }

    public static void twenty1(int n) {
        for (int i = 0; i <2*n-1 ; i++) {
            for (int j = 0; j<2*n-1; j++) {
                int top=j;
                int left=i;
                int bottom=(2*n-2)-i;
                int right=(2*n-2)-j;
                int min = Integer.min(Integer.min(top, bottom), Integer.min(left, right));
                System.out.print(n-min);
            }
            System.out.println();

        }
    }


    public static void main(String[] args) {
        twenty(5);

    }
}
