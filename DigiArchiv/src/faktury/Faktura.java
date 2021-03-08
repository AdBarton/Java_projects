/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faktury;
import java.util.Comparator;

/**
 *
 * @author Adrian
 * Třída Faktura zajistuje práci s jednotlivou fakturou
 * metody typu get vrací parametry faktury
 * metody typu set nastavuji parametry faktury
 * metoda getString zajistuje formátovaný výpis výpis
 * metoda toString vypisuje pouze obsah jedne faktury
 * classy fakturaById a fakturaBySize zajistuji pouzene srovnání na základě ID a názvu faktur
 */
public class Faktura {
    private int id;
    private String nazev;
    private String soubor;
    private String datum_vys;
    private String datum_spl;
    private String datum_uhrady;
    private int cena;

    public Faktura(int id, String nazev, String soubor, String dat_vys,String dat_spl, String dat_uhr,int cena) {
        this.id = id;
        this.nazev = nazev;
        this.soubor = soubor;
        this.datum_vys=dat_vys;
        this.datum_spl = dat_spl;
        this.datum_uhrady=dat_uhr;
        this.cena=cena;
    }

    public int getIdFaktury() {
        return id;
    }

    public String getNazevFaktury() {
        return nazev;
    }

    public String getCestaFaktury() {
        return soubor;
    }
    
    public String getDatumVys(){
        return datum_vys;
    }
    public String getDatumSpl(){
        return datum_spl;
    }
    public String getDatumUhr(){
        return datum_uhrady;
    }

    public int getCena(){
        return cena;
    }
    
    public void setNazevFaktury(String nazev) {
        this.nazev = nazev;
    }
    
    public void setDatumVys(String vystaveni){
        this.datum_vys=vystaveni;
    }
    
    public void setDatumSpl(String splatnost){
        this.datum_spl=splatnost;
    }
    
    public void setDatumUhr(String uhrada){
        this.datum_uhrady=uhrada;
    }
    
    public void setCena(int cena){
        this.cena=cena;
    }
    /**
    * Vrací stringa nic neber
    * {@code getString} poskytuje formátovaný vypis
    * @return  methoda vrací fakturu
    */
    public String getString(){
       String a=String.format("%2s %15s %12s %25s %12s %12s %12s",id,nazev,soubor,datum_vys,datum_spl,datum_uhrady,cena);
       return a;
    }
    @Override
    /**
    * Vrací stringa nic neber
    * {@code toString} poskytuje vypis
    * @return  methoda vrací fakturu
    */
    public String toString() {
        String a=id+";"+nazev+";"+soubor+";"+datum_vys+";"+datum_spl+";"+datum_uhrady+";"+cena;
        return a;
    }
    
    static class fakturaById implements Comparator<Faktura>{
        @Override
        public int compare(Faktura a, Faktura b) {
            if(a.getIdFaktury() > b.getIdFaktury()){
                return a.getIdFaktury()-b.getIdFaktury();
            }else{
                return b.getIdFaktury()-a.getIdFaktury();
            }
        }
    }
    
     static class fakturaBySize implements Comparator<Faktura>{

        @Override
        public int compare(Faktura a, Faktura b) {
            
            return a.getNazevFaktury().compareTo(b.getNazevFaktury());
        }
       
    }
}
