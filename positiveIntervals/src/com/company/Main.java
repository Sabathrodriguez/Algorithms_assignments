package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<ArrayList<Integer>> ar = new ArrayList<>();
        Main main = new Main();
        main.positiveIntervals(new int [] {+3, -5, +7, -4, +1, -8, +3, -7, +5, -9, +5, -2, +4}, ar);
        System.out.println(ar);
    }
    private void positiveIntervals(int[] arr, ArrayList<ArrayList<Integer>> list) {
        for (int i = 0; i < arr.length; i++) {
            int s = arr[i];
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = i + 1; j < arr.length-1; j++) {
                s += arr[j];
                //if positive interval
                if (s + arr[j+1] >= 0) {
                    if (j == (i+1))
                        temp.add(arr[i]);
                    temp.add(arr[j]);
                    temp.add(arr[j+1]);
                    s += arr[j+1];
                    j = j + 1;
                }
                //if not positive interval
                else {
                    i = j;
                    break;
                }
            }
            if (!temp.isEmpty()) {
                list.add((ArrayList<Integer>) temp.clone());
                temp.clear();
            }
        }
    }
}
