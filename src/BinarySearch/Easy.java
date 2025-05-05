package BinarySearch;

import org.w3c.dom.html.HTMLHeadElement;

import java.util.Arrays;

public class Easy {

    //BINARY SEARCH ON 1d array


    //T.C:- O(log base 2 n)
    //Overflow case-- if the search space s between 0 to INT.MAX
    // to avoid overflow we can use { int mid =(low + (high-low))/2   }
    public static int binarySearchRecursive(int[] nums, int target) {


        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private static int binarySearch(int[] nums, int low, int high, int target) {
        if (low > high) {        //this is the base case to stop recursion
            return -1;
        }
        // to avoid overflow we can use { int mid =(low + (high-low))/2   }
        int mid = (low + high) / 2;  //floor value
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, high, target);
        } else {                                                   //when nums[mid] > target
            return binarySearch(nums, low, mid - 1, target);
        }
    }

    public static int binarySearchIterative(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;


        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        return -1;
    }


    //Lower bound-- smallest (index) such that  arr[index]>= target
    //we have to return the smallest index such that arr[index]>= target
    //if such index not exist that means all the elements are smaller than target ,the hypothetical index(length of array) will be the ans
    //T.C:- O(log base 2 n)
    public static int lowerBound(int[] arr, int target) {
        int ans = arr.length;        //Hypothetical answer


        return lowerBoundSearch(arr, 0, arr.length - 1, target, ans);

    }

    private static int lowerBoundSearch(int[] arr, int low, int high, int target, int ans) {
        if (low > high) return ans;
        int mid = (low + high) / 2;
        if (arr[mid] >= target) {
            ans = mid;      //this can be my answer
            return lowerBoundSearch(arr, low, mid - 1, target, ans); //again checking smallest index  at left
        } else {                                                           //this will execute when at the left no index can be possible
            return lowerBoundSearch(arr, mid + 1, high, target, ans);
        }

    }


    public static int lowerBoundIterative(int[] arr, int target) {
        int ans = arr.length;   //Hypothetical answer
        int low = 0;
        int high = arr.length - 1;

        if (arr[arr.length - 1] < target) {
            //Edge case-if the last element is already smaller than there can be no index such that  arr[index]>= target
            return ans;
        }


        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    //Upper bound-- smallest (index) such that  arr[index]> target
    //we have to return the smallest index such that arr[index]> target
    //if such index not exist that means all the elements are smaller than target ,the hypothetical index(length of array) will be the ans
    //T.C:- O(log base 2 n)
    public static int UpperBoundIterative(int[] arr, int target) {
        int ans = arr.length;   //Hypothetical answer
        int low = 0;
        int high = arr.length - 1;

        if (arr[arr.length - 1] <= target) {    // Edge case-if the last element is already smaller or equal than there can be no index such that  arr[index]> target
            return ans;
        }


        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

//Ques-Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

    //Observation=> max(low,High) after the low and high cross each other will be the answer
    //it is similar to finding lower bound
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;  //When the loop exits, the low pointer represents the position where the target can be inserted while maintaining the order of the array.
    }

    public int searchInsertUsingLowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;

        if (nums[nums.length - 1] > target) return ans;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    //Floor of x:  largest number in array <= x
    //Ceil of x:  Smallest number in array >= x  (similar to lower bound)
    public static int[] getFloorAndCeil(int[] a, int n, int x) {
        //Going through lowest bound;
        int floor = -1;
        int ceil = -1;


        int low = 0;
        int high = a.length - 1;
        // Binary search for ceiling
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] >= x) {
                ceil = a[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        low = 0;
        high = a.length - 1;
        // Binary search for floor
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] <= x) {
                floor = a[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[]{floor, ceil};
    }


    //first and last occurrence of an element
    //By linear search
    //T.C:- O(n)
    public int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && first == -1) first = i;
            else if (nums[i] == target) {
                last = i;
            }
        }
        return new int[]{first, last};
    }

    //Search range By (using lower bound and upper bound)
    //T.C:- 2* O(log n)
    //T.C:- O(1)
     /*
    test case int ar={1} target=1
    */
    public static int[] searchRangeBinary(int[] nums, int target) {
        int ans = nums.length; //hypothetical index
        int first = ans;
        int last = ans;

        //calculating lower bound
        int low = 0;
        int high = nums.length - 1;

        if (nums.length == 0 || nums[nums.length - 1] < target) {
            return new int[]{-1, -1};
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                first = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (first == ans || nums[first] != target) {       //that means the last is not updated and still it is equal to hypothetical index
            return new int[]{-1, -1};                      // Or if the last is updated and the element at first is not target  (means no occurrence of the target )
        }

        //Calculating upper bound
        low = 0;
        high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                last = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{first, last - 1};
    }


    //simple binary search(without lower and upper bound)
    //T.C:- 2* O(log n)
    //T.C:- O(1)

    public static int[] searchRange2(int[] arr, int target) {
        int first = -1;
        int last = -1;

        int low = 0;
        int high = arr.length - 1;


        //finding first occurrence using B.S
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                first = mid;
                high = mid - 1; //moving to left for leftmost index
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        //if no first occurrence
        if (first == -1) {
            return new int[]{-1, -1};
        }

        //if there is first occurrence present
        low = 0;
        high = arr.length - 1;

        //finding first occurrence using B.S
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                last = mid;
                low = mid + 1; //moving to right for rightmost index
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{first, last};
    }


    //Using first and last occurrences
    public static int countOccurrence(int[] arr, int x) {
        int first = -1;
        int last = -1;

        int low = 0;
        int high = arr.length - 1;


        //finding first occurrence using B.S
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                first = mid;
                high = mid - 1; //moving to left for leftmost index
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        //if no first occurrence
        if (first == -1) {
            return 0;
        }

        //if there is first occurrence present
        low = 0;
        high = arr.length - 1;

        //finding first occurrence using B.S
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                last = mid;
                low = mid + 1; //moving to right for rightmost index
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (last - first) + 1;
    }


    //here we have to check if the element exist
    // array contains Duplicate  elements in rotated sorted array
    //the previous method  will not work for this type of cases
    //  arr={1,0,1,1,1}  arr={3,1,2,3,3,3,3}--> here we not able to find sorted part
    /*
    //nums[low] == nums[mid] && nums[mid] == nums[high]  --> THIS IS CREATING PROBLEM WHILE FINDING SORTED PART
      so when this condition arise we trim down the array by low++ high -- until this condition get false

    */
    public static boolean CheckOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;

                } else {
                    high = mid - 1;

                }
            }

        }
        return false;
    }

    /*
  Process:-
  while(low<=high)
  1) here we calculate mid
  3) if the target found at mid -- return (mid)
  2) and check if the right (nums[low] <= nums[mid]) or left (nums[mid] >= nums[high]) part is sorted    (EITHER THE LEFT OR RIGHT IS ALWAYS SORTED)
  3) if the left is sorted
     we check:-
      if the target lies in left part or not
        if lies - eliminate right part
        else - eliminate left part
  4)  if the right part is sorted
     we check:-
      if the target lies in right part or not
          if lies - eliminate left part
          else - eliminate right part

   Dry Run -{7,8,9,1,2,3,4,5,6}
   T.C:- O(log n)
    */
    //Elimination is the key
    public static int searchInRotatedSortedArray(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[low] <= nums[mid]) {                         //Checking If left part is sorted
                //Checking if the target lies in left part or not
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;                                      //if it lies -->eliminate right part
                } else {
                    low = mid + 1;                                       // If it not -->eliminate left part
                }
            } else {                                                     //If Right part is sorted
                //Checking if the target lies in right part or not
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;                                       //if it lies -->eliminate right part
                } else {
                    high = mid - 1;                                      //If it not -->eliminate left part
                }
            }
        }
        return -1;    //IF element is not present
    }

    public boolean searchInRotatedSortedArrayContainingDuplicates(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;

                } else {
                    high = mid - 1;

                }
            }

        }
        return false;
    }


    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[low] <= nums[mid]) {
                min = Math.min(min, nums[low]);
                low = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                high = mid - 1;
            }

        }
        return min;
    }

    public int findMinContainDuplicates(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int min = Integer.MAX_VALUE;

        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            min = Math.min(min, nums[mid]);
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }
            if (nums[low] <= nums[mid]) {
                min = Math.min(min, nums[low]);
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return min;
    }

    //An element is single--> if it is not equal to both left one and right one so we have to compare --> nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]
    //Check video
    public static int singleNonDuplicate(int[] nums) {
        //Edge cases if we stand at last or first or single element in array(Comes with experience)
        if (nums.length == 1) {              //if size of array is one means the element present is single
            return nums[0];
        }
        if (nums[0] != nums[1]) return nums[0];               //if we stand at 0 index(mid=0) we check for right element
        if (nums[nums.length - 1] != nums[nums.length - 2])   //if we stand at n-1 index(mid=last index) we check for left element
            return nums[nums.length - 1];


        //Trimming the boundary to avoid extra checks
        int low = 1;                     //starting low from index 0 (because we have already checked for it in edge case)
        int high = nums.length - 2;      //starting high from last index-1  (because we have already checked for it in edge case)
        while (low <= high) {

            int mid = (low + high) / 2;                                                      //Calculating mid
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1])                   //comparing with both left and right element from mid
                return nums[mid];                                                           //returning the mid element if its not equal to left and right
