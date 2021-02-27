package inputOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VarArgs {
    public static void main(String[] args){
        int m=max(1,2,3,15,20,65,90,1587,-10685);
        System.out.println(m);
        int[] a;
    }

    private static int max(int... values){
        int max=Integer.MIN_VALUE;
        for(int i : values){
            if(i>max){
                max=i;
            }
        }
        return max;
    }
}

