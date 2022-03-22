package com.company;

import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
	// write your code here
        int[] arr = {1,2,3,4,5,6,7,8,9};
        Arrays.sort(arr);


    }
}

class Tree
{
    Node root;

    public Tree(Node root)
    {
        this.root = root;
    }

    public Node getRoot()
    {
        return this.root;
    }
}

class Node
{
    int val;
    Node left;
    Node right;

    public Node(int val)
    {
        this.val = val;
    }

    public void setLeft(Node leftNode)
    {
        this.left = leftNode;
    }

    public void setRight(Node rightNode)
    {
        this.left = rightNode;
    }
}