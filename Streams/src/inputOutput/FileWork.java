package inputOutput;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWork {

    public static void main(String[] args){
        Path path= Paths.get("C:\\Users\\Adrian\\Desktop\\ahoj.txt");

        try(BufferedReader br = Files.newBufferedReader(path)){
            br.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }

        try(Scanner sc = new Scanner(Files.newBufferedReader(path))){
            sc.nextLine();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
