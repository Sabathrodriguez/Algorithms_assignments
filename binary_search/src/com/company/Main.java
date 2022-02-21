package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random random = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000'π=\-]\
        ');
        }
        Arrays.sort(arr);
        System.out.println(binarySearch(arr,0, arr.length, 10));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 10) {
                System.out.println(i);
                break;
            }
        }
    }

    private static int binarySearch(int[] arr, int left, int right, int i) {
        if ((right-left) > 0) {
            int m = (right+left) / 2;

            if (arr[m] == i) {
                return m;
            }
            if (arr[m] > i) {
                binarySearch(arr, left, m, i);
            }
            else if (arr[m] < i) {
                binarySearch(arr, m+1, right, i);
            }
        }
        return -1;
    }
}
