package collections;

import java.util.*;

public class Sets {

    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        //Set<String> set1=new LinkedHashSet<>();
        //Set<String> set3=new TreeSet<>();

        set.add("Element1");
        set.add("Element2");
        set.add("Element3");
        set.add("Element4");
        set.add("s");

        System.out.println(set.contains("s"));
        set.remove("s");
        Iterator i = set.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }

        Set<String> set2 = new HashSet<>();
        set2.add("Element3");
        set2.add("Element4");
        set2.add("Element5");
        set2.add("Element6");

        System.out.println(set2.containsAll(set));
        set.removeAll(set2);

        printSet(set);
        printSet(set2);
    }

    private static void printSet(Set<String> set){
        System.out.println("Set: ");
        for(String s: set){
            System.out.println(s);
        }
    }

}
