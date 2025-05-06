package Array;

import java.util.*;

public class Medium {


    //Better approach
    //T.C:-O(N) --> average case
    //T.C:-O(N^2) --> worst case when hashmap degrade
    //T.C:- O(n)
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                indices[0] = map.get(target - nums[i]);
                indices[1] = i;
                return indices;
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    //Optimal approach
    //Two sum variety 2 where (yes or no)
    //if there are two nos that make a sum to target
    //T.C:- O(n)+O(nlogn) --> for soorting and while loop in worst case
    public static boolean twoSum2(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        Arrays.sort(nums);
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return true;
            } else if (nums[i] + nums[j] > target) {

                j--;

            } else {
                i++;
            }
        }

        return false;
    }

    //Better
    //T.C:-O(2n)
    public void sortColorsBetter(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        for (int i = 0; i < count0; i++) {
            nums[i] = 0;
        }
        for (int i = count0; i < (count0 + count1); i++) {
            nums[i] = 1;
        }
        for (int i = count0 + count1; i < (count0 + count1 + count2); i++) {
            nums[i] = 2;
        }
    }

    //Using Dutch National Flag Algorithm(single pass algorithm)
    //0's-> 0 to low-1
    //1's-> low to mid-1
    //unsorted part -> mid to high
    //2's-> high+1 to n(array length)-1
    //low is also at 0 because there are in between no elements
    //T.C:-O(n)
    public void sortColorsOptimal(int[] nums) {
        int mid = 0;
        int high = nums.length - 1;
        int low = 0;
        while (mid <= high) {
            if (nums[mid] == 0) {
                //Swap nums[mid] and nums[low] and mid++ low++
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                mid++;
                low++;
            } else if (nums[mid] == 1) {
                //Increment mid++
                mid++;

            } else {
                //swap nums[high] and nums[mid] and high--;
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }
        }

    }

    //Brute force -by iterating and taking each element counting its appearance if it is greater than n/2 ,its the answer  T.c-n^2
    //Better - By hashing the count of each element and then iterating through hash    T.c-O(n logn) +O(n)

    //Optimal--  By moore's voting algorithm
    //in this we take a counter and assume first  element as majority;
    //--if  if the count is 0 we  will assume  appearing element as majority one
    //--if next time the same element appears increase count
    //--if  if next time the other element appears decease count
    //T.C- O(n)
    //only 1 element can appear more than 2 times
    //((someone appear more than n/2 times will not get cancelled)).
    public static int majorityElementOptimal(int[] nums) {
        int count = 1;
        int mElement = nums[0];  //mElement=majority element

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                mElement = nums[i];
                count++;
            } else if (nums[i] == mElement) {
                mElement = nums[i];
                count++;
            } else {
                count--;
            }

        }
        return mElement;
    }

    //Optimal-By kadane's algorithm
    //edge case why compare with 0 at last--because if all numbers are negative then it make them 0 at start,which should not done
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    //whenever sum gets 0 we again calculate th next subarray sum,when then sum is greater than max that means the new end ansIndex will get updated
    public int printMaxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int ansStart;
        int ansEnd;
        for (int i = 0; i < nums.length; i++) {
            if (sum == 0) {
                start = i;
            }
            sum += nums[i];
            if (sum > max) {
                max = sum;
                ansStart = start;
                ansEnd = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    //D.P problem
    //Taking each day as selling day and taking minimum stock prices on the left days and calculating profit ,maximum profit will be the answer.
    //T.C-O(n)
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int maxProfit = 0;
        int bestBuy = prices[0];  //bestBuy=minimum price
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > bestBuy) {
                int cost = prices[i] - bestBuy;
                maxProfit = Math.max(cost, maxProfit);
            }
            bestBuy = Math.min(bestBuy, prices[i]);
        }
        return maxProfit;
    }

    /*
    BruteForce(nextPermutation)-{
    1)calculate all permutations in sorted order
    2)Linear search and go to next index of given one
    3)if next index not exist first one will be the answer
    4) time complexity, approximately 𝑂(𝑛!) which is high
    }
    */


    /*
     Optimal(nextPermutation)-{
    observations:-
    1)longest prefix match
    find breakpoint (when arr[i] < arr[i+1]),   (ind)=i
    3)if we could not find ind we just reverse the whole array
    2)find someone slightly greater than arr[ind] in right and swap them
    3)reverse the array in right after (ind)

    Dry run {2,1,5,4,3,0,0)
   T.C:- approx O(3n) -->3 loop can go upto n times
    }
    */
    public void nextPermutationOptimal(int[] nums) {
        int ind = -1;    //this will be the index we find from where the sequence starts to decrease

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }

        if (ind == -1) {    //this means given array is the last permutation that can be created,so reversing it will give answer of next permutation
            //reverse the whole given array
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }

        } else {      //if we found the index we find slightly greater index at right ,as we know from last element the elements will be decreasing to left till (ind)
            int slightGreaterIndex;
            for (int i = nums.length - 1; i > ind; i--) {    //so we find right to left before (ind)
                if (nums[i] > nums[ind]) {                   //if we got we swap it with nums[ind]
                    //swap nums[ind] and nums[i]
                    int temp = nums[i];
                    nums[i] = nums[ind];
                    nums[ind] = temp;
                    break;
                }
            }
            // now we will reverse the array after (ind) because it is in increasing order from right so reversing it will give the closest permutation
            int start = ind + 1;
            int end = nums.length - 1;
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    //Bruteforce
    //T.C:- O(n^2) approx
    public static ArrayList<Integer> leaders(int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            boolean isGreaterEqual = true;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    isGreaterEqual = false;
                    break;
                }
            }
            if (isGreaterEqual) {
                list.add(arr[i]);
            }

        }
        return list;
    }

    //T.c:- O(n)+O(m) O(m) for reversing list where m is no. of elements
    public static ArrayList<Integer> leadersOptimal(int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();
        int max = arr[arr.length - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > max) {
                list.add(arr[i]);
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        Collections.reverse(list);  //in place reversal
        return list;
    }

    //T.c-   O(n log(n))+O(n)
    public static int longestConsecutiveBetter(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int maxlength = 0;
        int length = 1;

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i - 1] + 1) == nums[i]) {
                length++;
                maxlength = Math.max(length, maxlength);
            } else if (nums[i - 1] == nums[i]) {
                maxlength = Math.max(length, maxlength);
            } else {
                length = 1;
            }
        }
        return maxlength;
    }

    // O(n)+O(2n)
    //S.C- O(n)
    public static int longestConsecutiveOptimal(int[] nums) {
        if (nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        int maxLength = 0;

        for (int element : set) {

            if (!set.contains(element - 1)) {
                int count = 1;
                while (set.contains(element + 1)) {
                    count++;
                    element++;
                }
                maxLength = Math.max(count, maxLength);
            }
        }
        return maxLength;
    }

    //Brute Force (this will not work if matrix contain -1 or anything that u will use
    //T.C: - (m*n)(m+n)+(m*n)
    public void setZeroes(int[][] matrix) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    markRow(matrix, i);
                    markCol(matrix, j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;

                }
            }
        }

    }

    public static void markRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[row][j] != 0) {
                matrix[row][j] = -1;
            }
        }
    }

    public static void markCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] != 0) {
                matrix[i][col] = -1;
            }
        }
    }

    //T.C- 2(m*n)
    //S.C- (m+n)
    ///in this we traverse the whole matrix and whenever we get 0 at any (i,j) we will mark the booleanRow booleanColumn  at index i and j respectively (true);
    public void setZeroesBetter(int[][] matrix) {
        int rowLen = matrix.length;         // number of rows
        int columnLen = matrix[0].length;     // number of columns

        boolean[] row = new boolean[rowLen];        //row  boolean array that contains true at index where 0 is found
        boolean[] column = new boolean[columnLen];   //column  boolean array that contains true at index where 0 is found

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    //Remaining
    public void setZeroesOptimal(int[][] matrix) {

    }


    //Rotate matrix by 90 degree
    //T.C:- O(n^2) as it is n*n matrix
    //S.C:- O(n^2)
    public static void rotateBrute(int[][] matrix) {

        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[j][(matrix.length - 1) - i] = matrix[i][j];

            }

        }

        System.out.println(Arrays.deepToString(newMatrix));
    }

    /*
    1)Transpose the matrix (matrix[i][j]=matrix[j][i])
    2) reverse each row.
    T.c-O(N/2 * N/2)
    s.c- O(1)
    */
    public static void rotateOptimal(int[][] matrix) {
//Transposing the matrix ( matrix[i][j]=matrix[j][i])
//we just have to do this with the element above diagonal only
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        //reversing each row
        for (int i = 0; i < matrix.length; i++) {
            int start = 0;
            int end = matrix.length - 1;
            while (start < end) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    //T.C- O(n*m)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);

            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);

                }
                bottom--;
            }
            if (left <= right) {

                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);

                }
                left++;
            }
        }
        return list;
    }

    //return total number of arrays whose sum==k
    //T.C:- O(n^2)
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //O(n* 1) --> in average of hashmap
    //S.C- O(n)
    //Here we keep the count of how many time sum-k appears
    public int subarraySumBetter(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int preSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum = preSum + nums[i];
            if (nums[i] == k) {
                count++;
            }
            //here we have to check how many times before we get (sum-k) so that we got correct answer
            if (map.containsKey(preSum - k)) {
                count = count + map.get(preSum - k);
            }
//            count+=map.getOrDefault(preSum-k,0);
            if (map.containsKey(preSum)) {
                map.put(preSum, map.get(preSum) + 1);
            } else {
                map.put(preSum, 1);
            }
//            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 3};
        System.out.println(majorityElementOptimal(arr));
    }
}
