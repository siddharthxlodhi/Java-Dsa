package Hashing;

import java.util.HashMap;
import java.util.Map;

public class Show1 {

    public static int[] frequencyCount(int[] arr, int atmost) {
        int[] hash = new int[atmost + 1];
        for (int i = 0; i < arr.length; i++) {
            hash[arr[i]] = hash[arr[i]] + 1;
        }
        return hash;
    }
//ASCII based
    public static int charfrequency(char ch, String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash[c - 'a'] = hash[c - 'a'] + 1;               //'a'-'a'=0   97-97=0 ,'b'-'a'=1
        }
        return hash[ch - 'a'];
    }


    public static Integer frequencyCountAdv(int[] arr,int key){
        Map<Integer,Integer> map=new HashMap<>(arr.length);

        for (int i=0;i<arr.length;i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }
            else {
                map.put(arr[i],1);
            }
        }
        if (map.containsKey(key)) {

            return map.get(key);
        }
        return 0;


    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 2, 3, 3, 3};
        System.out.println(frequencyCountAdv(ar,4));


    }

}
