package Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Monotonic {


    //When we follow some order to maintain elements in a stack(increasing,decreasing,or other order) then we say the stack is a MOn
    //Expected T.C:- O(n1+n2)

    public static int[] nextGreaterElement(int[] nums) {
        int[] arr = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int nextG = -1;

            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextG = stack.peek();
            }
            stack.push(nums[i]);
            arr[i] = nextG;
        }
        return arr;
    }


    //Variation of next greater element
    //Here we have to take care of firs next element which are greater than the current
    // O(n1) + near O(n2^2)
    //O(n1)-for answer +O(n2)
    public int[] nextGreaterElementBrute(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] arr = new int[nums1.length];

        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(arr[i], -1);
            for (int j = i + 1; j < nums2.length; j++) {
                if (arr[i] < arr[j]) {
                    hashMap.put(arr[i], arr[j]);
                    break;
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = hashMap.get(nums1[i]);
        }
        return arr;
    }

    //O(n1*n2)
//    public int[] nextGreaterElementBrute2(int[] nums1, int[] nums2) {
//
//        int[] arr = new int[nums1.length];
//
//        for (int i = 0; i < nums1.length; i++) {
//            arr[i] = -1;
//            for (int j = 0; j < nums2.length; j++) {
//                if (nums1[i] == nums2[j]) {
//                    for (int k = j + 1; k < nums2.length; k++) {
//                        if (nums2[k] > nums1[i]) {
//                            arr[i] = nums2[k];
//                            break;
//                        }
//                    }
//                }
//
//            }
//        }
//
//        return arr;
//    }

    //we can reduce the T.C
    //The intuition comes with multiple poles and man in front of them
    //Here we first find the next greater of each element in nums 2, and put (el,next greater) in map

    // O(n1) + O(2n2)
    //O(n1)-for answer +O(n2) for stack +O(n2) for hashmap
    public static int[] nextGreaterElementOptimal(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[nums1.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            int nextG = -1;
            while (!stack.empty() && stack.peek() <= nums2[i]) { //at worst it can run for O(n) as it can atmax remove n els.   //If the stack is empty at start or gets empty (as no next greater element is present for the current element)
                stack.pop();                                        //we will pop the stack until we find -->stack.peek() > nums2[i]
            }
            if (!stack.isEmpty()) {                                  //while if the stack is empty after popping that means on the top there is ,-->stack.peek() > nums2[i]
                nextG = stack.peek();
            }
            map.put(nums2[i], nextG);                               //we are keeping the track of next greater element of every element
            stack.push(nums2[i]);
        }

        int i = 0;
        for (int el : nums1) {
            arr[i] = map.get(el);
            i++;
        }
        return arr;
    }


    //Note:-  moving in circular array (index % arr.length)

    //O(n^2)
    //O(n)
    public int[] nextGreaterElementsCircularArrayBrute(int[] nums) {
        int n = nums.length;

        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = -1;
            int j = i + 1;
            j = j % n;
            //Moving in circular
            while (j != i) {
                if (nums[j] > nums[i]) {
                    arr[i] = nums[j];
                    break;
                }
                j++;
                j = j % n;
            }
        }
        return arr;
    }


    //Here we are hypothetically doubling the array
    // Test case{ 2,10,12,1,11 } + { 2,10

    //T.C:- O(4n)
    //S.C:- O(n) for answer only + O(2n)
    public int[] nextGreaterElementsCircularArrayOptimal(int[] nums) {
        int n = 2 * nums.length - 1;
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[nums.length];


        for (int i = n; i >= 0; i--) { //i is a hypothetical index   //O(2n)
            int index = i % nums.length;
            while (!stack.isEmpty() && stack.peek() <= nums[index]) {   //At max O(2n)
                stack.pop();
            }
            if (i < nums.length) {
                arr[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[index]);
        }
        return arr;
    }


    public static int trap(int[] height) {
        int preMax = 0;
        int total = 0;

        int[] suffMax = suffMax(height);

        for (int i = 0; i < height.length; i++) {


            if (preMax > height[i] && height[i] < suffMax[i]) {
                total += Math.min(preMax, suffMax[i]) - height[i];
            }
            preMax = Math.max(preMax, height[i]);
        }

        return total;
    }

    private static int[] suffMax(int[] height) {
        int[] suffMax = new int[height.length];
        int max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(height[i], max);
            suffMax[i] = max;
        }
        return suffMax;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

}

