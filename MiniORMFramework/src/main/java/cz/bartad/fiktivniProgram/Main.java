package cz.bartad.fiktivniProgram;

import cz.bartad.fiktivniProgram.entity.Film;
import cz.bartad.fiktivniProgram.entity.Osoba;
import cz.bartad.miniormframework.database.MiniORMManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        MiniORMManager manager=new MiniORMManager();
        Osoba osoba= manager.getEntityById(0L,Osoba.class);
        Film f= manager.getEntityById(0L,Film.class);
        System.out.println(osoba.getId());
        System.out.println(osoba.getFirt_name());
        System.out.println(osoba.getLast_name());
        System.out.println(osoba.getVek());
        System.out.println(osoba.getAdresa());

        /*
        Film film=new Film();
        film.setName("Home Alone 2");
        film.setReziser("Chris Columbus");
        film.setHlavniPostava("Kevin McCallister");
        manager.insertEntity(film);*/

    }
}
