package apps;
import Exceptions.*;

import java.util.ArrayList;

public class Exceptions {

    public static double deleni(double a, double b) throws Exception {
        if(b==0){
            throw new Exception("Argument b je 0");
        }
        return a/b;
    }

    public static double deleni2(double a, double b) {
        if(b==0){
            throw new ArithmeticException("Argument b je 0");
        }
        return a/b;
    }

    public static void nullPointer(){
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0;i<3;i++){
            a.add(i,5);
        }
        a.set(1,null);
        a.get(1);
        System.out.println(a.get(1).toString());
    }

    public static void main(String[] args) throws InvalidAgeException {
        String jmeno="Dominik";
        int age=12;

        if (age < 18){
            throw new InvalidAgeException("Nejsi dospely");
        }else {
            System.out.println("Jsi dospely");
        }

        if(jmeno.length() > 3){
            throw new NameLengthException("Jmeno je prilis dlouhe");
        }else{
            System.out.println("Jmeno je OK!");
        }

        try{
            deleni(10.0,0.0);
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        deleni2(10.0,0.0);
        nullPointer();
    }


}
