package Array;

import java.util.*;

public class Hard {


    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        map.forEach((a, b) -> {

            if (b > (nums.length / 3)) {
                list.add(a);
            }
        });
        return list;
    }

    /*
    Ques-Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    Notes:-
    1)It must appear at least ⌈𝑛/3⌉(floor)+1 times in the array.
    2)if (ls.size() == 2) break; leverages this insight. Once two majority elements are found, it's guaranteed that there won't be a third one, so the loop can terminate early to save computation.
    */
    public static List<Integer> majorityElementBetter(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int mini = (n / 3) + 1;  //minimum appearance
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == mini) {
                list.add(num);
            }
            if (list.size() == 2) {
                break;
            }
        }

        return list;
    }

    public static List<Integer> majorityElementOptimal(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int mini = (nums.length / 3) + 1;
        int count1 = 0;
        int el1 = Integer.MIN_VALUE;
        int count2 = 0;
        int el2 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (count1 == 0 && nums[i] != el2) {
                count1++;
                el1 = nums[i];
            } else if (count2 == 0 && nums[i] != el1) {
                count2++;
                el2 = nums[i];
            } else if (nums[i] == el1) {
                count1++;
            } else if (nums[i] == el2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == el1) count1++;
            if (nums[i] == el2) count2++;
        }

        if (count1 >= mini) {
            list.add(el1);
        }
        if (count2 >= mini) {
            list.add(el2);
        }
        return list;
    }

//    public static List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (  nums[i] + nums[j] + nums[k] == 0) {
//                        List<Integer> list1 = new ArrayList<>();
//                        list1.add(nums[i]);
//                        list1.add(nums[j]);
//                        list1.add(nums[k]);
//                        Collections.sort(list1);
//                        if (!list.contains(list1)) {
//                            list.add(list1);
//                        }
//                    }
//                }
//            }
//
//        }
//return list;
//    }


    //T.C-- O(n^3)
    //Getting time limit exceeded
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        set.add(list);
                    }
                }
            }
        }
        return set.stream().toList();
    }

    //Here we take two pointers i and j=i+1 and we the elements in between them in hashmap to find whether the third elements exist .
    //and every time i increments we recreate the hashmap because all the possible combination that include i to make sum=0 are done.
    //T.C :- O(n^2) *  the contains of map can take O(n) sometimes
    //S.C:- O(n)+ O(no. of triplet )*3
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                int thirdElement = -(nums[i] + nums[j]);
                if (map.containsKey(thirdElement)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(thirdElement);
                    Collections.sort(list);
                    set.add(list);
                }
                map.put(nums[j], j);
            }
        }
        return set.stream().toList();
    }

    //By two pointer approach
    //in this as compare to better approach we eliminate the use of set by already sorting the array and skipping duplicate elements
    //T.C :- O(nlogn) + O(n^2)
    //S.C:-  O(no. of triplet )*2
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {       //For skipping duplicate elements after i=0 ,because previously no elements to compare
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;


            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> list1 = List.of(nums[i], nums[j], nums[k]);
                    list.add(list1);
                    k--;
                    j++;
                    while (j < k && nums[j - 1] == nums[j]) j++;  //avoiding duplicates
                    while (j < k && nums[k] == nums[k + 1]) k--;  //avoiding duplicate

                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return list;
    }


    //O(n^3)
    //O(n)+(unique quad)*2
    public static List<List<Integer>> fourSumBetter(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                Set<Long> set1 = new HashSet<>();
                for (int k = j + 1; k < nums.length; k++) {
                    // taking bigger data type
                    // to avoid integer overflow:
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long fourth = target - sum;
                    if (set1.contains(fourth)) {
                        List<Integer> list1 = new ArrayList<>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[k]);
                        list1.add((int) fourth);
                        Collections.sort(list1);
                        set.add(list1);
                    }
                    set1.add((long) nums[k]);
                }
            }
        }
        return set.stream().toList();
    }

    //T.C - O(n logn +n^3)
