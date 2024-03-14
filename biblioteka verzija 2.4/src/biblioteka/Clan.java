/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

import static biblioteka.Zaposleni.UpisiUJson;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author pc
 */
public class Clan extends Osoba {
   
    private ArrayList<UzetaKnjiga> pozajmljeneKnjige;

    public Clan(ArrayList<UzetaKnjiga> pozajmljeneKnjige, String ime, String prezime) {//Boris
        super(ime, prezime);
        this.pozajmljeneKnjige = pozajmljeneKnjige;
    }


    public ArrayList<UzetaKnjiga> getPozajmljeneKnjige() {//Boris
        return pozajmljeneKnjige;
    }

    public void setPozajmljeneKnjige(ArrayList<UzetaKnjiga> pozajmljeneKnjige) {//Boris
        this.pozajmljeneKnjige = pozajmljeneKnjige;
    }

    @Override
    public String toString() {//Aleksa
        String s = "";
        for(UzetaKnjiga u : pozajmljeneKnjige)
        {
            s+=u.toString();
            s+=" \n";
        }
        return super.toString() + "\n Knjige : " + s;
    }
       public static ArrayList<Clan> UcitajClanove()//Aleksa
    {
        ArrayList<Clan> clanovi = new  ArrayList<Clan>();
        try
        {
             JSONArray nizJ = (JSONArray)new JSONParser().parse(new FileReader("clanovi.json"));
             for(Object obj : nizJ)
             {
                 JSONObject objJ = (JSONObject)obj;
                String imeJ = objJ.get("ime").toString();
                String prezimeJ = objJ.get("prezime").toString();
                
                ArrayList<UzetaKnjiga> knjigeJ = new ArrayList<UzetaKnjiga>();
               
                JSONArray knjigeUzete = (JSONArray)objJ.get("knjige");
                for(Object objUzeto : knjigeUzete)
                {
                  JSONObject objU = (JSONObject)objUzeto;
                  
                  JSONObject objK = (JSONObject)objU.get("knjiga");
                  String nazivJ = objK.get("naziv").toString();
                  String autorJ = objK.get("autor").toString();
                  int kolicinaJ = Integer.parseInt(objK.get("kolicina").toString());
                  Knjiga pomK = new Knjiga(nazivJ,autorJ,kolicinaJ );
                  
                  String datumUzetoJ = objU.get("datumUzimanja").toString();
                  String datumVracanjaJ = objU.get("datumVracanja").toString();
                  Boolean vracenJ = Boolean.parseBoolean(objU.get("vraceno").toString());
                  
                  UzetaKnjiga pomU = new UzetaKnjiga(pomK,datumUzetoJ,datumVracanjaJ,vracenJ);
                  
                  knjigeJ.add(pomU);
                }
                
                
                Clan pomC = new Clan(knjigeJ,imeJ,prezimeJ);
                
                clanovi.add(pomC);
             }
        }
        catch(Exception ex)
        {
            System.out.println("Greska : " + ex.getMessage());
        }
        
        return clanovi;        
    }
      static void IspisiListu(ArrayList<Clan> Clanovi)//Aleksa
    { 
        System.out.println("Clanovi : \n");
        int i=0;
        for(Clan k : Clanovi)
        {
            System.out.println(i+" : "+ k);
            i++;
        }
    }
     
