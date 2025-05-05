package BinarySearch;

import java.util.Arrays;
import java.util.LinkedList;


public class Medium {

    //Binary search for answers(can only be applied when asked to find min or max)


    //Whenever u know the range in which the answer can be present
    //(the range determines the T.C)
    //And asked to find min max use B.S.


    //Ques -Finding the floor of the sqrt(n)

    public static int floorSqrRoot(int n) {
        int low = 1;
        int high = n;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if ((mid * mid) <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }


    //we have to find nth root of m
    public static int NthRoot(int m, int n) {
        int low = 1;
        int high = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midN = func(mid, n, m);
            if (midN == 1) {
                return mid;
            } else if (midN == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    //This function multiplies mid to n times
    //if it crosses m returns 2 ,if it equals m return 1, if < m returns 0
    //binary exponentiation can do it better
    public static int func(int mid, int n, int m) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) {
                return 2;
            }
        }
        if (ans == m) return 1;
        else return 0;
    }


//Ques-//we have to Return the minimum integer k(banana per hour) such that she can eat all the bananas within h hours.

    //By linear search
    //T.C :- O( max(piles) * n)
    public static int minEatingSpeed(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();
        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int get = reqTime(piles, i, h);
            if (get == 0) {     //minimum integer k(banana per hour) such that she can eat all the bananas within h hours.
                return i;
            }
        }
        return -1;
    }

    //By binary search
    //T.c: - O( log( max(piles) * n);  n-- for iterating piles
    public static int minEatingSpeedBS(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();
        int ans = 0;
        int low = 1;
        int high = max;     //(max range)max bananas he can koko can eat in one hour

        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println(mid);
            int reqTime = reqTime(piles, mid, h);
            if (reqTime == 0) {       //if the hour is <= h
                ans = mid;            //One of my possible answer
                high = mid - 1;       //Going to left to get smallest integer K
            } else {                  //if the hour is > h
                low = mid + 1;        //going right to get ans
            }
        }

        return ans;
    }

    //This Fn show that if we take (mid=banana per hours) ,then the number of hours taken is either <= h or it is greater than h
    private static int reqTime(int[] piles, int mid, int h) {

        int hour = 0;
        for (int i = 0; i < piles.length; i++) {
            int temp = (int) Math.ceil((double) piles[i] / mid); //hours it take to eat piles[i] bananas
            hour += temp;
            if (hour > h) return 2; //if the hour taken > h
        }
        return 0; //if the hour taken <= h

    }


    //Ques-Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
    //m= to make m bouquets
    //k == no. of adjacent flowers req to make bouquet
    //n=length of bloomDay =total flowers
    //piles[i] - the ith flower will bloom on piles[i] day

    //By Linear Search
    //T.C:- O(n) + O( (max-min)+1 * n)
    public static int minDays(int[] bloomDay, int m, int k) {
        if ((m * k) > bloomDay.length) {   //Impossible case if flowers < given
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int j : bloomDay) {
            if (j > max) max = j;
            if (j < min) min = j;
        }

        //we start from minimum day to calculate the bouquets formed on that day
        for (int i = min; i <= max; i++) {
            int num = calcBouquets(bloomDay, i, k);   //calculates the number of bouquets can be formed on ith day
            if (num >= m)
                return i;                   //if the bouquets formed on that day is >= m(given) ,we return that day
        }
        return -1;
    }

    //function that calculates how many bouquets can be formed on the given day
    private static int calcBouquets(int[] bloomDay, int day, int k) {
        int count = 0;   //it keeps the adjacent flower counts
        int num = 0;  //Number of bouquets

        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day)
                count++;                   //if the  (bloomDay[i] day <= given day) the ith flower is bloomed , so count++;
            else {                              //if the  (bloomDay[i] day > given day) the ith flower is not bloomed , so we  calculate the bouquet formed from the given counted flower
                num += (count / k);
                count = 0;                 //and again make the counted flower to 0, to count another adjacent flowers
            }
        }
        num += (count / k);
        return num;
    }

    public static int minDaysBS(int[] bloomDay, int m, int k) {
        if ((m * k) > bloomDay.length) {   //Impossible case(this is only case other than that all have a minimum day)
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int j : bloomDay) {
            if (j > max) max = j;
            if (j < min) min = j;
        }

        int ans = -1;

        int low = min;
        int high = max;
        while (low <= high) {
            int mid = (low + high) / 2;
            int num = calcBouquets(bloomDay, mid, k);
            if (num >= m) {                            //  if the number  bouquets formed on the day is >= m(given) ,we return that day(as it is the minimum day)
                ans = mid;                              //can be a answer
                high = mid - 1;                         // move to left for more minimum day
            } else {
                low = mid + 1;                          //if the bouquet formed is less than given, move to right for more
            }

        }
        return ans;
    }


    //By Linear search
    public static int smallestDivisor(int[] nums, int threshold) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int j : nums) {
            if (j > max) max = j;
            if (j < min) min = j;
        }
        for (int i = min; i <= max; i++) {
            int result = calcResult(nums, i);
            if (result <= threshold) {
                return i;
            }
        }
        return -1;
    }

    private static int calcResult(int[] nums, int i) {
        int result = 0;

        for (int j = 0; j < nums.length; j++) {
            result += (int) Math.ceil((double) nums[j] / i);
        }
        return result;
    }

    public static int smallestDivisorBS(int[] nums, int threshold) {
        int max = Integer.MIN_VALUE;

        for (int j : nums) {
            if (j > max) max = j;
        }

        int low = 1;
        int high = max;           //Because  it can divide all the number and get 1 (more than this number will do the same)
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = calcResult(nums, mid);
            if (result <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }


        }
        return ans;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i : weights) {
            if (i > max) max = i;
            sum += i;
        }
        int ans = 0;
        int low = max;
        int high = sum;
        while (low <= high) {
            int mid = (low + high) / 2;  //weight capacity of ship
            int daysNeeded = days(weights, mid);  //This will find the days required to ship packages on given ship weight capacity
            if (daysNeeded <= days) {
                ans = mid;         //Can be my answer
                high = mid - 1;    //to find minimum ship weight  capacity
            } else {
                low = mid + 1;      //to reduce days
            }
        }
        return ans;
    }

    //This function gives the days required to ship all the packages ,on given a specific weight capacity of ship
    private static int days(int[] weights, int mid) {
        int count = 0;   //No of days
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > mid) {
                count++;
                sum = weights[i];
            }

        }
        if (sum <= mid) count++;
        return count;
    }


    //Worst Case: O(n + k)
    //n-- length of array
    //k--kth missing
    //My approach
    public static int findKthPositive(int[] arr, int k) {
        int ans = -1;
        int misCount = 0;
        int i = 1;
        int arrI = 0;
        while (misCount < k) {
            if (arrI < arr.length && i == arr[arrI]) {
                i++;
                arrI++;
            } else {
                misCount++;
                ans = i;
                i++;
            }
        }
        return ans;
    }

    //O(n)
    public static int findKthPositive2(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= k) {  //It means the current number in the array is not missing and lies within the first k positive integers. Therefore, we "shift" k forward by incrementing it (k++).
                k++;
            } else {   //Otherwise (if vec[i] > k): It means that k is missing from the sequence of numbers represented by the array. In this case, the loop breaks, and we return the current value of k.
                break;
            }
        }
        return k;
    }

    //(log n)
    //Do dry runs
    public static int findKthPositiveBS(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] - (mid + 1) < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

