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

        for (int i = 1; i < A.length; i++) {
            backtracking(i, 1, 0, A, n, C, new ArrayList<Integer>());
        }
//        System.out.println(min);
        for (Integer i : solutionsMap.keySet()) {
            System.out.println(i + ", " + solutionsMap.get(i));
            break;
        }
        System.out.println(counter);
    }
        private static int backtracking(int lane, int step, int cost, int[][] A, int stepMax, int[] switchCostArray, ArrayList<Integer> solutions) {
            if (step > stepMax) {
                solutionsMap.put(cost, (ArrayList<Integer>) solutions.clone());
                return cost;
            }

            int temp1 = 100000;
            int temp3 = 100000;
            for (int i = 1; i < A.length; i++) {
                if (i == lane) {
                    counter += 1;
                    ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                    solutionsCopy.add(i);
                    temp1 = backtracking(i, step + 1, cost  + A[i][step], A, stepMax, switchCostArray, solutionsCopy);
                }
                else {
                    counter += 1;
                    ArrayList<Integer> solutionsCopy = (ArrayList<Integer>) solutions.clone();
                    solutionsCopy.add(i);
                    int temp2 = backtracking(i, step + 1, cost + A[i][step] + switchCostArray[step-1], A, stepMax, switchCostArray, solutionsCopy);
                    if (temp2 < temp3) {
                        temp3 = temp2;
                    }
                }
            }
            return Math.min(temp1, temp3);
        }
}
