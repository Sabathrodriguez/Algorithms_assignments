package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(fib_dp(40));
        System.out.println(fib_mem(40));
    }

    //memoization
    public static long fib_mem(long n) {
        return fib_mem(n, new long[(int)(n+2)]);
    }

    public static long fib_mem(long n, long[] memo) {
        if (n == 1 || n == 0) {return n;}

        if (memo[(int)n] != 0) {
            return memo[(int)n];
        }
        memo[(int)n] = fib_mem(n-1, memo) + fib_mem(n-2, memo);
        return memo[(int)n];
    }

    //bottom up
    public static int fib_dp(int k) {
//        int[] arr = new int[2];
//        arr[0] = 0;
//        arr[1] = 1;
        int a = 0;
        int b = 1;

        for (int i = 2; i <= k; i++) {
//            int temp = arr[0] + arr[1];
            int temp = a + b;
            a = b;
            b = temp;
//            arr[0] = arr[1];
//            arr[1] = temp;
        }

        return b;
    }
}
