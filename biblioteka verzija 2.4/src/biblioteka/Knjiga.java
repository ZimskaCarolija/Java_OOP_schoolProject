/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author pc
 */
public class Knjiga {
    private String naziv;
    private String autor;
    private int kolicina;

    public Knjiga(String naziv, String autor, int kolicina) {//Boris
        this.naziv = naziv;
        this.autor = autor;
        this.kolicina = kolicina;
    }

    public Knjiga() {
        
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Knjiga - " + "Naziv : " + naziv + ",  Autor : " + autor + ",  kolicina :" + kolicina;
    }
    
    public static ArrayList<Knjiga> UcitajKnjige()
    {
       
        ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
        try
        {
             JSONArray nizJ = (JSONArray)new JSONParser().parse(new FileReader("knjige.json"));
             for(Object obj : nizJ)
             {
                 JSONObject objJ = (JSONObject)obj;
                 String nazivJ = objJ.get("naziv").toString();
                 String autorJ = objJ.get("autor").toString();
                 int kolicinaJ = Integer.parseInt(objJ.get("kolicina").toString());
                 Knjiga pomK = new Knjiga(nazivJ,autorJ,kolicinaJ );
                 knjige.add(pomK);
                 
             }
        }
        catch(Exception ex)
        {
            System.out.println("Greska : " + ex.getMessage());
        }
        return knjige;
    }
    
    static void IspisiListu(ArrayList<Knjiga> knjige)
    { 
        System.out.println("Knjige : \n");
        int i=0;
        for(Knjiga k : knjige)
        {
            System.out.println(i+" : "+ k);
            i++;
        }
    }
    static void DodajKnjigu(ArrayList<Knjiga> knjige)
    {
        Boolean dodata = false;
        while(true)
        {
            try
            {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Unesite naziv knjige ");
        String naziv;
        naziv =  scanner.nextLine();
         System.out.println("Unesite ime i prezime autora ");
        String autor;
        autor =  scanner.nextLine();
         System.out.println("Unesite kolicinu ");
        int kolicina = 0;
        kolicina = scanner.nextInt();
        if(naziv.trim().length() <1 || autor.trim().length()<1)
            throw new ExceptionPraznoPolje();
         Knjiga k = new Knjiga(naziv,autor,kolicina);
        if(ProveriDaliSadrzi(knjige,k))
            throw new ExceptionVecPosotji();
        int pom =-1;
        do
        {
             System.out.println("Da li  zelite da uneste Zaposlenog \n 1-Da 2-Ne(Nazad)");
             pom = Unos.UnesiInt();
        }while(pom!=1 && pom!=2);
               
                //int pom = scanner.nextInt();
                if(pom == 1)
                {
                   
                    knjige.add(k);
                    System.out.println("Knjiga je uspesno dodata");
                    UpisiUJson(knjige);
                    return;
                }
                else if(pom == 2)
                {
                    System.out.println("Knjiga NIJE dodata");
                    return;
                }
            }
         catch(Exception ex)
         {
             System.out.println("Greska : "+ ex.getMessage() );
         }
        }
    }
    static void UpisiUJson(ArrayList<Knjiga> knjige)
    {
        JSONArray listaJ = new JSONArray();
        try
        {
        for(Knjiga k : knjige)
        {
            JSONObject objJ = new JSONObject();
            objJ.put("naziv",k.naziv);
            objJ.put("autor",k.autor);
            objJ.put("kolicina",k.kolicina);
            listaJ.add(objJ);
        }
        
        PrintWriter pw = new PrintWriter("knjige.json");
        pw.write(listaJ.toJSONString());
        pw.close();
            //System.out.println("Uspesno upisani podaci");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage() + " Podaci nisu upisani");
        }
    }
    static boolean ProveriDaliSadrzi(ArrayList<Knjiga> knjige,Knjiga zaProveru)//ova funkcija prima listu knjiga i iknjigu i i ako knhjiga posotj isa rim anzivom vraca true ako ne psotjio vraca false
    {
        Boolean vraca=  false;
        for(Knjiga k  : knjige)
        {
            if(k.naziv.equals(zaProveru.naziv))
                vraca = true;
        }
        return vraca;
    }
    
    static void PovecajKolicinu(ArrayList<Knjiga> sveKnjige){
        Knjiga.IspisiListu(sveKnjige);
        int indeksKnjige=-1;
        int broj=0;
        Scanner scanner = new Scanner(System.in);
        
         
        while(indeksKnjige<0 || indeksKnjige>=sveKnjige.size()){
            try{
                
                System.out.println("Odaberi knjigu unosom broja knjige: ");
                
                indeksKnjige=scanner.nextInt();
             
                   
                
            }
            catch(InputMismatchException ex){
                System.out.println("Morate uneti broj");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            finally{
                if(scanner.hasNextLine())
                    scanner.nextLine();
            }
        }
        while(broj<=0){
            try{
                System.out.println("Povecaj kolicinu knjige za: ");
                broj=scanner.nextInt();
                if(broj>0){
                    int izbor=1;
                    do{
                        System.out.println("Da li zelite da povecate kolicinu knjige "+ sveKnjige.get(indeksKnjige).naziv+"?  1-DA  2-NE ");
                        izbor=scanner.nextInt();
                    }while(izbor!=1 && izbor!=2);
                    if(izbor==1){
                        sveKnjige.get(indeksKnjige).kolicina+=broj;
                         Knjiga.UpisiUJson(sveKnjige);
                    }
                    
                    
               
                }
                else System.out.println("Morate uneti pozitivan broj");
                
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            finally{
                if(scanner.hasNextLine())
                    scanner.nextLine();
            }
        }
        
       
        
    }
    static void SmanjiKolicinu(ArrayList<Knjiga> sveKnjige){
        Knjiga.IspisiListu(sveKnjige);
        int indeksKnjige=-1;
        int broj=0;
        Scanner scanner = new Scanner(System.in);
        while(indeksKnjige<0 || indeksKnjige>=sveKnjige.size()){
            try{
                System.out.println("Odaberi knjigu unosom broja knjige: ");
                indeksKnjige=scanner.nextInt();
                if(indeksKnjige>0 && indeksKnjige< sveKnjige.size())
                    break;
                
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
               
            }
            finally{
                if(scanner.hasNextLine())
                    scanner.nextLine();
            }
        }
        while(broj<=0 || broj>sveKnjige.get(indeksKnjige).kolicina){
            try{
                System.out.println("Smanji kolicinu knjige za: ");
                broj=scanner.nextInt();
                 if(broj>0){
                     if(broj<sveKnjige.get(indeksKnjige).kolicina){
                        int izbor=1;
                        do{
                            System.out.println("Da li zelite da smanjite kolicinu knjige "+ sveKnjige.get(indeksKnjige).naziv+"?  1-DA  2-NE ");
                            izbor=scanner.nextInt();
                        }while(izbor!=1 && izbor!=2);
                        if(izbor==1){
                            sveKnjige.get(indeksKnjige).kolicina-=broj;
                             Knjiga.UpisiUJson(sveKnjige);
                        }
                     
                     }
                     else System.out.println("Na stanju ima samo "+ sveKnjige.get(indeksKnjige).kolicina+" knjiga");
                    
               
                }
                 else   System.out.println("Morate uneti pozitivan broj");
                
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            finally{
                if(scanner.hasNextLine())
                    scanner.nextLine();
            }
        }
        
        
        
    }
}
