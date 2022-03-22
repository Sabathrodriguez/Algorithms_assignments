package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        //number of lanes
        int m = scanner.nextInt();
        //number of steps
        int n = scanner.nextInt();
        int[][] A = new int[m + 1][n + 1];

        int[] C = new int[n];

        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < A[i].length; j++) {
                if (scanner.hasNextInt())
                    A[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i < C.length; i++) {
            C[i] = scanner.nextInt();
        }

        int[][] DP = new int[m+1][n+1];
        String[][] DP2 = new String[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            DP[i][n] = A[i][n];
            DP2[i][n] = i + "";
        }

        for (int i = n-1; i >= 1; i--) {
            for (int j = m; j >= 1; j--) {
                int min = Integer.MAX_VALUE;
                int minIndex = 999;
                for (int k = m; k >= 1; k--) {

                    if (k == j) {
                        if (DP[k][i + 1] + A[j][i] < min) {
                            minIndex = k;
                            min = DP[k][i + 1] + A[j][i];
                        }
                    }
                    else {
                        if (DP[k][i + 1] + A[j][i] + C[i] < min) {
                            minIndex = k;
                            min =  DP[k][i + 1] + A[j][i] + C[i];
                        }
                    }
                }
                DP[j][i] = min;
                DP2[j][i] = j + "->" + minIndex;
            }
        }

        int minLaneIndex = -1;
        int minLaneValue = Integer.MAX_VALUE;
        for (int i = 1; i <= m; i++) {
            if (DP[i][1] < minLaneValue) {
                minLaneValue = DP[i][1];
                minLaneIndex = i;
            }
        }

//        for (int i = 1; i <= m; i++)
//            System.out.println(Arrays.toString(DP[i]));

        int[] finalArr = new int[n+1];
        int i = minLaneIndex;
        for (int j = 1; j < DP2[i].length; j++) {
            if (DP2[i][j].charAt(0) == DP2[i][j].charAt(DP2[i][j].length()-1)) {
                finalArr[j] = Character.getNumericValue(DP2[i][j].charAt(0));
            } else {
                finalArr[j] = Character.getNumericValue(DP2[i][j].charAt(0));
                i = Character.getNumericValue(DP2[i][j].charAt(DP2[i][j].length()-1));
            }
        }
        int[] lastArr = new int[n];
        System.out.println(minLaneValue);
        System.arraycopy(finalArr, 1, lastArr, 0, lastArr.length);
        System.out.print(Arrays.toString(lastArr).replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim());
    }
}
