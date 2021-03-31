package app;

import app.utils.Osoba;

public class Main {
    public static void main(String[] args) {
        OsobaDao dao=null;
        try{
            dao=new OsobaDao();
            long id = dao.create(null);
            dao.commit();
        }catch (Exception e){
            if(dao!=null)
                dao.rollBack();
        }finally {
            if(dao!=null)
                dao.disconnect();
        }
    }
}
