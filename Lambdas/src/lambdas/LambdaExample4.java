package lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExample4 {
    public static void main(String[] args) {
        List<String> mesta = Arrays.asList("Praha","Brno","Nymburk","Mladá Boleslav");

        Collections.sort(mesta, new Comparator<String>(){
            @Override
            public int compare(String o1,String o2){
                return o1.compareTo(o2);
            }
        });

        printMesta(mesta);
        System.out.println("Kratke mesta");
        printFilter(mesta, new Filter() {
            @Override
            public boolean filter(String s) {
                return !s.contains(" ");
            }
        });

        System.out.println("Mesta zacinajici na P");
        printFilter(mesta, new Filter() {
            @Override
            public boolean filter(String s) {
                return s.startsWith("P");
            }
        });
    }

    private static void printFilter(List<String> mesta, Filter filter) {
        for(String m :mesta){
            if(filter.filter(m)){
                System.out.println(m);
            }
        }
    }

    private static void printMesta(List<String> mesta) {
        for(String m : mesta){
            System.out.println(m);
        }
    }

    interface Filter{
        boolean filter(String s);
    }
}
