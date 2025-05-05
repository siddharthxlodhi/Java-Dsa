package Sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class Show {

    //taking minimum to first position
//assuming first as minimum and then comparing it with others and swap with minimum element,this we do for each element expect last(already get sort)
    //every time the portion get reduced from left
    //Tc:- n^2
    public static int[] selectionSort(int[] arr) {

        for (int i = 0; i <= arr.length - 2; i++) {
            int min = i; //considering first index to have  minimum
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;      //updating the minimum index to new that has minimum element
                }
            }
            int temp = arr[i]; //swapping (ith) element with (minimum)
            arr[i] = arr[min];
            arr[min] = temp;

        }
        return arr;
    }


    //taking maximum  to last position(adjacent swaps)
    //in this we swap adjacent elements by comparing ith element to i+1 th this will move the maximum element to last
    //every time the portion get reduced from right
    //T.c= n^2(worst and average)
    //T.C= n(best  array already sorted)
    public static int[] bubblesort(int[] arr) {

        for (int i = 0; i <= arr.length - 1; i++) {
            int didSwap = 0;
            for (int j = 0; j < (arr.length - i) - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    didSwap = 1;
                }
            }
            if (didSwap == 0) {
                break;
            }
            System.out.println("runs");
        }

        return arr;
    }

    //loop diffrence(same)
    public static int[] bubblesort2(int[] arr) {

        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    //In insertion sort we pick one element(from start) and compare it towards left element and place it to correct position
    // Worst T.c= O(n^2) in case array is reverse
    //Best T.c =O(n) if array is already sorted because inner while loop will not ron
    public static int[] insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }

        }
        return arr;
    }

    //T.C:- n log n
    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid); //first
        mergeSort(arr, mid + 1, high);  //second
        merge(arr, low, mid, high); //third
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        ArrayList<Integer> list = new ArrayList<>();   //temporary

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

        for (int i =low;i<=high;i++){
            arr[i]=list.get(i-low);     //here i-low has a logic to place element at correct position in the original array
        }

    }


    public static void main(String[] args) {
        int[] arr = {6, 2, 8, 4, 5};
        mergeSort(arr,0,4);
        System.out.println(Arrays.toString(arr));
    }


}
