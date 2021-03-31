package cz.bartad.fiktivniProgram.entity;

import cz.bartad.miniormframework.annotation.Identifier;
import cz.bartad.miniormframework.annotation.Table;
import cz.bartad.miniormframework.annotation.TableColumn;

@Table("OSOBA")
public class Osoba {

    @Identifier
    @TableColumn("ID")
    private Long id;

    @TableColumn("NAME")
    private String firt_name;

    @TableColumn("SURNAME")
    private String last_name;

    @TableColumn("AGE")
    private Integer vek;

    @TableColumn("ADDRESS")
    private String adresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirt_name() {
        return firt_name;
    }

    public void setFirt_name(String firt_name) {
        this.firt_name = firt_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getVek() {
        return vek;
    }

    public void setVek(Integer vek) {
        this.vek = vek;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