//        int times = k - (arr[high] - (high + 1));
//        int ans = arr[high]+times;
//        return ans;
//        solving this will make the below formula(otherwise it will fail in {4,5,6,7,8} kth=3
        return k + high + 1;   //can also return k+low  as low=high+1
    }


    /*
    Given
    stalls[] -  positions of stalls ,there are n(stalls.length stalls)
    k - No. of aggressive  cows
    To find:- Minimum distance between two cows that is maximum possible
    Your task is to assign stalls to k cows such that the minimum distance between any two of them is the maximum possible.
     */
    //Largest(min) --Minimum distance between two cows that is maximum possible
    //T.C : -  O( N*log N+ N*lOG(max-min))
    //Nlogn - for sorting
    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        if (k == 2) {             //If there are two cows the minimum distance b/w them that is maximum possible
            return stalls[stalls.length - 1] - stalls[0];   //After sort
        }

        int ans = -1;

        int low = 1;
        int high = stalls[stalls.length - 1] - stalls[0]; //Extreme case     //maximum(minimum) distance that is possible
        while (low <= high) {
            int mid = (low + high) / 2;  //we will check if this distance(mid) can be possible (means two cows can be placed such that minimum distance will be (>=mid)  )
            if (islargestPossibleMin(stalls, mid, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    //This function shows whether the given minimum distance(or greater) is possible
    //dist =given distance
    public static boolean islargestPossibleMin(int[] stalls, int dist, int k) {
        int lastCowPos = 0;                           //Placing first cow at first stall
        int cowsPlaced = 1;                                   //1 cow is placed at first position
        for (int j = 1; j < stalls.length; j++) {
            if (stalls[j] - stalls[lastCowPos] >= j) {   //This check if the distance  between the cows is not less than mid(given)  ( distance more than that can be considered)
                cowsPlaced++;                            //place the next cow by increment
                lastCowPos = j;                          //Update placed  cows position
                if (cowsPlaced == k) return true;         //if all the cows placed (return true)
            }
        }
        return false;                //if all cows are not placed (return false) that means given distance cant be possible
    }


    //min(max)
    //all the books should be allocated
    public static int findPages(int[] arr, int k) {
        if (k > arr.length) return -1;

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        if (k == 1) {
            return sum;
        } else if (k == arr.length) {
            return max;
        }

        int low = max;
        int high = sum;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println(mid);
            if (isMinimumPossibleMaximumPages(arr, mid, k)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }


    private static boolean isMinimumPossibleMaximumPages(int[] arr, int mid, int k) {
        int stud = 1;
        int pages = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > mid) return false;        //important edge  case
            if (pages + arr[i] <= mid) {
                pages += arr[i];
            } else {
                stud++;
                pages = arr[i];
            }
        }
        return stud <= k;
    }

    //Same to Book Allocation Problem
    public int splitArray(int[] nums, int k) {
        if (k > nums.length) return -1;

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if (k == 1) {
            return sum;
        } else if (k == nums.length) {
            return max;
        }

        int low = max;
        int high = sum;


        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println(mid);
            if (isMinimumPossibleMaximumSubArray(nums, mid, k)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;


    }

    private static boolean isMinimumPossibleMaximumSubArray(int[] arr, int mid, int k) {
        int stud = 1;
        int pages = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > mid) return false;        //important edge  case
            if (pages + arr[i] <= mid) {
                pages += arr[i];
            } else {
                stud++;
                pages = arr[i];
            }
        }
        return stud <= k;
    }

    //MIN(MAX)
    public int minTime(int[] arr, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }
        if (k == 1) {
            return sum;
        }
        int ans = -1;
        int low = max;
        int high = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isMinimumPossibleMaximumPages(arr, mid, k)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    //T.C:- O(m+n)
    //S.c:- O(m+n)
    public static double findMedianSortedArraysBrute(int[] nums1, int[] nums2) {
        int[] arr1 = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int n = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                arr1[n] = nums1[i];
                n++;
                i++;
            } else {
                arr1[n] = nums2[j];
                n++;
                j++;
            }
        }

        while (i < nums1.length) {
            arr1[n] = nums1[i];
            n++;
            i++;
        }
        while (j < nums2.length) {
            arr1[n] = nums2[j];
            n++;
            j++;
        }
        System.out.println(Arrays.toString(arr1));
        int mid = (arr1.length - 1) / 2;
        if (arr1.length % 2 == 0) {
            return (double) (arr1[mid] + arr1[mid + 1]) / 2;
        } else {
            return arr1[mid];
        }

    }

    //In this we we eliminate the third array used in Brute approach
    //we are using the concept of merge
    //T.C:- O(m+n)
    //S.c:- O(1)
    public static double findMedianSortedArraysBetter(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;     //length of merged array will be
        int ind1 = ((nums1.length + nums2.length) - 1) / 2;   //in case of even or odd this will be the first element
        int i = 0;
        int j = 0;
        int el1 = -1;          //we'll find mid element 1
        int el2 = -1;          //we'll find mid element 2
        int count = -1;
        while (i < nums1.length && j < nums2.length) {         // iterate over both array and when the count==ind1 we got the el1 and if count==ind1+1 we got el2
            count++;                                           //every time we find a smaller element
            if (nums1[i] <= nums2[j]) {
                if (count == ind1) {
                    el1 = nums1[i];
                } else if (count == (ind1 + 1)) {
                    el2 = nums1[i];
                    break;
                }
                i++;
            } else {
                if (count == ind1) {
                    el1 = nums2[j];
                } else if (count == (ind1 + 1)) {
                    el2 = nums2[j];
                    break;
                }
                j++;
            }
        }

        while (i < nums1.length) {
            count++;
            if (count == ind1) {
                el1 = nums1[i];
            } else if (count == (ind1 + 1)) {
                el2 = nums1[i];
                break;
            }
            i++;
        }

        while (j < nums2.length) {
            count++;
            if (count == ind1) {
                el1 = nums2[j];
            } else if (count == (ind1 + 1)) {
                el2 = nums2[j];
                break;
            }
            j++;
        }
        System.out.println(el1);
        System.out.println(el2);

        if (n % 2 == 0) {                       //if length of merged array is even we return(average of both mid elements)
            return (double) (el1 + el2) / 2;
        } else {
            return el1;                          //if length of merged array is odd we return(el1)
        }
    }


    //Intuition points
    // the BS will be based on the symmetry(where we will determine how many els from arr1 and how many els from arr 2 to get a symmetry)
    //how to determine if its a valid or invalid symmetry( l1<r2 and l2 < r1)
    // median=( ( max(l1,l2) + min(r1,r2) ) /2)
