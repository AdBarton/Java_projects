package lambdas;

import objects.Osoba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewIteration {
    public static void main(String[] args) {
        List<Osoba> osoby= Arrays.asList(
                new Osoba("Adrian","Barton",21),
                new Osoba("Adam","Kynych",12),
                new Osoba("Lukáš","Martinec",15),
                new Osoba("Jiří","Vaina",15)
        );
        /*
        //To same
        for (int i=0;i<osoby.size();i++){
            System.out.println(osoby.get(i).getFirstName());
        }

        for (Osoba o : osoby){
            System.out.println(o.getFirstName());
        }
        */
        osoby.forEach(o -> System.out.println(o.getFirstName()));
        osoby.forEach(o -> System.out.println(o));
    }
}
