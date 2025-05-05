package pattern1;

public class Show1 {


    public static void one(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void two(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void three(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void four(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void five(int n) {
        for (int i = n; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void six(int n) {
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void seven(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {              //j<=n-i
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {            //2*i-1
                System.out.print("*");
            }
            for (int j = 1; j <= n - i; j++) {              //j<=n-i
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public static void eight(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2 * n) - (2 * i - 1); j++) {
                System.out.print("*");
            }
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void nine(int n) {
        seven(n);
        eight(n);
    }

    public static void ten(int n) {
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int j = n - 1; j >= 1; j--) {
            for (int i = 1; i <= j; i++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }

    public static void ten1(int n) {
        int stars;
        for (int i = 1; i < (n * 2); i++) {
            stars = i;
            if (i > n) {
                stars = n * 2 - i;
            }
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void eleven(int n) {
        for (int i = 1; i <= n; i++) {
            int num = 1;
            if (i % 2 == 0) {
                num = 0;
            }

            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num = 1 - num;  //logic
            }
            System.out.println();
        }
    }

    public static void tweleve(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }

            for (int j = 1; j <= (2 * n) - (2 * i); j++) {
                System.out.print(" ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void thirteen(int n) {
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num = num + 1;
            }
            System.out.println();
        }
    }

    public static void fourteen(int n) {
        for (int i = 1; i <= n; i++) {

            for (char ch = 'A'; ch < 'A' + i; ch++) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    public static void fifteen(int n) {
        for (int i = n; i >= 1; i--) {
            for (char ch = 'A'; ch < 'A' + i; ch++) {  //this works only in comparison
                System.out.print(ch);
            }
            System.out.println();

        }
    }

    public static void sixteen(int n) {
        char ch = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (ch + i));
            }

            System.out.println();
        }

    }

    public static void eighteen(int n) {
        char ch = 'E';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (ch + j) + " ");
            }
            ch--;
            System.out.println();
        }
    }


    public static void nineteen(int n) {
        for (int i = 1; i <= n * 2; i++) {
            if (i <= n) {
                for (int j = n - i; j >= 0; j--) {
                    System.out.print("*");
                }
                for (int j = 0; j < (i * 2) - 2; j++) {
                    System.out.print(" ");
                }
                for (int j = n - i; j >= 0; j--) {
                    System.out.print("*");
                }
            } else {
                for (int j = 1; j <= i - n; j++) {
                    System.out.print("*");
                }
                for (int j = 1; j <= 2 * (n * 2 - i); j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= i - n; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public static void twenty(int n) {
        for (int i = 1; i <=(n * 2) - 1; i++) {
            if (i <= n) {
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
                for (int j = 1; j <= (n * 2) - (i * 2); j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = 1; j <= (n * 2) - i; j++) {
                    System.out.print("*");
                }
                for (int j = 1; j <= (2 * i) - (n * 2); j++) {
                    System.out.print(" ");

                }
                for (int j = 1; j <= (n * 2) - i; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();

        }


    }

    public static void main(String[] args) {
        twenty(3);

    }
}

