package apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleData {
    private static void classic(){
        BufferedReader br = null;

        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                System.out.print("Enter something");
                String input=br.readLine();
                if("q".equals(input)){
                    System.out.println("Exit!");
                    System.exit(0);
                }
                System.out.println("input : "+input);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br !=null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void jdk1_5(){
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.print("Enter something: ");
            String input = sc.nextLine();

            if("q".equals(input)){
                System.out.println("Exit!");
                break;
            }
            System.out.println("input : "+input);
            System.out.println();
        }
        sc.close();
    }

    private static void jdk1_6(){
        while (true){
            System.out.print("Enter something: ");
            String input = System.console().readLine();

            if("q".equals(input)){
                System.out.println("Exit!");
                System.exit(0);
            }
            System.out.println("input : "+input);
            System.out.println();
        }
    }

}
