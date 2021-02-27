package inputOutput;

import java.io.*;

public class LineStream {

    public static void main(String[] args) throws IOException{
        BufferedReader br=null;
        BufferedWriter bw=null;

        try{
            br = new BufferedReader(new FileReader("resources/in.txt"));
            bw = new BufferedWriter(new FileWriter(new File("resources/out.txt")));

            String line;
            while ((line = br.readLine()) != null){
                bw.write(line);
            }

        } finally {
            if(br != null){
                br.close();
            }
            if(bw != null){
                bw.close();
            }
        }
    }
}
