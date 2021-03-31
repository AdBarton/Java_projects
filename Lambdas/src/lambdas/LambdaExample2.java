package lambdas;

public class LambdaExample2 {
    public static void main(String[] args) {
        Example example= (int a, int b) ->{
            if(b==0){
                return 0;
            }
            return a/b;
        };

        double d = example.delni(10,2);
        System.out.println(d);
    }

    interface Example{
        double delni(int x,int y);
    }
}
