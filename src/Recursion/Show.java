package Recursion;

import java.util.ArrayList;

public class Show {

    public static void printNameNtime(String name, int n) {
        if (n == 0) {
            return;
        }
        System.out.println(name);
        printNameNtime(name, n - 1);
    }

    public static void printNtime(int i, int n) {
        if (i > n) {
            return;
        }
        System.out.println(i);
        printNtime(i + 1, n);
    }


    public static void printNtoOne(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(n);
        printNtoOne(n - 1);
    }
    //By backtracking

    public static void print1toN(int i, int n) {
        if (i < 1) {
            return;
        }
        print1toN(i - 1, n);
        System.out.println(i);
    }

    public static void printNto1(int i, int n) {
        if (i > n) {
            return;
        }
        print1toN(i + 1, n);
        System.out.println(i);
    }

    public long sumFirstN(long n) {
        if (n == 0) return 0;


        return n * n * n + sumFirstN(n - 1);
    }

    public static long factorial(long n) {
        if (n >= 1)
            return n * factorial(n - 1);
        else
            return 1;
    }

    public static ArrayList<Long> factorial2(long n) {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (factorial(i) > n) {
                break;
            }
            list.add(factorial(i));
        }
        list.forEach(e -> System.out.print(e + " "));
        return list;
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;

    }

    public static int sumNumbers(int n) {
        if (n == 0) return 0;                //functional way
        return n + sumNumbers(n - 1);
    }

    public static int sumNumbers2(int n, int sum) {
        if (n == 0) return 0;
        return n + sumNumbers2(n - 1, sum);

    }

    //    public static int[] reverseAr(int[] ar) {
//        int i = 0;
//        int j = ar.length - 1;
//
//        while (i < j) {
//            int temp = ar[i];
//            ar[i] = ar[j];
//            ar[j] = temp;
//            i++;
//            j--;
//        }
//        return ar;
//
//    }
    public static int[] reverseAr(int[] ar, int low, int high) {
        if (low >= high) {
            return ar;
        }
        int temp = ar[low];
        ar[low] = ar[high];
        ar[high] = temp;
        return reverseAr(ar, low + 1, high - 1);
    }

    //reversing using one pointer
    public static int[] reverseAr1(int[] ar, int low) {
        if (low >= ar.length / 2) {
            return ar;
        }
        int temp = ar[low];
        ar[low] = ar[ar.length - low - 1];
        ar[ar.length - low - 1] = temp;
        return reverseAr1(ar, low + 1);
    }

    public static boolean isPalindrome(String orgStr, int i) {
        if (i >= orgStr.length() / 2) return true;
        if (orgStr.charAt(i) != orgStr.charAt(orgStr.length() - 1 - i)) {
            return false;
        }
        return isPalindrome(orgStr, i + 1);

    }

    public static boolean isPalindrome1(String orgStr, int start, int last) {
        if (start >= last) return true;
        if (!Character.isLetterOrDigit(orgStr.charAt(start))) {
            start++;
        } else if (!Character.isLetterOrDigit(orgStr.charAt(last))) {
            last--;
        } else if (Character.toLowerCase(orgStr.charAt(start)) != Character.toLowerCase(orgStr.charAt(last))) {
            return false;
        }
        return isPalindrome1(orgStr, start, last);
    }


    public static void Fibonacci(int n) {
        int[] ar = new int[n + 1];
        ar[0] = 0;
        ar[1] = 1;
        for (int i = 2; i <= n; i++) {
            ar[i] = ar[i - 1] + ar[i - 2];

        }
        System.out.println(ar[n]);
    }

    public static int Fibonacci1(int n) {
        if (n <= 1) {
            return n;
        }
        int last = Fibonacci1(n - 1);
        int Slast = Fibonacci1(n - 2);
        return last + Slast;

    }

    static int occ = 0;

    public static void occourrence(int[] ar, int i, int key) {
        if (i == ar.length) {
            System.out.println(occ);
            return;
        }
        if (ar[i] == key) {
            occ = occ + 1;
        }
        occourrence(ar, i + 1, key);
    }


    public static int StrLength(String str) {
        // if we reach at the end of the string
        if (str.isEmpty())
            return 0;
        else
            return StrLength(str.substring(1)) + 1;

    }

    public static void StrReverse(String s, int index) {
        if (index < 0) {
            return;
        }
        System.out.print(s.charAt(index));
        StrReverse(s, index - 1);


    }

    public static void reverse(String s) {
        if (s.isEmpty()) {
            return;
        }
        System.out.println(s.charAt(s.length() - 1));
        reverse(s.substring(0, s.length() - 1));

    }




    public static void main(String[] args) {
        reverse("baby");
    }
}
