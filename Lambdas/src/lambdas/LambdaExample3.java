package lambdas;

public class LambdaExample3 {
    public static void main(String[] args) {
        Nasob nasobPeti= a -> a*5;
        printNasob(a -> a*5);
    }
    interface Nasob{
        int nasob(int a);
    }

    public static void printNasob(Nasob nasob){
        System.out.println(nasob.nasob(2));
    }
}
