package com.company;

public class Main {
    static int[] arr = new int[] {13,14,16,18,1,2,3,4,5,6,7,8,9,10,11,12};
    public static void main(String[] args) {
	// write your code here
        System.out.println(findK(0,arr.length-1));
    }

    public static int findK(int i, int j) {
        if ((j - i) > 1) {
            int m = ((j-i) / 2) + i;
            int someMin = Math.min(Math.min(arr[i],arr[m]),arr[j]);
            if (someMin == arr[i]) {
                if (isMin(i)) {
                    return i;
                }
            } else if (someMin == arr[m]) {
                if (isMin(m)) {
                    return m;
                }
            } else {
                if (isMin(j)) {
                    return j;
                }
            }
            if (arr[i] > arr[m]) {
                return findK(i,m);
            } else {
                return findK(m,j);
            }
        }
        return -1;
    }

    public static boolean isMin(int i) {
        System.out.println("here");
        return (i > 0 && arr[i] < arr[i-1] && i < arr.length-2 && arr[i] < arr[i+1]);
    }
}
