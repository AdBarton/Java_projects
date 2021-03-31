import utils.Osoba;
import cz.bartad.miniormframework.database.MiniORMManager;

public class Main {
    public static void main(String[] args) {
        MiniORMManager miniORMManager=new MiniORMManager();
        try{
            miniORMManager.getEntityById(0L, Osoba.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
