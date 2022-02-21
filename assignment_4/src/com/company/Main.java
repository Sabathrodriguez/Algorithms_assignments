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

//        for (int i = 1; i < A.length; i++)
//            for (int k = 1; k < A.length; k++)
//                    System.out.println(Arrays.toString(arr[i][k]));

        for (int i = 1; i < A.length; i++) {
            backtracking(i, i,1, 0, A, n, C, new ArrayList<Integer>(), arr);
        }
//        System.out.println(min);
        for (Integer i : solutionsMap.keySet()) {
            System.out.println(i + ", " + solutionsMap.get(i));
            break;
        }
    }
        private static int backtracking(int toLane, int fromLane, int step, int cost, int[][] A, int stepMax, int[] switchCostArray, ArrayList<Integer> solutions, int[][][] memo) {
            if (step > stepMax) {
                solutionsMap.put(cost, (ArrayList<Integer>) solutions.clone());
                return cost;
            }

            for (int i = 1; i < A.length; i++) {
                System.out.println("from: " + fromLane + ", to: " + i + ", step: " + step);
                //if we stay on the same lane
                if (i == toLane) {
                    if ((step+1) < memo[i][fromLane].length && memo[i][fromLane][step + 1] != -1) {
                        return memo[i][fromLane][step + 1];
                    } else
                    {
                        counter += 1;
                        ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                        solutionsCopy.add(i);
                        int temp1 = backtracking(i, fromLane, step + 1, cost + A[i][step], A, stepMax, switchCostArray, solutionsCopy, memo);
                        if ((step + 1) < memo[i][fromLane].length)
                            memo[i][fromLane][step + 1] = temp1;
                    }

                }
                //if we switch lanes
                else {
//                    if ((step+1) < memo[i][fromLane].length && memo[i][fromLane][step + 1] != -1) {
//                        return memo[i][fromLane][step + 1];
//                    }
//                    if ((step+1) < memo[fromLane][i].length && memo[fromLane][i][step+1] != -1 && memo[fromLane][i][step + 1] != 0) {
//                        System.out.println("1: from: " + fromLane + ", to: " + i + ", step+1: " + (step + 1));
//                        temp2 = memo[fromLane][i][step + 1];
//                        continue;
//                    } else
                    {
                        counter += 1;
                        ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                        solutionsCopy.add(i);
                        int temp2 = backtracking(i, fromLane, step + 1, cost + A[i][step] + switchCostArray[step - 1], A, stepMax, switchCostArray, solutionsCopy, memo);
                        if ((step + 1) < memo[i][fromLane].length)
                            memo[i][fromLane][step + 1] = temp2;
                    }

                }
            }
            return cost;
        }
}
