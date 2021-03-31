package lambdas;

public class ThisReference {
    public void callProcess(int i, Process process){
        process.process(i);
    }

    interface Process{
        void process(int i);
    }

    public static void main(String[] args) {
        ThisReference thisReference=new ThisReference();

        thisReference.callProcess(1, new Process() {
            @Override
            public void process(int i) {
                System.out.println(i);
                System.out.println(this);
            }

            @Override
            public String toString() {
                return "Jsem v Process";
            }
        });
        thisReference.callProcess(1,i -> {
            System.out.println(i);
//            System.out.println(this);
        });
        thisReference.callIt();
    }
    public void callIt(){
        callProcess(1,i -> {
            System.out.println(i);
            System.out.println(this);
        });
    }

    @Override
    public String toString() {
        return "Jsem v ThisReference";
    }
}
