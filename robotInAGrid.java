class robotInAGrid
{
    public static void main(String[] args)
    {
        String[][] maze =
        {
            {"r",".",".","."},
            {"#","#",".","#"},
            {"#","#",".","#"},
            {"#","#",".","d"}
        };
        
        System.out.println(findPath(maze, 0, 0));
    }
    
    public static boolean findPath(String[][] maze, int row, int col)
    {
//        System.out.println("col: " + col + ", " + row);
        if (maze[row][col].equals("d"))
        {
            return true;
        }
        
        if (row+1 < maze.length && (maze[row+1][col].equals(".") || maze[row+1][col].equals("d")))
            return findPath(maze, row+1, col);
        
        if (col+1 < maze[row].length && (maze[row][col+1].equals(".") || maze[row][col+1].equals("d")))
            return findPath(maze, row, col+1);
        
        return false;
    }
}
