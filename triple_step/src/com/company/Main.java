package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Main main = new Main();
        System.out.println(main.tripleStep(15));
        System.out.println(main.tripleStepDP(15));
    }

    private int tripleStepDP(int n) {
        int a = 1;
        int b = 0;
        int c = 0;

        for (int i = 1; i <= n; i++) {
            int temp = a + b + c;
            c = b;
            b = a;
            a = temp;
        }

        return a;
    }

    private int tripleStep(int n) {
        int[] arr = new int[n+2];
        for (int i = 0; i < arr.length; i++) {arr[i] = -1;}
        return tripleStep(n, arr);
    }

    private int tripleStep(int n, int[] memo) {
        if (n < 0) {return 0;}
        if (n == 0) {return 1;}

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = tripleStep(n-1) + tripleStep(n-2) + tripleStep(n-3);
        return memo[n];
    }
}