      static void DodajClana(ArrayList<Clan> sviClanovi)//Aleksa
    {
        while(true)
        {
            try
            {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Unesite Ime clana ");
        String ime;
        ime =  scanner.nextLine();
         System.out.println("Unesite Prezime clana ");
        String prezime;
        prezime =  scanner.nextLine();      

        if(ime.trim().length() <1 || prezime.trim().length()<1)
            throw new ExceptionPraznoPolje();
         Clan c = new Clan(new ArrayList<UzetaKnjiga>(),ime,prezime);      
                
               int pom = -1;
               do
               {
                   System.out.println("Da li  zelite da uneste clana \n 1-Da 2-Ne(Nazad)");
                   pom = Unos.UnesiInt();
               }while(pom!=1 && pom!=2);
               // int pom = scanner.nextInt();
                if(pom == 1)
                {
                   
                    sviClanovi.add(c);
                    System.out.println("clan je uspesno dodata");
                    UpisiUJson(sviClanovi);
                    return;
                }
                else if(pom == 2)
                {
                    System.out.println("clan nije dodat");
                    return;
                }
            }
            catch(Exception ex)
            {
             System.out.println("Greska : "+ ex.getMessage() );
                }
        }
    }
     static void UpisiUJson(ArrayList<Clan> sviClanovi)//Aleksa
    {
        JSONArray listaJ = new JSONArray();
        try
        {
        for(Clan c : sviClanovi)
        {
            JSONObject objJ = new JSONObject();
            objJ.put("ime",c.getIme());
            objJ.put("prezime",c.getPrezime());
            JSONArray listaUZetihJ = new JSONArray();
            for(UzetaKnjiga k : c.getPozajmljeneKnjige())
            {
              Knjiga pomK = k.getKnjiga();
              JSONObject objKJ = new JSONObject();
              objKJ.put("naziv",pomK.getNaziv());
              objKJ.put("autor", pomK.getAutor());
              objKJ.put("kolicina", pomK.getKolicina());
              JSONObject objUKJ = new JSONObject();
              objUKJ.put("knjiga", objKJ);
              objUKJ.put("datumUzimanja", k.getDatumUzimanja());
              objUKJ.put("datumVracanja", k.getDatumVracanja());
              objUKJ.put("vraceno", k.isVraceno());
              listaUZetihJ.add(objUKJ);
            }
            objJ.put("knjige", listaUZetihJ);
            listaJ.add(objJ);
        }
        
        PrintWriter pw = new PrintWriter("clanovi.json");
        pw.write(listaJ.toJSONString());
        pw.close();
            System.out.println("Uspesno upisani podaci");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage() + " POdaci nisu upisani");
        }
    }  
     void UbaciKnjigu(UzetaKnjiga  uzeta)
     {
        pozajmljeneKnjige.add(uzeta);
     }
     
    static void DodajKnjiguZaClana(ArrayList<Clan> sviClanovi, ArrayList<Knjiga> sveKnjige)
    {
        Scanner scanner = new Scanner(System.in);
        //Clan.IspisiListu(sviClanovi);
        Clan.ispisiClanoveNevraceneKnjige(sviClanovi);
        int indexClana = 0;
        do
        {
             System.out.println("Unesite redni broj clana da bi ste ga selektovali  za izlaz unesite -1: ");
            // indexClana = scanner.nextInt();
            indexClana = Unos.UnesiInt();
             if(indexClana == -1)
                 return;
             if(indexClana<0 || indexClana>=sviClanovi.size())
             {
                 System.out.println("Niste selektovali clana!");
             }
        }while(indexClana<0 || indexClana>=sviClanovi.size());
        if(sviClanovi.get(indexClana)==null)
        {
            System.out.println("Greska!!!");
            return;
        }
        Knjiga.IspisiListu(sveKnjige);
        Knjiga izabranaKnjiga = null;
        int indexKnjige = 0;
        boolean postojiKnjiga = true;
        do
        {
             System.out.println("Unesite redni broj knjige da bi ste je selektovali  za izlaz unesite -1: ");
             //indexKnjige = scanner.nextInt();
             indexKnjige = Unos.UnesiInt();
             if(indexKnjige == -1)
                 return;
             if(indexKnjige<0 || indexKnjige>=sveKnjige.size())
             {
                 System.out.println("Niste selektovali knjigu!");
             }
        }while(indexKnjige<0 || indexKnjige>=sveKnjige.size() || !postojiKnjiga);
        if(sveKnjige.get(indexKnjige)!=null)
        izabranaKnjiga = sveKnjige.get(indexKnjige);
        else
        {
            System.out.println("Greska!!!");
            return;
        }
        
        //dodavanje datumai i provera
       boolean dobarDatum = false;
       String datumVracanja;
       String datumUzimanja;
       do{
        System.out.println("Unesite datum uzimanja : ");
         /*if(scanner.hasNext())
            scanner.next();*/
         datumUzimanja = scanner.next();
        System.out.println("Unesite rok vracanja : ");
         /*if(scanner.hasNext())
            scanner.next();*/
        datumVracanja = scanner.next();
        Provera provera = new Provera();
        if(!provera.ProveriDatum(datumVracanja) || !provera.ProveriDatum(datumUzimanja))
                dobarDatum = false;
        else
            dobarDatum  =true;
        
       }while(!dobarDatum);
        //nastavak
        int dodavanje = -1;
        do{
             System.out.println("Da li zelite da za clana : " + sviClanovi.get(indexClana).getIme() + " "+sviClanovi.get(indexClana).getPrezime() +" Dodate knjigu : "+ izabranaKnjiga.getNaziv() +" 1-Da  2-Ne(Nazad)");
        dodavanje = Unos.UnesiInt();
        }while(dodavanje!= 1 && dodavanje!=2);
        if(dodavanje == 1)
        {
            if(sveKnjige.get(indexKnjige).getKolicina()>0){
            UzetaKnjiga uz = new   UzetaKnjiga(izabranaKnjiga,datumUzimanja,datumVracanja,false);
            sviClanovi.get(indexClana).UbaciKnjigu(uz);
            UpisiUJson(sviClanovi);
            
            int kolicinaKnjige=sveKnjige.get(indexKnjige).getKolicina()-1;
            sveKnjige.get(indexKnjige).setKolicina(kolicinaKnjige);
            Knjiga.UpisiUJson(sveKnjige);
            
            System.out.println("Uspesno ste dodali knjigu clanu");
            }
            else{
                System.out.println("Nazalost ova knjige nema na stanju");
            }
        }
        return;
    }
    
    
    static void ispisiNeVraceneKnjige(ArrayList<Clan> sviClanovi, int indeks){
        int br=0;
        Scanner scan = new Scanner(System.in);
        boolean ima=false;
        do{
            if(indeks<0 || indeks>=sviClanovi.size()){
                System.out.println("Niste selektovali clana\nOdaberite za kog clana zelite da vidite nevracene knjige");
                ima=false;
                indeks=scan.nextInt();
            }
            else{
                ima=true;
            }
        }while(ima==false);
        if(sviClanovi.get(indeks).getPozajmljeneKnjige().isEmpty())
            System.out.println("Ovaj član nema nijednu knjigu koju je uzeo a nije vratio");
        for(int i=0; i<sviClanovi.get(indeks).getPozajmljeneKnjige().size();i++){
            if(!sviClanovi.get(indeks).getPozajmljeneKnjige().get(i).isVraceno()){
                System.out.println(br+ " : " +sviClanovi.get(indeks).getPozajmljeneKnjige().get(i));
                br++;
            }
            
        }
    }
    static void ispisiClanoveNevraceneKnjige(ArrayList<Clan> sviClanovi){
        System.out.println("\n");
        
        for(int i=0; i<sviClanovi.size();i++){
            System.out.println(i+" : "+sviClanovi.get(i).getIme() + " " + sviClanovi.get(i).getPrezime());
            for(int j=0; j<sviClanovi.get(i).pozajmljeneKnjige.size(); j++){
                if(!sviClanovi.get(i).pozajmljeneKnjige.get(j).isVraceno()){
                    System.out.println("     "+sviClanovi.get(i).pozajmljeneKnjige.get(j));
                }
            }
            
        }
        System.out.println("\n");
    }
  
    static void vratiKnjigu(ArrayList<Clan> sviClanovi, ArrayList<Knjiga> sveKnjige){
        Scanner scanner = new Scanner(System.in);
        //Clan.IspisiListu(sviClanovi);
        Clan.ispisiClanoveNevraceneKnjige(sviClanovi);
        int indexClana = 0;
        do
        {
             System.out.println("Unesite redni broj clana da bi ste ga selektovali  za izlaz unesite -1: ");
             //indexClana = scanner.nextInt();
             indexClana = Unos.UnesiInt();
             if(indexClana == -1)
                 return;
             if(indexClana<0 || indexClana>=sviClanovi.size())
             {
                 System.out.println("Niste selektovali clana!");
             }
        }while(indexClana<0 || indexClana>=sviClanovi.size());
        if(sviClanovi.get(indexClana)==null)
        {
            System.out.println("Greska!!!");
            return;
        }
        System.out.println("Knjige koje je pozajmio "+sviClanovi.get(indexClana).getIme()+" "+sviClanovi.get(indexClana).getPrezime()+":");
        ispisiNeVraceneKnjige(sviClanovi,indexClana);
        
        int br=0;
        ArrayList<UzetaKnjiga> uzeteKnjige= new ArrayList<>();
        ArrayList<Integer> indeksiUListi= new ArrayList<>();
        for(int i=0; i<sviClanovi.get(indexClana).getPozajmljeneKnjige().size();i++){
            if(!sviClanovi.get(indexClana).getPozajmljeneKnjige().get(i).isVraceno()){
                uzeteKnjige.add(sviClanovi.get(indexClana).getPozajmljeneKnjige().get(i));
                indeksiUListi.add(i);
                br++;
            }
            
        }
        if(uzeteKnjige.isEmpty())
            return;
        
        
        
        UzetaKnjiga izabranaKnjiga = null;
        int indexKnjige = 0;
        boolean postojiKnjiga = true;
        do
        {
             System.out.println("Unesite redni broj knjige da bi ste je selektovali  za izlaz unesite -1: ");
             //indexKnjige = scanner.nextInt();
             indexKnjige = Unos.UnesiInt();
             if(indexKnjige == -1)
                 return;
             if(indexKnjige<0 || indexKnjige>=uzeteKnjige.size())
             {
                 System.out.println("Niste selektovali knjigu!");
             }
        }while(indexKnjige<0 || indexKnjige>=uzeteKnjige.size() || !postojiKnjiga);
        
        if(uzeteKnjige.get(indexKnjige)!=null)
        izabranaKnjiga = uzeteKnjige.get(indexKnjige);
        else
        {
            System.out.println("Greska!!!");
            return;
        }
        int odabir = -1;
        do{
             System.out.println("Da li zelite da za clana : " + sviClanovi.get(indexClana).getIme() + " "+sviClanovi.get(indexClana).getPrezime() +" Vratite knjigu : "+ izabranaKnjiga.getKnjiga().getNaziv() +" 1-Da  2-Ne");
        odabir = Unos.UnesiInt();
        }while(odabir!= 1 && odabir!=2);
        
        if(odabir==1){
            sviClanovi.get(indexClana).getPozajmljeneKnjige().get(indeksiUListi.get(indexKnjige)).setVraceno(true);
            UpisiUJson(sviClanovi);
            
           
            int kolicinaKnjige;
            for(int i=0; i<sveKnjige.size();i++){
                if(sveKnjige.get(i).getNaziv().equals(uzeteKnjige.get(indexKnjige).getKnjiga().getNaziv())){
                    kolicinaKnjige=sveKnjige.get(i).getKolicina()+1;
                    sveKnjige.get(i).setKolicina(kolicinaKnjige);
                    break;
                    
                }
            }
            
             Knjiga.UpisiUJson(sveKnjige);
            
        }
        
        
    }
    
    
}
