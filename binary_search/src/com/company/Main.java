package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random random = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        int[] arrClone = arr.clone();
        Arrays.sort(arrClone);
        System.out.println(Arrays.toString(arrClone));
        mergesort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, 0, arr.length, 10));


    }

    private static void mergesort(int[] arr) {
        mergesort(arr, 0, arr.length-1);
    }

    private static void mergesort(int[] arr, int left, int right) {
        if (right > left) {
            int m = (right+left) / 2;
            mergesort(arr, left,m);
            mergesort(arr, m+1,right);
            merge(arr, m, left, right);
        }
    }

    private static void merge(int[] arr, int m, int left, int right) {
        int i = left;
        int j = m + 1;
        int[] b = new int[(right-left)+1];

        for (int k = 0; k < right; k++) {
            if (i > m) {
                b[k] = arr[j];
                j++;
            } else if (j > right) {
                b[k] = arr[i];
                i++;
            } else if (arr[i] > arr[j]) {
                b[k] = arr[j];
                j++;
            } else {
                b[k] = arr[i];
                i++;
            }
        }

        for (int k = 0; k < right; k++) {
            arr[k] = b[k];
        }
    }

    private static int binarySearch(int[] arr, int left, int right, int i) {
        if (right >= left) {
            int m = (right+left) / 2;

            if (arr[m] == i) {
                return m;
            }
            else if (arr[m] > i) {
                return binarySearch(arr, left, m-1, i);
            }
            else {
                return binarySearch(arr, m+1, right, i);
            }
        }
        return -1;
    }
}
