package lambdas;

public class HelloWord2 {
    public void printHelloWord2(Lambda printHello){
        printHello.print();
    }

    public static void main(String[] args) {
        Lambda printHello = () -> System.out.println("Hello Word");
        HelloWord2 helloWord2= new HelloWord2();
        helloWord2.printHelloWord2(printHello);
    }
}
