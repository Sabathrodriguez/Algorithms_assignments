package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Instant startTime = Instant.now();
        int[] arr1 = new int[] {5,4,3,2,1};
        quicksort(arr1);
        System.out.println(Arrays.toString(arr1));
        Instant endTime = Instant.now();
        Duration interval = Duration.between(startTime, endTime);
        System.out.println(interval);
    }

    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    public static void quicksort(int[] arr, int left, int right) {
        if ((right-left) > 0) {
            int pivot = right;
//            int pivot = (right+left)/2;
            int r = partition(arr, right, pivot);
            quicksort(arr,left,r-1);
            quicksort(arr, r+1,right);
        }
    }

    public static int partition(int[] arr, int right, int pivot) {
        swap(arr, pivot,right);
        int l = -1;

        for (int i = 0; i < right; i++) {
            if (arr[i] < arr[right]) {
                    l += 1;
                    swap(arr, l, i);
            }
        }
        swap(arr, l+1, right);

        return l+1;
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