//    public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
//        int n = (nums1.length + nums2.length) / 2;
//        int low = 0;                                      //we can take 0 elements
//        int high = Math.min(nums1.length, nums2.length);  //or we can take upto
//        int median = -1;
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            int l1;
//            if (mid == 0) {
//                l1 = Integer.MIN_VALUE;
//            } else {
//                l1 = nums1[mid - 1];
//            }
//
//            int l2 = nums2[(n - mid) - 1];
//            int r2 = nums2[n - mid];
//            int r1;
//            if (mid == n) {
//                r1 = Integer.MAX_VALUE;
//            } else {
//                r1 = nums1[mid];
//            }
//            if (l1 <= r2 && l2 <= r1) {
//                median = ((Math.max(l1, l2) + Math.min(r1, r2)) / 2);
//                return median;
//            } else if (l1 > r2) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;
//            }
//
//        }
//        return median;
//    }

    public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        if (n1 > n2) return findMedianSortedArraysBS(nums2, nums1);
        int low = 0;
        int high = n1;

        int left = (n1 + n2 + 1) / 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;
            if (mid > 0) {
                l1 = nums1[mid - 1];
            }
            if (mid < left) {
                l2 = nums2[(left - mid) - 1];
            }
            if (mid < n1) {
                r1 = nums1[mid];
            }
            if ((left - mid) < n2) {
                r2 = nums2[left - mid];
            }

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 0) return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int kthElement(int a[], int b[], int k) {
        int kIndex = k - 1;
        int i = 0;
        int j = 0;
        int el1 = -1;          //we;ll find mid element 1
        int count = -1;
        while (i < a.length && j < b.length) {         // iterate over both array and when the count==ind1 we got the el1 and if count==ind1+1 we got el2
            count++;
            if (a[i] <= b[j]) {
                if (count == kIndex) {
                    el1 = a[i];
                }
                i++;
            } else {
                if (count == kIndex) {
                    el1 = b[j];
                }
                j++;
            }
        }

        while (i < a.length) {
            count++;
            if (count == kIndex) {
                el1 = a[i];
            }
            i++;
        }

        while (j < b.length) {
            count++;
            if (count == kIndex) {
                el1 = b[j];
            }
            j++;
        }

        return el1;
    }


    public static void main(String[] args) {

        int[] ar1 = {7, 12, 14, 15};
        int[] ar2 = {1, 2, 3, 4, 9, 11};
        System.out.println(findMedianSortedArraysBS(ar1, ar2));
    }
}
