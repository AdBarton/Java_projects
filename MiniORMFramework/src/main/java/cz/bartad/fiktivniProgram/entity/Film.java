package cz.bartad.fiktivniProgram.entity;

import cz.bartad.miniormframework.annotation.Identifier;
import cz.bartad.miniormframework.annotation.Table;
import cz.bartad.miniormframework.annotation.TableColumn;

@Table("MOVIE")
public class Film {
    @Identifier
    @TableColumn("ID")
    private Long id;

    @TableColumn("NAZEV_FILMU")
    private String name;

    @TableColumn("REZISER")
    private String reziser;

    @TableColumn("HLAVNI_POSTAVA")
    private String hlavniPostava;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public String getHlavniPostava() {
        return hlavniPostava;
    }

    public void setHlavniPostava(String hlavniPostava) {
        this.hlavniPostava = hlavniPostava;
    }
}
