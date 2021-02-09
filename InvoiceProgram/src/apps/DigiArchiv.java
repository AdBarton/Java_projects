package apps;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import faktury.SeznamFaktur;

/**
 *
 * @author Adrian
 * DigiArchiv je třída pouze pro komunikaci s uživatelem
 * Vsechny atributy jsou privatni - pouzivam je pouze v teto tride
 * fak je objekt třídy SeznamFaktur
 * {@code cesta1} - je adresar
 * {@code cesta2} je samotna slozka pro zapis
 * Locale a ResourceBundle je pro změnu jazyka na Angličtinu
 * Program pracuje pouze s příponou PDF a také žádné jiné přípony neber, ošetřeno a zachyceno zde v této třídě
 * Většina metod se pouze uživatele ptají na požadavek a oní ho zpracují a pošlou do třídy SeznamFaktur k vykonání
 * Ale zachycení špatných vstupů už probíha zde, např metoda nactiVolu, nactiDatum a nactiCislo
 * {@code nactiDatum} -  šetří pouze že zadaná hodnota bude vždy mít 3 políčka čísel
 */
public class DigiArchiv {
    //data/faktury
    //data/seznam
    //data/ahoj.pdf
    //Uživatelské rozhraní

    public static Scanner sc=new Scanner(System.in);
    private static SeznamFaktur fak=null; 
    private static String cesta1="data/faktury"; //adresar pro ukladani faktur PDF,potrebuji tuto cestu vyuzivat i v jinych metodach, private protoze ji pouzivam pouze v teto tride
    private static String cesta2="data/faktury.csv"; //slozka na ukladani listu faktur, potrebuji tuto cestu vyuzivat i v jinych metodach, private protoze ji pouzivam pouze v teto tride
    private static Locale bLocale = new Locale.Builder().setLanguage("cz").setRegion("CZ").build();
    private static ResourceBundle myResources = ResourceBundle.getBundle("Properties/MessageBundle", bLocale);
    
