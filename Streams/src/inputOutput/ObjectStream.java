package inputOutput;

import Obejcts.Player;

import java.io.*;

public class ObjectStream {
    public static void main(String[] args) throws IOException{
        Player p= new Player("Adrian","LethargicYew",20);

        ObjectInput oi;
        ObjectOutput ou;


        try{
            FileOutputStream fos= new FileOutputStream("resources/object.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.close();
            System.out.println("Done");
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            FileInputStream fin=new FileInputStream("resources/object.txt");
            ObjectInputStream ois= new ObjectInputStream(fin);
            Player pa= (Player)ois.readObject();
            ois.close();
            System.out.println(pa.getName());
            System.out.println(pa.getNickname());
            System.out.println(pa.getAge());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
