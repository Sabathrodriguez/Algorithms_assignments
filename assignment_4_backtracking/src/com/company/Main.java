package com.company;

import com.sun.source.tree.Tree;

import java.util.*;

public class Main {
    public static Map<Integer, ArrayList<Integer>> solutionsMap = new TreeMap<>();
    static int counter = 0;
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        //number of lines
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

        int[][][] arr = new int[m+1][m+1][n+1];
        for (int i = 1; i < m+1; i++)
            for (int k = 1; k < m+1; k++)
                for (int j = 1; j < n+1; j++)
                    arr[i][k][j] = -1;

            backtracking(1, 1,1, 0, A, n, C, new ArrayList<Integer>());
        for (Integer i : solutionsMap.keySet()) {
            System.out.println(i + ", " + solutionsMap.get(i));
            break;
        }

    }
        private static void backtracking(int toLane, int fromLane, int step, int cost, int[][] A, int stepMax,
                                        int[] switchCostArray, ArrayList<Integer> solutions) {

            if (step > stepMax) {
                solutionsMap.put(cost, (ArrayList<Integer>) solutions.clone());
                return;
            }

            for (int i = 1; i < A.length; i++) {
                if (i == toLane) {
                    {
                        ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                        solutionsCopy.add(i);
                        backtracking(i, fromLane, step + 1, cost + A[i][step],
                                A, stepMax, switchCostArray, solutionsCopy);

                    }

                }
                else {
                    {
                        ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                        solutionsCopy.add(i);
                        backtracking(i, fromLane, step + 1, cost + A[i][step] + switchCostArray[step - 1],
                                A, stepMax, switchCostArray, solutionsCopy);

                    }

                }
            }
        }
}
