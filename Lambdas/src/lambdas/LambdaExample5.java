package lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaExample5 {
    public static void main(String[] args) {
        List<String> mesta = Arrays.asList("Praha","Brno","Nymburk","Mladá Boleslav");

        Collections.sort(mesta,( o1, o2) -> o1.compareTo(o2));

        System.out.println("Mesta:");
        /*printMestaWithFilter(mesta, s -> true,s-> System.out.println(s), s -> {
            try{
                s= s.toUpperCase();
            }catch (Exception e){
                e.printStackTrace();
            }
            return s;
        });*/
        printMestaWithFilter(mesta, s -> true,s-> System.out.println(s), wrapperFunction(s -> s.toUpperCase()));
        System.out.println("-----------");

        System.out.println("Kratke mesta:");
        printFilter(mesta, (s) -> !s.contains(" "));
        System.out.println("-----------");

        System.out.println("Mesta zacinajici na P:");
        printFilter(mesta, (s) -> s.startsWith("P"));
    }

    private static void printFilter(List<String> mesta, LambdaExample4.Filter filter) {
        for(String m :mesta){
            if(filter.filter(m)){
                System.out.println(m);
            }
        }
    }

    private static void printMestaWithFilter(List<String> mesta, LambdaExample4.Filter filter,
                                             Consumer<String> consumer, Function<String, String> function) {
        for(String m : mesta){
            if (filter.filter(m)){
                consumer.accept(function.apply(m));
            }
        }
    }

    interface Filter{
        boolean filter(String s);
    }

    private static <T,R> Function<T,R> wrapperFunction(Function<T,R> function){
        return s -> {
            R a=null;
            try{
                a=function.apply(s);
            }catch (Exception e){
                System.err.println("Error v wrapperFunction");
            }
            return a;
        };
    }

    private static <T> Consumer<T> wrapperConsumer(Consumer<T> consumer){
        return s -> {
            try{
                consumer.accept(s);
            }catch (Exception e){
                System.err.println("Error v wrapperConsumer");
            }
        };
    }
}
