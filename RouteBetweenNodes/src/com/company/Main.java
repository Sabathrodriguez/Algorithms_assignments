package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Map<String, Set<String>> G = new TreeMap<>();
        G.put("S", new TreeSet<>());
        G.put("W", new TreeSet<>());
        G.put("U", new TreeSet<>());
        G.put("E", new TreeSet<>());

        G.get("S").add("W");
        G.get("S").add("T");

        G.get("W").add("U");

        G.get("U").add("E");

        G.get("E").add("X");

        DFS("W", "U", G);
    }

    private static void DFS(String startV, String endV, Map<String, Set<String>> G)
    {
        Stack<String> stack = new Stack<>();
        ArrayList<String> visited = new ArrayList<>();
        stack.push(startV);

        while (!stack.empty())
        {
            String node = stack.pop();

            if (node == endV)
            {
                System.out.println("TRUE");
                return;
            }

            if (!visited.contains(node))
            {
                visited.add(node);

                if (G.containsKey(node))
                {
                    for (String str : G.get(node))
                    {
                        stack.push(str);
                    }
                }
            }
        }
        System.out.println("FALSE");
    }
}
