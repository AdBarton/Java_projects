package lambdas;

public class LambdaVnoreneTridy {
    public static void main(String[] args) {
        Lambda lambda =() -> System.out.println("Printuju z inner class");

        Lambda lambda2 = new Lambda() {
            @Override
            public void print() {
                System.out.println("Printuju z inner class, lambda 2");
            }
        };

        printIt(lambda);
        printIt(() -> System.out.println("Printuju z inner class"));
        printIt(lambda2);
    }

    public static void printIt(Lambda lambda){
        lambda.print();
    }
}
