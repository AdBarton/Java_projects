package inputOutput;

import java.io.*;

public class CharStream {
    public static  void main(String[] args) throws IOException {
        FileReader in=null;
        FileWriter out=null;

        try {
            in= new FileReader("resources/in.txt");
            out=new FileWriter("resources/out.txt");

            int c;
            while ((c = in.read()) != -1){
                out.write(c);
            }
        }finally {
            if(in!=null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }
}
