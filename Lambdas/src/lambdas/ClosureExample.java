package lambdas;

public class ClosureExample {
    public static void main(String[] args) {
        int a=10;
        int b=20;
       /* addToIt(a, new Process() {
            @Override
            public void process(int i) {
                b++;
                System.out.println(i+b);
            }
        });*/
        //Lambda zjednodušení
        addToIt(a, i -> System.out.println(i+b));
    }
    static void addToIt(int i, Process process){
        process.process(i);
    }

    interface Process{
        void process(int i);
    }


}

