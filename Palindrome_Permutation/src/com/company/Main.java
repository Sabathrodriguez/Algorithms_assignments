package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String test1 = "Tact Coa";
        String test2 = "tactcoapapa";
        String test3 = "papa";
        Main main = new Main();
        System.out.println(main.palindromePermutation(test3.toLowerCase()));
    }

    private boolean palindromePermutation(String str)
    {
        char[] strArr = str.toCharArray();
        Arrays.sort(strArr);
        Map<Character, Integer> map = new TreeMap<>();
        boolean pivot = true;

        for (Character ch : strArr)
        {
            if (ch != ' ')
            {
                if (map.containsKey(ch))
                {
                    int temp = map.get(ch);
                    map.replace(ch, temp + 1);
                } else
                {
                    map.put(ch, 1);
                }
            }
        }

        System.out.println(map);

        for (Character ch : map.keySet())
        {
            if (map.get(ch) % 2 != 0)
            {
                if (!pivot)
                {
                    return false;
                }
                else
                {
                    pivot = false;
                }
            }
        }

        return true;
    }
}
