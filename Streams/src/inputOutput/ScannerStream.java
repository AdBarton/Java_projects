package inputOutput;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ScannerStream {

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(new FileInputStream("resources/text.txt"));
        System.out.println(sc.next());
        if(sc.hasNextInt()){
            int i = sc.nextInt();
            System.out.println();
        }
    }
}
