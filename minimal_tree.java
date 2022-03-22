class minimal_tree
{
    public static void main(String[] args)
    {
        int[] arr = new int[] {2,4,5,7,8};
        Node tree = createMinimalBST(arr, 0, arr.length-1);
        
        printTree(tree, 0);
    }
    
    public static void printTree(Node node, int level)
    {
        if (node == null)
        {
            System.out.println(level);
            return;
        }
        if (node != null)
        {
            System.out.println(node.val);
        }
        if (node.left != null)
        {
//            System.out.println("  " + node.left.val);
            printTree(node.left, level + 1);
        }
        if (node.right != null)
        {
//            System.out.println("      " + node.right.val);
            printTree(node.right, level + 1);
        }
    }
    
    public static Node createMinimalBST(int[] arr, int beg, int end)
    {
        if (beg > end)
        {
            return null;
        }
        
        int m = (beg+end)/2;
        Node n = new Node(arr[m]);
        n.left = createMinimalBST(arr, beg, m-1);
        n.right = createMinimalBST(arr, m+1, end);
        return n;
    }
}

class Node
{
    int val;
    public Node left;
    public Node right;
    
    public Node(int val)
    {
        this.val = val;
    }
    
    public void setVal(int val)
    {
        this.val = val;
    }
    
    public void setLeft(Node node)
    {
        this.left = node;
    }
    
    public void setRight(Node node)
    {
        this.right = node;
    }
    
    @Override
    public String toString()
    {
        return this.val + "";
    }
}
