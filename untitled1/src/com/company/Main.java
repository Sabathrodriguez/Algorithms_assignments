package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Integer> a = new ArrayList<>();
        int d = 1;
        for (int i = a.size(); i >= 0; i--) {
            Integer temp = a.get(a.size()-1);
            for (int j = a.size(); j >= 0; j--) {
                if (j == 0) {
                    //last element to move
                    a.set(a.size()-1, temp);
                }
                else {
                    int temp2 = a.get(j-1);
                    a.set(j-1, temp);
                    temp = temp2;
                }
            }
        }
    }
}