    public static void main(String[] args)throws IOException, ParseException {
        System.out.println("---DigiArchiv---");
        System.out.println(myResources.getString("hl_nazev"));
        boolean program=true;
        try{
            System.out.println(myResources.getString("cesta1"));
            String cest=sc.next();//data/faktury
            cesta1=cest;
            System.out.println(myResources.getString("cesta2"));
            String cesta=sc.next();//data/seznam
            cesta2=cesta;
            fak.check(cesta1,cesta2);
            kontrola(cesta1,cesta2);
        }catch(IOException e){
            System.out.println(myResources.getString("error"));
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
        while(program){
            menu();
            int volba=nactiVolbu();
            switch(volba){
                case 1:pridejFak();break;
                case 2:odeberFak();break;
                case 3:vypisFaktur();break;
                case 4:prejmenovatFakturu();break;
                case 5:otevriFakturu();break;
                case 6:myResources = ResourceBundle.getBundle("Properties/MessageBundle",bLocale=zmena());break;
                case 7:porovnej();break;
                case 0:program=false;break;
                default:System.out.println(myResources.getString("chyba_Ins"));break;
            }
        }
    }
    //zmena AJ na CZ
    /**
    * vraci Locale a nic neber
    * {@code zmena} vypise moznosti zmeny jazyka a zepta se na zmenu
     * @return vraci jazyk
    */
    public static Locale zmena(){
        System.out.println(myResources.getString("jazyk"));
        System.out.println(myResources.getString("aj"));
        System.out.println(myResources.getString("cz"));
        int volba=nactiVolbu();
        if(fak.jazyk(volba)==null){
            System.out.println(myResources.getString("error"));
            return bLocale;
        }else{return fak.jazyk(volba);}
        
    }
    
    //vypise menu
    /**
    * {@code menu} vypisuje menu
    */
    public static void menu(){//Menu programu
        System.out.println("Menu:");
        System.out.println(myResources.getString("menu_volba1"));
        System.out.println(myResources.getString("menu_volba2"));
        System.out.println(myResources.getString("menu_volba3"));
        System.out.println(myResources.getString("menu_volba4"));
        System.out.println(myResources.getString("menu_volba5"));
        System.out.println(myResources.getString("menu_volba6"));
        System.out.println(myResources.getString("menu_volba7"));
        System.out.println(myResources.getString("menu_volba0"));
    }
    //Kontrola spojeni s adresarem
    /**
    * typu void
    * {@code kontrola} odkazuje se na instanci v SeznamuFaktur
     * @param a - cesta souboru
     * @param b -cesta adresare
     * @throws java.io.IOException
    */
    public static void kontrola(String a,String b) throws IOException{
        fak=fak.getInstance(b,a);
    }
    //vypis faktur
    /**
    * typu void
    * {@code vypisFaktur} vypise formatovany vystup a pote vypise list faktur
     */
    public static void vypisFaktur(){ //vypis faktur
        System.out.println(myResources.getString("vypis"));
        System.out.println(String.format("%1s %15s %13s %12s %12s %12s %12s","Id",myResources.getString("vn"),
                            myResources.getString("vs"),myResources.getString("vdv"),
                            myResources.getString("vds"),myResources.getString("vdu"),
                            myResources.getString("vc")));
        System.out.println(fak.vypis());
    }
    //pridani faktury
    /**
    * typu void
    * {@code pridejFak} nacita od uzivaele vstupy
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static void pridejFak() throws IOException, ParseException{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        //Nacteni jmena
        System.out.println(myResources.getString("fak_inp"));
        sc.nextLine();
        String nazev =sc.nextLine();
        while(fak.duplikat(nazev)==true){
            System.out.println(myResources.getString("shoda"));
            nazev=sc.nextLine();
        }
        System.out.println(myResources.getString("okey"));
        
        
        //Nacteni cesty k fakture
        String soubor="Žádny soubor";
        System.out.println(myResources.getString("cesta"));
        soubor=sc.next();
        while(fak.cesta(soubor)==true){
            System.out.println(myResources.getString("nexist"));
            System.out.println(myResources.getString("try"));
            soubor=sc.next();
        }
        System.out.println(myResources.getString("okey"));
        
        //zadani datumu vystaveni
        System.out.println(myResources.getString("dat_vys"));
        String datVys;
        datVys=nactiDatum();
        Date dateV=formatter1.parse(datVys);
        while(fak.datum(datVys)==false){
            System.out.println(myResources.getString("error"));
            datVys=sc.next();
            dateV=formatter1.parse(datVys);
        }
        System.out.println(myResources.getString("okey"));
        
        //zadani datumu splatnosti
        System.out.println(myResources.getString("dat_spl"));
        String datSpl;
        datSpl = nactiDatum();
        Date dateS=formatter1.parse(datSpl);
        while(fak.datum(datSpl)==false || dateS.before(dateV)){
            System.out.println(myResources.getString("error"));
            datSpl = sc.next();
            dateS=formatter1.parse(datSpl);
        }
        System.out.println(myResources.getString("okey"));
        
        //zadani datumu uhrady
        System.out.println(myResources.getString("dat_uhr"));
        String datUhr=nactiDatum();
        Date dateU=formatter1.parse(datUhr);
        while(fak.datum(datUhr)==false || (dateU.before(dateV) && dateU.after(dateS))){
            System.out.println(myResources.getString("error"));
            datSpl = sc.next();
            dateS=formatter1.parse(datSpl);
        }
        System.out.println(myResources.getString("okey"));
        
        //zadani ceny
        System.out.println(myResources.getString("cena"));
        int cena=nactiCislo();
        while(cena < 1){
            System.out.println(myResources.getString("cena_o"));
            cena=sc.nextInt();
        }
        
        //zapis do faktury.csv
        String file=fak.vytvor(nazev,soubor);
        fak.zapis(nazev,file,datVys,datSpl,datUhr,cena);
        kontrola(cesta1,cesta2);
    }
    //odebrani faktury
    /**
    * typu void
    * {@code odeberFak} Načte od uživatele jakou Fakturu chce odebrat
     * @throws java.io.IOException
     */
    public static void odeberFak() throws IOException{
        if(fak.vypis().length()==0){
            System.out.println(myResources.getString("zadny"));
        }else{
            if(vyhledatOdebrat()==true){
            System.out.println(myResources.getString("n_list"));
            }else{
                System.out.println(myResources.getString("odeber_fak"));
            }
            //Aktualizace nacteneho arraylistu
            kontrola(cesta1,cesta2);
        }
    }
    //prejmenovani faktury
    /**
    * typu void
    * {@code prejmenovatFakturu} Načte od uživatele jakou Fakturu chce prejmenovat
     * @throws java.io.IOException
     */
    public static void prejmenovatFakturu() throws IOException{
        
        if(fak.vypis().length()==0){
            System.out.println(myResources.getString("zadny"));
        }else{
        
        vypisFaktur();
        boolean process=true;
        String a;int b;
        while(process){
            System.out.println(myResources.getString("vyh_volbaN"));
            System.out.println(myResources.getString("vvh_volba1"));
            System.out.println(myResources.getString("vvh_volba2"));
            System.out.println(myResources.getString("vyh_volba0"));
            int volba=nactiVolbu();
            switch(volba){
                case 1:System.out.println(myResources.getString("fak_inp"));
                        sc.nextLine();a=sc.nextLine();
                        if(fak.podleNazvu(a)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            System.out.println(myResources.getString("new_fak"));
                            sc.nextLine();
                            String nazev=sc.nextLine();
                            fak.podleNazvu(a).setNazevFaktury(nazev);
                            fak.save();
                        }
                        process=false;
                        break;
                case 2:System.out.println(myResources.getString("sou_inp"));
                        b=sc.nextInt();
                        if(fak.podleID(b)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            System.out.println(myResources.getString("new_fak"));
                            sc.nextLine();
                            String nazev=sc.nextLine();
                            fak.podleID(b).setNazevFaktury(nazev);
                            fak.save();
                        };
                        process=false;
                        break;
                case 0:process=false;break;
                default:System.out.println(myResources.getString("chyba_Ins"));break;
            }
        }
        }
    }
    //otevreni faktury
    /**
    * typu void
    * {@code otevriFakturu} Načte od uživatele jakou Fakturu chce otevrit
     */
    public static void otevriFakturu(){
        if(fak.vypis().length()==0){
            System.out.println(myResources.getString("zadny"));
        }else{
        boolean process=true;
        String a;int b;
        while(process){
            System.out.println(myResources.getString("vyh_volbaN"));
            System.out.println(myResources.getString("vvh_volba1"));
            System.out.println(myResources.getString("vvh_volba2"));
            System.out.println(myResources.getString("vyh_volba0"));
            int volba=nactiVolbu();
            switch(volba){
                case 1:System.out.println(myResources.getString("fak_inp"));
                        sc.nextLine();a=sc.nextLine();
                        if(fak.podleNazvu(a)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            if(fak.open(fak.podleNazvu(a))==false){
                            System.out.println(myResources.getString("error"));
                            }
                        }
                        process=false;
                        break;
                case 2:System.out.println(myResources.getString("sou_inp"));
                        b=sc.nextInt();
                        if(fak.podleID(b)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            if(fak.open(fak.podleID(b))==false){
                            System.out.println(myResources.getString("error"));
                            }
                        };
                        process=false;
                        break;
                case 0:process=false;break;
                default:System.out.println(myResources.getString("chyba_Ins"));break;
            }
        }}
    }
    //vyhleda a odeber fakturu
    /**
    * typu boolean
    * {@code vyhledatOdebrat} vyhleda fakturu a odstani ji
     * @return vrací typ treu/false pokud vyhledat a odbral tak true
     * @throws java.io.IOException
     */
    public static boolean vyhledatOdebrat() throws IOException{
        vypisFaktur();
        boolean process=true;
        String a;
        int b;
        boolean odebrano=false;
        while(process){
            System.out.println(myResources.getString("vyh_volbaN"));
            System.out.println(myResources.getString("vvh_volba1"));
            System.out.println(myResources.getString("vvh_volba2"));
            System.out.println(myResources.getString("vyh_volba0"));
            int volba=nactiVolbu();
            switch(volba){
                case 1:System.out.println(myResources.getString("fak_inp"));
                        sc.nextLine();a=sc.nextLine();
                        if(fak.podleNazvu(a)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            fak.odeberFakturu(fak.podleNazvu(a));
                            odebrano=true;
                        }
                        process=false;
                        break;
                case 2:System.out.println(myResources.getString("sou_inp"));
                        b=sc.nextInt();
                        if(fak.podleID(b)==null){
                            System.out.println(myResources.getString("error"));
                        }else{
                            fak.odeberFakturu(fak.podleID(b));
                            odebrano=true;
                        };
                        process=false;
                        break;
                case 0:process=false;break;
                default:System.out.println(myResources.getString("chyba_Ins"));break;
            }
        }
        return odebrano;
    }
    //porovna
    /**
    * typu void
    * {@code porovnej} vypise vyber podle kterého kriteria bude porovnavat a pak posle požadavek do SeznamFaktur
     */
    public static void porovnej(){
        System.out.println(myResources.getString("comp"));
        System.out.println(myResources.getString("comp1"));
        System.out.println(myResources.getString("comp2"));
        System.out.println(myResources.getString("select"));
        fak.compare(sc.nextInt());
    }
    //osetreni nacteni volby
    /**
    * typu void
    * {@code nactiVolbu} Ošetření zadání volby
     */
    private static int nactiVolbu(){
        int volba=-1;
        System.out.println(myResources.getString("nacti_volb"));
        try{
            volba=sc.nextInt();
        }catch(InputMismatchException e){
            volba=-1;
        }
        return volba;
    }
    /**
    * typu int
    * {@code nactiVolbu} Ošetření zadání čísla
     */
    private static int nactiCislo(){
        int cislo=-1;
        try{
            cislo=sc.nextInt();
        }catch(InputMismatchException e){
            cislo=-1;
        }
        return cislo;
    }
    /**
    * typu String
    * {@code nactiVolbu} Ošetření zadání datumu
     */
    private static String nactiDatum(){
        SimpleDateFormat formatter1;
        formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        String dat=sc.next();
        String[] result=dat.split("/");
        boolean cislo=false;
        while(result.length != 3){
            System.out.println(myResources.getString("try"));
            dat=sc.next();
            result=dat.split("/");
        }
        return dat;
    }
    
}
