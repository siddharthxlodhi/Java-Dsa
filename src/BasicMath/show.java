package BasicMath;

import java.util.ArrayList;
import java.util.List;

public class show {

    public static void printDigits(int n) {
        int num = n;
        while (num != 0) {
            System.out.println("Digit:" + n % 10);
            num = n /= 10;
        }
    }

    public static int countDigits(int n) {
        int num = n;
        int count = 0;
        while (num != 0) {

            num = num / 10;
            count++;

        }
        return count;
    }

    public static int reverseDigits(int n) {
        int reverse = 0;
        while (n != 0) {
            int temp = n % 10;
            if (reverse > Integer.MAX_VALUE / 10 || reverse < Integer.MIN_VALUE / 10) {
                return 0;
            }
            reverse = (reverse * 10) + temp;

            n = n / 10;
        }

        return reverse;

    }

    public static boolean isPalindrome(int n) {
        if (n < 0)
            return false;
        int num = n;
        int reverse = 0;
        while (n != 0) {
            int temp = n % 10;
            reverse = (reverse * 10) + temp;

            n = n / 10;
        }
        return num == reverse;

    }

    public static boolean isArmstrong(int n) {
        int original = n;
        int temp = n;
        double sum = 0;
        int digit = 0;
        while (temp != 0) {
            temp = temp / 10;
            digit++;
        }
        while (n != 0) {
            int last = n % 10;
            sum = sum + Math.pow(last, digit);
            n = n / 10;
        }

        return original == sum;
    }

    public static List<Integer> printDivisors(int n) {
        List<Integer> list = new ArrayList<Integer>(n);
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        System.out.print("The divisors of " + n + " are: ");
        return list;
    }

    public static long sumDivisors(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    sum = sum + j;
                }

            }
        }
        return sum;
    }

    public static long sumDivisors1(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + (long) (n / i) * i;

        }
        return sum;
    }

    public static int gcd(int n1, int n2) {
        int common = 1;
        for (int i = 1; i <= Math.min(n1, n2); i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                common = i;
            }
        }
        return common;
    }

    public static int gcdBetter(int n1, int n2) {
        int common = 1;
        for (int i = Math.min(n1, n2); i >= 1; i--) {
            if (n1 % i == 0 && n2 % i == 0) {
                common = i;
                break;
            }
        }
        return common;
    }// TC := O(min(n1,n2))

    public static int gcdEuclidean(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) a = a % b;
            else b = b % a;
        }
        if (a == 0) return b;
        else return a;
    }

    public static int lcm(int n1, int n2) {
        int common = 0;
        for (int i = Math.min(n1, n2); i <= 100; i++) {
            if (i % n1 == 0 && i % n2 == 0) {
                common = i;
                break;
            }
        }
        return common;
    }

    public static int secondfGreatestElement(int[] arr) {
        int highest = Integer.MIN_VALUE;
        int sHigesht = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {                   // 8597135
            if (arr[i] > highest) {
                sHigesht = highest;
                highest = arr[i];
            } else if (arr[i] > sHigesht && highest != arr[i]) {
                sHigesht = arr[i];
            }
        }
        if (sHigesht == Integer.MIN_VALUE) {
            return -1;
        }
        return sHigesht;
    }

    public static boolean checkPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int sumPrime(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (checkPrime(arr[i])){
                sum=sum+arr[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(show.countDigits(10000));
    }

}
