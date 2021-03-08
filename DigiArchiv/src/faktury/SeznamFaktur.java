package faktury;

import faktury.Faktura.fakturaById;
import faktury.Faktura.fakturaBySize;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Adrian
 */

public class SeznamFaktur {
    /**
        * trida pro praci se soubory, ListArray, SeznamFaktur - plní požadavky uživatelského rozhraní
        * Atribut faktury je ArrayList faktury, které jsou v databazi
        * faktura je objekt třídy Faktura, cestaA je string zadaneho souboru, cestaB je string zadaneho adresare, prip je jenom přípona
        * Všechny atributy jsou privátní - přistupuji k nim pouze ve tříde SeznamFaktur
    */
    private final List<Faktura> faktury;//list Faktur
    private Faktura faktura; //objekt faktura
    private String cestaA="";//soubor.csv
    private String cestaB="";//Adresar
    
    private SeznamFaktur(List<Faktura> faktury,String a,String b) {
        this.faktury = faktury;
        this.cestaA=a;
        this.cestaB=b;
    }
    
    /**
    * vrací SeznamFaktur a bere string
    * {@code getInstance} nacte ze souboru do ArrayListu
     * @param soubor - soubor s fakturami
     * @param cesta - cesta k adresari
     * @return SeznamFaktur
     * @throws java.io.IOException
    */
    public static SeznamFaktur getInstance(String soubor,String cesta) throws IOException {
        File a;
        a = new File(soubor);
        ArrayList<Faktura> d=new ArrayList<>();
        try(BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(a)))){
            String line;
            while ((line=b.readLine()) != null) {
                String[] result = line.split(";");
                d.add(new Faktura(Integer.parseInt(result[0]),result[1],result[2],result[3],result[4],result[5],Integer.parseInt(result[6])));
            }
        }
        SeznamFaktur list=new SeznamFaktur(d,soubor,cesta);
        return list;
    }
    //předavá uživateli String s fakturami
    /**
    * vrací string a nic neber
    * {@code vypis} vypise ArrayList
     * @return vrací list faktur ve Stringu
    */
    public String vypis(){
        String vys="";
            for(int i=0;i<faktury.size();i++){
                vys+=faktury.get(i).getString()+"\n";
            }
        return vys;
    }
    //Zapisuje zmenu v seznamu faktur
    /**
    * nic nevrací nic neber
    * {@code save} pouze zapise z ArrayListu do souboru
     * @throws java.io.FileNotFoundException
    */
    public void save() throws FileNotFoundException, IOException {
        String vys="";
        for (int i = 0; i < faktury.size(); i++) {
            vys+=faktury.get(i).getIdFaktury()+";"+faktury.get(i).getNazevFaktury()+";"+faktury.get(i).getCestaFaktury()+"\n";
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(cestaA))){
            bw.write(vys);
            }
        }
    //vyhleda fakturu podle nazvu pomoci equals
    /**
    * vrací Fakturu a bere String
    * {@code podleID} porovna nazvy faktur
     * @param b - jmkeno hledane faktury
     * @return vrací fakturu
    */
    public Faktura podleNazvu(String b){
        Faktura f=null;
        for(int i=0;i<faktury.size();i++){
            if((b.equals(faktury.get(i).getNazevFaktury()))==true){
                f=faktury.get(i);
            }
        }
        return f;
    }
    //vyhleda fakturu podle ID pomoci equals
    /**
    * vrací Fakturu  a bere int
    * {@code podleID} porovna id faktur
     * @param b - id hledane faktury
     * @return vrací fakturu
    */
    public Faktura podleID(int b){
        Faktura fak=null;
        boolean nasel=false;
        for(int i=0;i<faktury.size();i++){
            int a=faktury.get(i).getIdFaktury();
            if(a==b){
                nasel=true;
                fak=faktury.get(i);
            }
        }
        return fak;
    }
    //porovna dve ffaktury bude podle ID nebo Nazvu
    /**
    * Nic nevrací a bere string a int
    * {@code zapis} zapise parametry faktury
     * @param a - výběr podle čeho chci porovnávat
    */
    public void compare(int a){
        switch(a){
            case 1:Collections.sort(faktury,new fakturaById());break;
            case 2:Collections.sort(faktury,new fakturaBySize());break;
            default:break;
        }
        
    }
    //Zapise do faktury
    /**
    * Nic nevrací a bere string a int
    * {@code zapis} zapise parametry faktury
     * @param a - nazev faktury
     * @param b - cesta k souboru PDF
     * @param c - datum vystaveni
     * @param d - datum splatnosti
     * @param e - datum uhrady
     * @param f - cena
     * @throws java.io.IOException
     * 
    */
    public void zapis(String a,String b,String c,String d,String e,int f) throws IOException{
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(cestaA,true))){
            bw.write((faktury.size()+1)+";"+a+";"+b+";"+c+";"+d+";"+e+";"+f+"\n");
            }finally{
                getInstance(cestaA,cestaB);
            }
    }
    //prohleda faktury aby nebyli dve stejne jmena
    /**
    * vrací boolean a bere fakturu kterou má odbrat
    * {@code odeberFakturu} vypise faktury bez te jedne faktury a ulozi jej
     * @param a - zadane jmeno faktury
    * @return  vrací true pokud probehl správně
    */
    public boolean duplikat(String a){
        boolean shoda=false;
        for(int i=0;i<faktury.size();i++){
                if((a.equals(faktury.get(i).getNazevFaktury()))==true){
                    shoda=true;
                    break;
                }else{
                    shoda=false;
                }
            }
        return shoda;
    }
    //odebere fakturu
    /**
    * Nic nevrací a bere fakturu kterou má odbrat
    * {@code odeberFakturu} vypise faktury bez te jedne faktury a ulozi jej
     * @param a - faktura, kterou chci vynechat
     * @throws java.io.IOException
    */
    public void odeberFakturu(Faktura a) throws IOException{
        String vys="";
        for(int i=0;i<faktury.size();i++){
            if(faktury.get(i)!=a){
                vys+=faktury.get(i);
            }
        }
        String soubor=a.getCestaFaktury();
        File s=new File(soubor);
        s.delete();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(cestaA))){
            bw.write(vys);
        }
    }
    //Vytvori soubor PDF do adresar
    /**
    * Vrací boolean a bere String soubor
    * {@code cesta} kontoluje zda soubor exituje a jestli je správného typu
     * @param soubor - cesta k souboru
    * @return  vrací true pokud probehl správně
    */
    public boolean cesta(String soubor){
        boolean shoda = false;
        File a=new File(soubor);
        String ext = getFileExtension(a);
        if(!a.exists()){
                shoda=true;
            }else if(ext.equals("pdf")==false){
                shoda=true;
            }
        
        return shoda;
    }
    //vytvori jmeno souboru
    /**
    * Vrací String a bere String jmeno faktury a cestu k pdf souboru
    * {@code vytvor} zkopíruje složku a přejmenuje
     * @param nazev - jméno faktury
     * @param soubor - cesta k souboru
    * @return  cestu ke slozce
     * @throws java.io.IOException
    */
    public String vytvor(String nazev,String soubor) throws IOException{
        String file=cestaB+"/"+nazev.replace(" ","_")+".pdf";
        Path temp= Files.copy(Paths.get(soubor),Paths.get(file));
        return file;
    }
    //porovna datumy jestli jsou spravne
    /**
    * Vrací boolean a bere String datum
    * {@code datum} kontroluje zda zadný datum je správný
     * @param datVys - datum ve formatu string
    * @return  vrací true pokud je zadany spravne
     * @throws java.text.ParseException
    */
    public boolean datum(String datVys) throws ParseException{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        boolean shoda=false;
        
        Date a=formatter1.parse(datVys);
        String[] result = datVys.split("/");
            if(result.length!=3){
                return false;
            }
            int d=Integer.parseInt(result[0]);
            int m=Integer.parseInt(result[1]);
            int y=Integer.parseInt(result[2]);
            if(m>0 && m<13){
                if(m%2==0){
                    if(m==2){
                        if(d>0 && d<29){
                            shoda = true;
                        }
                    }
                    if(d>0 && d<31){
                            shoda = true;
                        }
                }else{
                    if(d>0 && d<32){
                            shoda = true;
                        }
                }
            }
        return shoda;
    }
    //otevre PDF soubor
    /**
    * Vrací boolean a bere Fakturu kterou má otevřít
    * {@code open} otevře danou fakturu
     * @param a - faktura
    * @return  vrací true pokud probehla
    */
    public boolean open(Faktura a){
        boolean prubeh=false;
        if (Desktop.isDesktopSupported()) {
            try {
                if (a!=null){
                    File myFile = new File(a.getCestaFaktury());
                    Desktop.getDesktop().open(myFile);
                    prubeh=true;
                }
            } catch (IOException e) {
            prubeh= false;
            }
        }
        return prubeh;
    }
    //kontrola jestli jsou vytvorene nejaky soubory
    /**
    * Vrací boolean a bere String
    * {@code check} kontroluje zda slozka a adresar exutuji, pokud ne zalozi ho
     * @param b - adresar
     * @param c - slozka
    * @return  vrací true pokud existuji oba
     * @throws java.io.IOException
    */
    public static boolean check(String b,String c) throws IOException{
        boolean process=true;
        File d=new File(c);
        if(!d.exists() || !d.isFile()){
            d.createNewFile();
            process=false;
        }
        
        File a=new File(b);
        if(!a.exists() || !a.isDirectory()){
            a.mkdir();
        }else if(process==false){
            a.delete();
            a.mkdir();
        }
        return process;
    }
    /**
    * Vrací zmenu localizace a bere int
    * {@code jazyk} podle intu určí výběr jazyka
     * @param i - vyběr jazyka
    * @return  vrací zmenu jazyka
    */
    public Locale jazyk(int i){
        Locale bLocale;
        switch (i){
            case 1:
                bLocale = new Locale.Builder().setLanguage("cz").setRegion("CZ").build();
                break;
            case 2:
                bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
                break;
            default:
                bLocale=null;
        }
        return bLocale;
    }
    /**
    * Vrací stringa a bere File slozku
    * {@code getFileExtension} poskytuje formátovaný vypis
    * @return  methoda vrací string priponu
    */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    /**
    * Vrací boolean a bere string
    * {@code adresar} kontoluje jestli je zadany adresar opravdu adresar
     * @param adresar - cesta k adresari
    * @return  methoda vrací shodu
    */
    public boolean adresar(String adresar){
        boolean okey=false;
        File a=new File(adresar);
        if(a.isDirectory() || !a.exists()){okey=true;}
        return okey;
    }
    /**
    * Vrací boolean a bere string
    * {@code adresar} kontoluje jestli je zadany soubor opravdu soubor
     * @param soubor
    * @return  methoda vrací shodu
    */
    public static boolean soubor(String soubor){
        boolean okey=false;
        File a=new File(soubor);
        if(a.isFile() || !a.exists()){okey=true;}
        return okey;
    }
}