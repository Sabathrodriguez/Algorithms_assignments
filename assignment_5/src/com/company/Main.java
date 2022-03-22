package com.company;

import java.util.*;

public class Main {

    public static int treasure = 1000000;
    public static int minX = -1;
    public static int minY = -1;
    public static void main(String[] args) {
        // write your code here
//        String[][] maze =
//                {
//                        {"#", "#", "#", "#", "#", "#", "#", "#", "#"},
//                        {"#", ".", "p", ".", ".", "3", ".", ".", "#"},
//                        {"#", ".", ".", ".", ".", ".", ".", ".", "#"},
//                        {"#", ".", "#", "#", "#", "#", ".", ".", "#"},
//                        {"#", "1", "m", "#", ".", "8", ".", ".", "#"},
//                        {"#", "9", ".", ".", ".", ".", ".", ".", "#"},
//                        {"#", "#", "#", "#", "#", "#", "#", "#", "#"}
//                };
//        int rows = 7;
//        int columns = 9;

        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = Integer.parseInt(scanner.nextLine().trim());
        char[][] maze = new char[rows][columns];
        int[] startLoc = new int[2];

        for (int i = 0; i < rows; i++)
        {
            char[] tempArr = scanner.next().trim().toCharArray();

            for (int j = 0; j < columns; j++)
            {
                if (tempArr[j] == 'p')
                {
                    startLoc[0] = j;
                    startLoc[1] = i;
                }

                maze[i][j] = tempArr[j];
            }
        }

        byte[][] visitedB;

        byte[][] visitable = new byte[rows][columns];
        collectTreasure(startLoc[0], startLoc[1], maze, 0, 0, visitable);

        for (int i = 1; i < rows; i++)
        {
            for (int j = 1; j < columns; j++)
            {
                if ((visitable[i][j] != 0) && maze[i][j] == '.' &&
                        !((maze[i+1][j] == 'p') || (maze[i-1][j] == 'p')
                        || (maze[i][j-1] == 'p') || (maze[i][j+1] == 'p')))
                {
                    visitedB = new byte[rows][columns];
                    char temp = maze[i][j];
                    maze[i][j] = 'm';
                    collectTreasure(startLoc[0], startLoc[1], maze, j, i, visitedB);
                    maze[i][j] = temp;
                }
            }
        }

        System.out.println(minY + " " + minX );
        System.out.print(treasure);
    }

    private static void collectTreasure(int startX, int startY, char[][] maze, int localX, int localY, byte[][] visited)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(startX);
        stack.push(startY);

        int localTreasure = 0;

        while (!stack.empty())
        {

            int tempY = stack.pop();
            int tempX = stack.pop();

            visited[tempY][tempX] = 1;

            if (visited[tempY][tempX+1] == 0)
            {
                localTreasure = mazeEquals(maze, tempY, tempX+1,visited, localTreasure, stack);
            }

            if (visited[tempY][tempX-1] == 0)
            {
                localTreasure = mazeEquals(maze, tempY, tempX-1, visited, localTreasure, stack);
            }

            if (visited[tempY+1][tempX] == 0)
            {
                localTreasure = mazeEquals(maze, tempY+1, tempX, visited, localTreasure, stack);
            }

            if (visited[tempY-1][tempX] == 0)
            {
                localTreasure = mazeEquals(maze, tempY-1,tempX,visited, localTreasure, stack);
            }
        }

        if (localTreasure < treasure)
        {
            minX = localX;
            minY = localY;
            treasure = localTreasure;
        }
    }

    private static int mazeEquals(char[][] maze, int tempY, int tempX, byte[][] visited, int localTreasure, Stack<Integer> stack)
    {
        visited[tempY][tempX] = 1;

        if (maze[tempY][tempX] == 'm' || maze[tempY][tempX] == '#')
        {
            return localTreasure;
        }

        if (!((maze[tempY+1][tempX] == 'm') || (maze[tempY-1][tempX] == 'm') ||
            (maze[tempY][tempX-1] == 'm') || (maze[tempY][tempX+1] == 'm')))
        {
            stack.push(tempX);
            stack.push(tempY);
        }

        //if treasure
        if (
            maze[tempY][tempX] == '1' || maze[tempY][tempX] == '2'
            || maze[tempY][tempX] == '3' || maze[tempY][tempX] == '4'
            || maze[tempY][tempX] == '5' || maze[tempY][tempX] == '6'
            || maze[tempY][tempX] == '7' || maze[tempY][tempX] == '8'
            || maze[tempY][tempX] == '9'
            )
        {
            localTreasure += Character.getNumericValue(maze[tempY][tempX]);
        }
        return  localTreasure;
    }
}