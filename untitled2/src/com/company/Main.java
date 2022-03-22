
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
        String[][] maze = new String[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            maze[i] = scanner.nextLine().trim().split("");
        }

        int[] startLoc = findStart(maze);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (maze[i][j].equals(".") && !(aroundStart(j, i, maze)))
                {
                    String temp = maze[i][j];
                    maze[i][j] = "m";
                    collectTreasure(startLoc[0], startLoc[1], maze, 0, j, i);
                    maze[i][j] = temp;
                }
            }
        }
//        collectTreasure(startLoc[0], startLoc[1], maze, 0, 0, 0);
        System.out.println(minY + " " + minX );
        System.out.print(treasure);
    }

    private static void collectTreasure(int startX, int startY, String[][] maze, int localTreasure, int localX, int localY)
    {
        Stack<Integer> x = new Stack<>();
        Stack<Integer> y = new Stack<>();
        x.push(startX);
        y.push(startY);

        List<String> visited = new ArrayList<>();
        //TODO: use tuples to represent points
        // use tuples for stack

        while (!(x.empty()) && !(y.empty()))
        {

            int tempX = x.pop();
            int tempY = y.pop();

            if (! (visited.contains(tempX + ", " + tempY)))
            {
                visited.add("("+tempX + ", " + tempY+")");

                if (! (visited.contains("("+(tempX+1) + ", " + tempY+")")))
                {
                    localTreasure = mazeEquals(maze, tempY, tempX+1,visited, localTreasure, x, y);
//                    if (maze[tempY][tempX + 1].equals("."))
//                    {
//                        if (isMonsterAround(tempX+1,tempY,maze))
//                        {
//                            visited.add((tempX + 1) + ", " + tempY);
//                        } else
//                        {
//                            x.push(tempX + 1);
//                            y.push(tempY);
//                        }
//                    }
//                    else if (maze[tempY][tempX + 1].equals("#"))
//                    {
//                        visited.add((tempX + 1) + ", " + tempY);
//                    }
//                    else if (maze[tempY][tempX + 1].equals("m"))
//                    {
//                        visited.add((tempX + 1) + ", " + tempY);
//                    }
//                    else if (isNumeric(maze[tempY][tempX + 1]))
//                    {
//                        localTreasure += Integer.parseInt(maze[tempY][tempX + 1]);
//                        visited.add((tempX + 1) + ", " + tempY);
//                        if (!isMonsterAround(tempX+1, tempY,maze))
//                        {
//                            x.push(tempX + 1);
//                            y.push(tempY);
//                        }
//                    }
                }

                if (! (visited.contains("("+(tempX-1) + ", " + tempY+")")))
                {
                    localTreasure = mazeEquals(maze, tempY, tempX-1, visited, localTreasure, x, y );
//                    if (maze[tempY][tempX - 1].equals("."))
//                    {
//                        if (isMonsterAround(tempX-1,tempY,maze))
//                        {
//                            visited.add((tempX - 1) + ", " + tempY);
//                        } else
//                        {
//                            x.push(tempX - 1);
//                            y.push(tempY);
//                        }
//                    }
//                    else if (maze[tempY][tempX - 1].equals("#"))
//                    {
//                        visited.add((tempX - 1) + ", " + tempY);
//                    }
//                    else if (maze[tempY][tempX - 1].equals("m"))
//                    {
//                        visited.add((tempX - 1) + ", " + tempY);
//                    }
//                    else if (isNumeric(maze[tempY][tempX - 1]))
//                    {
//
//                        localTreasure += Integer.parseInt(maze[tempY][tempX - 1]);
//                        visited.add((tempX - 1) + ", " + tempY);
//                        if (!isMonsterAround(tempX-1, tempY,maze))
//                        {
//                            x.push(tempX - 1);
//                            y.push(tempY);
//                        }
//                    }
                }

                if (! (visited.contains("("+(tempX) + ", " + (tempY+1)+")")))
                {
                    localTreasure = mazeEquals(maze, tempY+1, tempX, visited,localTreasure, x, y);
//                    if (maze[tempY + 1][tempX].equals("."))
//                    {
//                        if (isMonsterAround(tempX,tempY+1,maze))
//                        {
//                            visited.add((tempX) + ", " + (tempY+1));
//                        } else
//                        {
//                            x.push(tempX);
//                            y.push(tempY + 1);
//                        }
//                    }
//                    else if (maze[tempY + 1][tempX].equals("#"))
//                    {
//                        visited.add((tempX) + ", " + (tempY+1));
//                    }
//                    else if (maze[tempY + 1][tempX].equals("m"))
//                    {
//                        visited.add((tempX) + ", " + (tempY+1));
//                    }
//                    else if (isNumeric(maze[tempY + 1][tempX]))
//                    {
//                        localTreasure += Integer.parseInt(maze[tempY+1][tempX]);
//                        visited.add((tempX) + ", " + (tempY+1));
//
//                        if (!isMonsterAround(tempX, tempY+1,maze))
//                        {
//                            x.push(tempX);
//                            y.push(tempY + 1);
//                        }
//                    }
                }

                if (! (visited.contains("("+(tempX) + ", " + (tempY-1)+")")))
                {
                    localTreasure = mazeEquals(maze, tempY-1,tempX,visited,localTreasure, x, y);
//                    if (maze[tempY - 1][tempX].equals("."))
//                    {
//                        if (isMonsterAround(tempX,tempY-1,maze))
//                        {
//                            visited.add((tempX) + ", " + (tempY-1));
//                        } else
//                        {
//                            x.push(tempX);
//                            y.push(tempY - 1);
//                        }
//                    }
//                    else if (maze[tempY - 1][tempX].equals("#"))
//                    {
//                        visited.add((tempX) + ", " + (tempY-1));
//                    }
//                    else if (maze[tempY - 1][tempX].equals("m"))
//                    {
//                        visited.add((tempX) + ", " + (tempY-1));
//                    }
//                    else if (isNumeric(maze[tempY - 1][tempX]))
//                    {
//                        localTreasure += Integer.parseInt(maze[tempY-1][tempX]);
//                        visited.add((tempX) + ", " + (tempY-1));
//                        if (!isMonsterAround(tempX, tempY-1,maze))
//                        {
//                            x.push(tempX);
//                            y.push(tempY - 1);
//                        }
//                    }
                }
            }
        }
        if (localTreasure < treasure)
        {
            minX = localX;
            minY = localY;
            treasure = localTreasure;
//            System.out.println(visited);
        }
    }

    public static boolean isNumeric(String strNum) {

        return
                (strNum.equals("1")) || (strNum.equals("2"))
                        || (strNum.equals("3")) || (strNum.equals("4"))
                        || (strNum.equals("5")) || (strNum.equals("6"))
                        || (strNum.equals("7")) || (strNum.equals("8"))
                        || (strNum.equals("9"));
    }

    private static boolean isMonsterAround(int x, int y, String[][] maze)
    {
        //check up
        if (maze[y+1][x].equals("m"))
        {
            return true;
        }
        //check down
        else if (maze[y-1][x].equals("m"))
        {
            return true;
        }
        //check left
        else if (maze[y][x-1].equals("m"))
        {
            return true;
        }
        //check right
        else if (maze[y][x+1].equals("m"))
        {
            return true;
        }

        return false;
    }

    private static int[] findStart(String[][] maze)
    {
        for (int i = 1; i < maze.length; i++)
            for (int j = 1; j < maze[i].length; j++)
                if (maze[i][j].equals("p"))
                    return new int[] {j, i};
        return new int[] {};
    }

    private static boolean aroundStart(int x, int y, String[][] maze)
    {
        //check up
        if (maze[y+1][x].equals("p"))
        {
            return true;
        }
        //check down
        else if (maze[y-1][x].equals("p"))
        {
            return true;
        }
        //check left
        else if (maze[y][x-1].equals("p"))
        {
            return true;
        }
        //check right
        else if (maze[y][x+1].equals("p"))
        {
            return true;
        }

        return false;
    }

    private static int mazeEquals(String[][] maze, int tempY, int tempX, List<String> visited, int localTreasure, Stack<Integer> x, Stack<Integer> y)
    {
        if (maze[tempY][tempX].equals("."))
        {
            if (isMonsterAround(tempX,tempY,maze))
            {
                visited.add("("+tempX + ", " + tempY+")");
            } else
            {
                x.push(tempX);
                y.push(tempY);
            }
        }
        else if (maze[tempY][tempX].equals("#"))
        {
            visited.add("("+tempX + ", " + tempY+")");
        }
        else if (maze[tempY][tempX].equals("m"))
        {
            visited.add("("+tempX + ", " + tempY+")");
        }
        else if (isNumeric(maze[tempY][tempX]))
        {
            localTreasure += Integer.parseInt(maze[tempY][tempX]);
            visited.add("("+tempX + ", " + tempY+")");
            if (!isMonsterAround(tempX, tempY,maze))
            {
                x.push(tempX);
                y.push(tempY);
            }
        }
        return  localTreasure;
    }
}