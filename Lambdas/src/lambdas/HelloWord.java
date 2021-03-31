package lambdas;

public class HelloWord {

    public void printHelloWord(){
        System.out.println("Hello word");
    }

    public void printeHelloWord2(HelloWordFunctions helloWordFunctions){
        helloWordFunctions.sayHello();
    }

    public static void main(String[] args){
        HelloWord helloWord1=new HelloWord();
        helloWord1.printHelloWord();

        helloWord1.printeHelloWord2(helloWord1.new HelloWordC());
        helloWord1.printeHelloWord2(()-> System.out.println("Hello word Adrian"));

        String name="Adrian";
        //promennaFunkce = () -> {
        //    System.out.println("Hello Word Adrian");
        //}
    }

    class HelloWordC implements HelloWordFunctions {
        @Override
        public void sayHello(){
            System.out.println("Hello Word");
        }
    }
}
