package Array;

import java.util.*;

public class Easy {

    //1) brute force
    //2)Better
    //3) Optimal


    // O(n)
    public static int secondHighest(int[] arr) {
        int Highest = Integer.MIN_VALUE;
        int sHighest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > Highest) {
                sHighest = Highest;
                Highest = arr[i];
            } else if (arr[i] > sHighest && arr[i] != Highest) {
                sHighest = arr[i];
            }

        }
        if (sHighest == Integer.MIN_VALUE)
            return -1;
        return sHighest;
    }

    public static boolean checkSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (!(arr[i] <= arr[i + 1])) {
                return false;
            }
        }
        return true;

    }

    //N log N +N
    public static int removeDuplicates(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int i = 0;
        for (int element : set) {
            arr[i] = element;
            i++;
        }
        return set.size();
    }

    //T.C-O(n)
    public static int removeDuplicatesOptimal(int[] arr) {
        int i = 0;//Two pointer approach
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    //Left rotate an array by 1 place {1,2,3,4,5}->{2,3,4,5,1}

    public static int[] leftRotateBy1(int[] arr) {
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = temp;
        return arr;
    }

    //1) Brute force(to understand look upper one)
    public static int[] leftRotateByK(int[] arr, int k) {
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {     //adding elements upto k places to temp array = till (k-1) index
            temp[i] = arr[i];
        }
        for (int i = k; i < arr.length; i++) {   //shifting  elements
            arr[i - k] = arr[i];
        }
        for (int i = arr.length - k; i < arr.length; i++) {   //adding the temp elements back
            arr[i] = temp[i - (arr.length - k)];
        }
//      or->  int j=0;
//        for (int i = arr.length - k; i < arr.length; i++) {   //adding the temp elements back
//            arr[i] = temp[j];
//        j++;
//        }
        return arr;
    }

    public static void leftRotateByKOptimal(int[] arr, int k) {

        k = k % arr.length;
        if (k == 0)
            return;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        System.out.println("Rotated array:" + Arrays.toString(arr));
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

    }

    public static void rightRotateByKOptimal(int[] arr, int k) {
        k = k % arr.length;
        if (k == 0) {
            return;
        }
        reverse(arr, 0, (arr.length - k) - 1);
        reverse(arr, arr.length - k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);


        //this can also be done(here first the whole array is reversed)
//        reverse(nums,0,arr.length-1);
//        reverse(nums,0,k-1);
//        reverse(nums,k,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //    T.C= O(n)+O(x)+o(n-x)=O(2n)
    //S.c- O(n)-> worst case
    public static int[] moveZeroes(int[] arr) {
        List<Integer> temp = new ArrayList<>();
        for (int element : arr) {
            if (element != 0) {
                temp.add(element);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i);
        }
        for (int i = temp.size(); i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

    public static void moveZeroesOptimal(int[] arr) {                 //By two pointer approach
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        if (j == -1) {
            System.out.println("No zeros");
            return;
        }
        for (int i = j + 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        System.out.println("Resultant arr:" + Arrays.toString(arr));
    }

    public static void reverseArray(int arr[]) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        System.out.println(Arrays.toString(arr));
    }

    //Bruteforce
    public static ArrayList<Integer> findUnion(int[] a, int[] b) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            set.add(b[i]);
        }
        return new ArrayList<>(set);
    }


    //T.C- O(n1+n2)
    //S.C-O(n1+n2)
    public static ArrayList<Integer> findUnionOptimal(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                if (list.isEmpty() || list.getLast() != a[i]) {
                    list.add(a[i]);
                }
                i++;
            } else {
                if (list.isEmpty() || list.getLast() != a[j]) {
                    list.add(b[j]);
                }
                j++;
            }
        }
        while (i < a.length) {
            if (list.isEmpty() || list.getLast() != a[i]) {
                list.add(a[i]);
            }
            i++;
        }
        while (j < b.length) {
            if (list.isEmpty() || list.getLast() != b[i]) {
                list.add(b[i]);
            }
            j++;
        }

        return list;
    }

    //T.C=O(n^2)
    public static ArrayList<Integer> findIntersection(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] vis = new int[b.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[j] > a[i]) {
                    break;
                }
                if (a[i] == b[j] && vis[j] == 0) {
                    list.add(a[i]);
                    vis[j] = 1;
                    break;
                }

            }
        }
        return list;
    }

    //By two pointer
    //T.C=O(n1+n2)
    public static ArrayList<Integer> findIntersectionOptimal(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                list.add(a[i]);
                i++;
                j++;
            } else if (a[i] > b[j]) {
                j++;
            } else if (a[i] < b[j]) {
                i++;
            }
        }
        return list;
    }


    //Given an array nums containing n distinct numbers in the range [0, n],
// return the only number in the range that is missing from the array.
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = (n * (n + 1)) / 2;
        int sum = 0;
        for (int i : nums) {
            sum = sum + i;
        }
        return expectedSum - sum;


    }


    //Optimal
    public static int findMaxConsecutiveOnes(int[] nums) {
        int[] oneCount = new int[nums.length];
        int j = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                if (i == nums.length - 1) {
                    oneCount[j] = count;
                    j++;
                }
            } else {
                oneCount[j] = count;
                j++;
                count = 0;
            }
        }
        int max = oneCount[0];
        for (int i = 0; i < j; i++) {
            if (oneCount[i] > max) {
                max = oneCount[i];
            }
        }
        return max;
    }

    public static int findMaxConsecutiveOnes2(int[] nums) {
        int max1 = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                count++;
                max1 = Math.max(max1, count);
            } else {
                count = 0;
            }
        }
        return max1;
    }


    //BruteForce Take one each time and find its count by iterating the array if its count is 1 return the element
//Better(for positives)
    public int singleNumber(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int[] hash = new int[max + 1];
        for (int num : nums) {
            hash[num]++;
        }
        int one = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 1) {
                one = i;
            }
        }

        return one;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes2(arr));

    }

}
