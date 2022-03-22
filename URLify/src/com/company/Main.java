package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String testInput1 = "Mr John Smith    ";
        String testInput2 = "Sabath Rodriguez  ";
        int realSize1 = 13;
        int realSize2 = 16;
        Main main = new Main();
        System.out.println(main.urlIFY(testInput2, realSize2));
    }

    private String urlIFY(String input, int realSize)
    {
        char[] inputChar = input.toCharArray();
        int spaces = 0;
        for (int i = 0; i < realSize; i++)
        {
            if (input.charAt(i) == ' ')
                spaces++;
        }

        int pointer = realSize-1;

        for (int i = realSize-1; i >= 0; i--)
        {
            if (inputChar[i] == ' ')
            {
                for (int j = pointer; j > i; j--)
                {
                    inputChar[j + (2*spaces)] = inputChar[j];
                    inputChar[j] = ' ';
                }
                spaces -= 1;
                pointer = i-1;
            }
        }

        for (int i = 0; i < inputChar.length; i++)
        {
            if (inputChar[i] == ' ')
            {
                inputChar[i] = '%';
                inputChar[i+1] = '2';
                inputChar[i+2] = '0';
            }
        }

        System.out.println(Arrays.toString(inputChar));
        return "";
    }
}
