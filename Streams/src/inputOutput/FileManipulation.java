package inputOutput;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileManipulation {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("C:\\Users\\Adrian\\Desktop\\ahoj.txt");
        Path path2 = Paths.get("C:\\Users\\Adrian");

        System.out.println("File exists: " + Files.exists(path));
        System.out.println("File notExists" + Files.notExists(path));
        System.out.println("Files isReadable"+Files.isReadable(path));
        System.out.println("Files isHidden" + Files.isHidden(path));
        System.out.println("Files isDirectory" + Files.isDirectory(path));

        System.out.println(Files.isSameFile(path,path2));

        Files.delete(path);
        try{
            Files.delete(path);
        }catch (NoSuchFileException e){
            System.err.println("Dany soubor neexistuje"+path);
        }catch (DirectoryNotEmptyException e){
            System.err.println("Dir neni prazdny"+path);
        }catch (IOException e){
            System.err.println(e);
        }

        System.out.println(Files.deleteIfExists(path2));

        Files.copy(path,path2,StandardCopyOption.REPLACE_EXISTING);

        Files.readAllLines(path);
    }
}