//S.C - O(unique quad)
    public static List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum == target) {
                        List<Integer> list1 = List.of(nums[i], nums[j], nums[k], nums[l]);
                        list.add(list1);
                        k++;
                        l--;
                        while (k < l && nums[k] == nums[k + 1]) k++;
                        while (k < l && nums[l] == nums[l - 1]) l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }

            }

        }
        return list;
    }

    // i j k
    //here if sum portionn i to k has sum (S) and some portion i to j also has sum (S) then the sum from j+1 to k is 0
    static int maxLenBrute(int arr[]) {
        int maxLength = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxLength = Math.max(i + 1, maxLength);
            }
            if (!map.containsKey(sum)) {  //if the sum calculated already exist we  do not add again beacuse the length of previous always greater(we choose leftmost)
                map.put(sum, i);
            }
            if (map.containsKey(sum)) {
                int subarrayLen = i - map.get(sum);
                maxLength = Math.max(maxLength, subarrayLen);
            }

        }
        return maxLength;
    }


//  int[][] arr = {{1, 3}, {2, 6}, {8, 9}, {9, 11}, {8, 10}, {2, 4}, {15, 18}, {16, 17}};
    //Two intervals [a, b] and [c, d] overlap if c <= b and a <= d.
    /*
   here we first sort the intervals on the basis of first element ,& if equal then based on 2nd element.
   if(i<
    1)then we check if the list of non overlapping intervals is empty or not
    -if empty that means no element to compare for skipping
    -if there's an interval in the list then we compare with the ith intervals and find the interval which not get merged in the existing interval
    2) if we got the interval we compare it with the forward intervals
    -if end(last element of ith interval)>= (first element of forward interval) we update the end with th max of end of both the intervals   , because the get covered in them
    -if not we break the loop and add the updated  interval in the result of non overlapping intervals
    */

    // T.C - O(nlogn) +O(2n)+O(n)  --> n is no. of intervals
    //S.C - O(n) + O(n) for result
    public static int[][] mergeOverlappingIntervalsBrute(int[][] intervals) {

        //Sorting the 2d  array so to easily compare intervals
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //list that contain an array of the non-overlapping intervals that cover all the intervals in the input.
        List<List<Integer>> list = new ArrayList<>();
        int k = 0;

        for (int i = 0; i < intervals.length; i++) {

            int start = intervals[i][0];
            int end = intervals[i][1];


            if (!(list.isEmpty()) && list.getLast().get(1) >= end) {
                continue;
            }

            for (int j = i + 1; j < intervals.length; j++) {
                if (end >= intervals[j][0]) {
                    end = Math.max(end, intervals[j][1]);
                } else {

                    break;
                }
            }
            list.add(List.of(start, end));
        }


        //converting the list to array
        int i = 0;
        int[][] newInterval = new int[list.indexOf(list.getLast()) + 1][2];
        for (List<Integer> l : list) {
            newInterval[i][0] = l.get(0);
            newInterval[i][1] = l.get(1);
            i++;
        }
        return newInterval;

    }


    public static int[][] mergeOverlappingIntervalsOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            if (list.isEmpty() || list.getLast().get(1) < intervals[i][0]) {
                list.add(List.of(intervals[i][0], intervals[i][1]));
            } else {
                if (list.getLast().get(1) >= intervals[i][0]) {
                    java.lang.Integer maxEnd = Math.max(list.getLast().get(1), intervals[i][1]);
                    list.set(list.indexOf(list.getLast()), (List.of(list.getLast().get(0), maxEnd)));
                }
            }
        }
        //converting the list to array
        int i = 0;
        int[][] newInterval = new int[list.indexOf(list.getLast()) + 1][2];
        for (List<Integer> l : list) {
            newInterval[i][0] = l.get(0);
            newInterval[i][1] = l.get(1);
            i++;
        }
        return newInterval;

    }

    //T.C:- O(n logn) +O(n) + conveting to arrau
    //S.C :- O(n)

    public static int[][] mergeOptimal(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            } else {
                int[] curr = res.getLast();
                curr[0] = Math.min(curr[0], interval[0]); // it directly updates the values in list because java uses references
                curr[1] = Math.max(curr[1], interval[1]);

            }
        }

        return res.toArray(new int[res.size()][]);

    }


    //With extra space
    //T.C :- 2O(n+m)
    //S.C :- O(n+m)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int M = 0;
        int N = 0;
        int index = 0;
        while (M < m && N < n) {

            if (nums1[M] <= nums2[N]) {
                arr[index] = nums1[M];
                M++;
                index++;
            } else {
                arr[index] = nums2[N];
                N++;
                index++;

            }

        }
        while (M < m) {
            arr[index] = nums1[M];
            M++;
            index++;
        }
        while (N < n) {
            arr[index] = nums2[N];
            N++;
            index++;
        }
        if (m + n >= 0) System.arraycopy(arr, 0, nums1, 0, m + n);
        System.out.println(Arrays.toString(nums1));
    }


    //without extra space
    //T.C :- n(length of second array) + n log m
    //S.C - 0
    public static void mergeBetter(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        for (int j : nums2) {
            nums1[m] = j;
            m++;
        }
        Arrays.sort(nums1);
    }


    //T.C:- O(m+n)
    //here we are filling the elements in the extra backward space of nums1 by comparing largest element of both arrays
    //Test case
    /*
    num1={4,5,6,0,0,0} m =3
    num2={1,2,3} n=3
    */
    public static void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }

        int pos = (m + n) - 1;
        int left = m - 1;
        int right = n - 1;
        while (right >= 0) {
            if (left >= 0 && nums1[left] > nums2[right]) {
                nums1[pos] = nums1[left];
                left--;
            } else {   //this will work even if num1 get exhausted
                nums1[pos] = nums1[right];
                right--;
            }
            pos--;
        }
    }


    //T.C:- O(n^2)
    //S.C:-
    public static ArrayList<Integer> findTwoElementBrute(int[] arr) {
        int missing = -1;
        int repeating = -1;

        for (int i = 1; i <= arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == i) {
                    count++;
                    System.out.println(count);
                }
            }
            if (count == 2) {
                repeating = i;
            } else if (count == 0) {
                missing = i;
            }
            if (missing != -1 && repeating != -1) {
                break;
            }
        }
        List<Integer> numbers = Arrays.asList(missing, repeating);
        return new ArrayList<>(numbers);


    }


    //T.C:- O(2n)
    //S.C:- O(n-1)
    /*
     here we get a array i  the range [1,n], n is the length of array
     we have to find missing number and number that is repeated two times
     1) we hash the array to have a count
     2) we run a loop from one to n and check
     if(the map not contains a number it will be a missing num)
     or if (its count ==2) it is a repeating number

    */
    public static ArrayList<Integer> findTwoElementBetter(int[] arr) {
        int missing = -1;
        int repeating = -1;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (int i = 1; i <= arr.length; i++) {
            if (!map.containsKey(i)) {
                missing = i;
            } else if (map.get(i) == 2) {
                repeating = i;
            }
            if (missing != -1 && repeating != -1) {
                break;
            }
        }
        List<Integer> numbers = Arrays.asList(missing, repeating);
        return new ArrayList<>(numbers);
    }


    //T.C:- O(n)
    //By maths trick
    //x=repeating no. y=missing no.
    //check youtube
    public static ArrayList<Integer> findTwoElementOptimal(int[] arr) {
        int n = arr.length;
        long actualSum = (long) n * (n + 1) / 2;
        long actualSqSum = (long) n * (n + 1) * (2 * n + 1) / 6;
        long sum = 0;
        long sqSum = 0;
        for (int el : arr) {
            sum += el;
            sqSum += (long) el * el;
        }

        long first = sum - actualSum;
        long second = (sqSum - actualSqSum) / first;
        int repeating = (int) ((first + second) / 2);
        int missing = (int) (second - repeating);


        ArrayList<Integer> list = new ArrayList<>();
        list.add(repeating);
        list.add(missing);
        return list;

    }

    //count no. of pairs where nums[i] >nums[j]
    static int inversionCount(int arr[]) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) count++;
            }
        }
        return count;
    }


    //   Using logic of merge sort(altering the data)
    //T.C:- nlog m
    //S.C:- O(n)
    static int inversionCountOptimal(int arr[]) {
        int low = 0;
        int high = arr.length - 1;
        return inversionCountOptimal(arr, low, high);

    }

    public static int inversionCountOptimal(int[] arr, int low, int high) {
        int count = 0;
        if (low >= high) return count;

        int mid = (low + high) / 2;
        count += inversionCountOptimal(arr, low, mid);
        count += inversionCountOptimal(arr, mid + 1, high);
        count += merge(arr, low, mid, high);
        return count;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int count = 0;

        ArrayList<Integer> temp = new ArrayList<>();

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                count += (mid - left + 1); //Modification 2
                right++;
            }
        }

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }

        return count;
    }


    //count no. of pairs where nums[i] >2 * nums[j]
    //T.C:-O(n^2)
    //S.C:- O(1)
    public static int reversePairsBrute(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long num = (long) nums[j] * 2;
                if (nums[i] > num) {
                    count++;
                }
            }
        }


        return count;
    }

    /*
      =>T.C:- 2n * log n
    log n -  divides the array in each step
    n - counting pairs
    n -  for merging the arrays

    =>  S.C:- O(n)
    */

    public static int reversePairsOptimal(int[] arr) {

        return reversePairsBetter(arr, 0, arr.length - 1);
    }

    public static int reversePairsBetter(int[] arr, int low, int high) {
        int count = 0;
        if (low >= high)
            return count;
        int mid = (low + high) / 2;
        count += reversePairsBetter(arr, low, mid);
        count += reversePairsBetter(arr, mid + 1, high);
        count += countPairs(arr, low, mid, high);
        return count;
    }

    private static int countPairs(int[] arr, int low, int mid, int high) {
        int count = 0;
        int ptr1 = low;
        int ptr2 = mid + 1;
        int left = low;
        int right = mid + 1;

        for (int i = ptr1; i <= mid; i++) {
            while (ptr2 <= high && arr[i] > (long) 2 * arr[ptr2]) {
                ptr2++;
            }
            count += (ptr2 - (mid + 1));
        }

        ArrayList<Integer> list = new ArrayList<>();

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                list.add(arr[left]);
                left++;
            } else {
                list.add(arr[right]);
                right++;
            }
        }

        while (left <= mid) {
            list.add(arr[left]);
            left++;
        }
        while (right <= high) {
            list.add(arr[right]);
            right++;
        }
        for (int i = low; i <= high; i++) {
            arr[i] = list.get(i - low);
        }

        return count;
    }

    //T.C:- n^3 approx
    public static int maxProductBrute(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int Pmax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int p = 1;
                for (int k = i; k <= j; k++) {
                    p = p * nums[k];
                }
                Pmax = Math.max(Pmax, p);
            }
        }
        return Pmax;
    }

    //T.C:- O(n^2)
    public static int maxProductBetter(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int Pmax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int p = 1;
            for (int j = i; j < nums.length; j++) {
                p = p * nums[j];
                Pmax = Math.max(Pmax, p);
            }
        }
        return Pmax;
    }

    // T.C:-O(n)
    // S.C:- O(1)
    //Observation
    public static int maxProductOptimal(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int maxP = Integer.MIN_VALUE;
        int prefix = 1;
        int suffix = 1;
        int i = 0;
        int j = nums.length - 1;
        while (i <= nums.length - 1) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            prefix = prefix * nums[i];

            suffix = suffix * nums[j];

            maxP = Math.max(maxP, Math.max(prefix, suffix));

            j--;
            i++;

        }
        return maxP;
    }


    //Container with most water
    public static int maxArea(int[] height) {
        int max = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                max = Math.max(max, h * w);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1};
        System.out.println(maxArea(arr));

    }
}