//now we are checking which part to eliminate because the element at mid is not single
            if (mid % 2 == 0) {                                                             //if we are at mid and mid is even

                if (nums[mid] == nums[mid + 1]) {                                           //if nums[mid] == nums[mid + 1]  we are left of single element
                    low = mid + 1;                                                          //eliminate left part
                } else {                                                                    //else  nums[mid]! == nums[mid + 1] we are at right of single element
                    high = mid - 1;                                                         //eliminate right part
                }

            } else {                                                                         //if we are at mid and mid is odd

                if (nums[mid] == nums[mid + 1]) {                                            //if nums[mid] == nums[mid + 1]  we are right of single element
                    high = mid - 1;                                                          //eliminate right part
                } else {                                                                     //else  nums[mid]! == nums[mid + 1] we are at left of single element
                    low = mid + 1;                                                           //eliminate left part
                }
            }
        }
        return -1;  //dummy statement
    }

    //BruteForce
    //T.C: O(n)
    /*
    test cases:- {5,4,3,2,1} , {1,2,3,4,5} , {1,2,1,3,5,6,4}

   An element is considered as peak if nums[i-1] < nums[i] > nums[i+1]
   Given -- nums[-1] = nums[n] = -∞  (means the left of the first element is -∞ and thr right of last element is -∞ )

   Conditions(for getting peak element) :-
   1)  (i==0 && nums[i] > nums[i + 1])
   2)  (nums[i - 1] < nums[i] &&  nums[i] > nums[i+1])
   3)  (nums[i - 1] < nums[i] && i==n-1)
    */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;


        for (int i = 0; i < n; i++) {
            if ((i == 0 || nums[i - 1] < nums[i]) && (i == n - 1 || nums[i] > nums[i + 1])) {
                return i;  //index of peak element
            }
        }
        return -1;
    }

    //Now we have to solve it in (log n) using Binary Search
    //This will work for both one peak or multiple peak
    //Visualize using graph
    //T.C:- O(logn)
    public static int findPeakElementBS(int[] nums) {
        int n = nums.length;

        //Edge cases
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;


        int low = 1;
        int high = nums.length - 2;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;  //index of peak element
            } else if (nums[mid - 1] < nums[mid]) {
                low = mid + 1;
            } else {                         //This will work for even down peak{1,5,1,2,1}
                high = mid - 1;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1,5,1,2,1};

        System.out.println(findPeakElement(arr));
    }
}