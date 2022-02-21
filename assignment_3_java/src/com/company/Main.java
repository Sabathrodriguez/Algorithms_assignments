package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static long n = 0L;
    static int t = 0;
    static long[] portFromIslands;
    static long s = 1L;
    static long[] collector = new long[2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] in1 = scanner.nextLine().trim().split(" ");
        n = Integer.parseInt(in1[0]);
        t = Integer.parseInt(in1[1]);
        for (int i = 1; i < n+1; i++) {
//            s = s.or(BigInteger.valueOf(1L << i));
            s = s | 1L << i;
        }
        collector = new long[] {n, s};
        portFromIslands = new long[(int)n + 1];
        for (int i = 0; i < t; i++) {
            int[] arr = new int[2];

            String[] in2 = scanner.nextLine().trim().split(" ");
            arr[0] = Integer.parseInt(in2[0]);
            arr[1] = Integer.parseInt(in2[1]);

            portFromIslands[arr[0]] = portFromIslands[arr[0]] | 1L << arr[1];
            portFromIslands[arr[1]] = portFromIslands[arr[1]] | 1L << arr[0];

        }
        findMinShops();
    }

    public static void findMinShops() {
        long checker1 = 1L;
        long checker2 = 1L;
//        Long w = ~Integer.toUnsignedLong(n + 1);
//        System.out.println("s: " + s);
////        int w = n ;
//        System.out.println("w: " + w);
        backtrackingAlgorithm(1, checker1, checker2, 0L);

//        Long[] someArr = collector;

        long someNum = collector[1];
        int[] tempArr = new int[(int)collector[0]];
        int i = 0;
        for (int k = 1; k < n+1; k++) {
            if ((someNum & (1L << k)) != 0L)  {
                tempArr[i] = k;
                i += 1;
            }
        }
        System.out.println(collector[0]);
        for (int j = 0; j < tempArr.length; j++) {
            if (j == (tempArr.length-1)) {
                System.out.print(tempArr[j]);
            } else {
                System.out.print(tempArr[j] + " ");
            }
        }
    }

    public static void backtrackingAlgorithm(int i, long islandsVisitedChecker, long islandsUsedChecker, long num) {
        if (num >= collector[0]) {
            return;
        }
        if (islandsVisitedChecker == s) {
            if (num < collector[0]) {
                collector[0] = num;
                collector[1] = islandsUsedChecker;
            }
            return;
        }
        if (i > n) {
            return;
        }

        long islandsVisitedCheckerCopy = islandsVisitedChecker;
        long islandsUsedCheckerCopy = islandsUsedChecker;

        islandsVisitedChecker = islandsVisitedChecker | portFromIslands[i] | (1L << i);
        islandsUsedChecker = islandsUsedChecker | (1L << i);

        //if islands used/visited has changed
        if (islandsVisitedChecker != islandsVisitedCheckerCopy) {
            backtrackingAlgorithm(i + 1, islandsVisitedChecker, islandsUsedChecker, num + 1L);
        }

        //prune subtrees that cannot be a solution
        //we always have to include the orphan island, we cannot not include this island
        if (portFromIslands[i] != 0L) {
            backtrackingAlgorithm(i + 1, islandsVisitedCheckerCopy, islandsUsedCheckerCopy, num);
        }
    }
}
