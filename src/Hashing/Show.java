package Hashing;

import java.util.*;

public class Show {

//    Hashing
//1)Precomputation
//    2)Fetching


    public static int frequencyCount(int k, int[] ar, int atmost) {
        int[] hash = new int[atmost + 1];
        int min;
        int max = 0;
        for (int i = 0; i < ar.length; i++) {
            hash[ar[i]] = hash[ar[i]] + 1;
        }
        System.out.println(k + "->Appears " + hash[k] + " times");

        System.out.println(max);
        return hash[k];

    }

    //For upper case
    public static int CharfrequencyCount(char ch, String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            hash[charAt - 'A']++;
        }
        return hash[ch - 'A'];
    }

    //For all
    public static int frequencyCountAll(char ch, String s) {
        int[] hash = new int[256];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i)]++;
        }
        return hash[ch];
    }

    //in this(map) u dont have to set array size to atmost
    public static int frequency(int[] ar, int key) {
        Map<Integer, Integer> map = new HashMap<>(ar.length);
        for (int i = 0; i < ar.length; i++) {
            if (map.containsKey(ar[i])) {
                map.put(ar[i], map.get(ar[i]) + 1);
            } else {
                map.put(ar[i], 1);
            }
        }
        map.forEach((a, b) -> {
            System.out.println("key:" + a + ",Value:" + b);
        });
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return 0;
    }

    public static int charFrequncy(char[] ar, char key) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ar.length; i++) {

            if (map.containsKey(ar[i])) {
                map.put(ar[i], map.get(ar[i]) + 1);
            } else {
                map.put(ar[i], 1);


            }

        }
        return map.get(key);
    }


    public static Boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (int val : map.values()) {
            if (!hashSet.contains(val)) {
                hashSet.add(val);
            } else {
                return false;
            }
        }
        return true;
    }
//    public Integer  findUnique(int[] arr){
//        Map<Integer, Integer> map = new HashMap<>();
//        HashSet<Integer> hashSet = new HashSet<>();
//        for (int i = 0; i < arr.length; i++) {
//            if (map.containsKey(arr[i])) {
//                map.put(arr[i], map.get(arr[i]) + 1);
//            } else {
//                map.put(arr[i], 1);
//            }
//        }
//        map.forEach();
//
//        for (int val : map.values()) {
//            if (!hashSet.contains(val)) {
//                hashSet.add(val);
//            } else {
//                return false;
//            }
//        }
//        return true;
//
//    }


    public static void main(String[] args) {
        char[] ar = {'a', 'b', '2', '3', '3', '3'};
        System.out.println(charFrequncy(ar,'2'));



    }

}
