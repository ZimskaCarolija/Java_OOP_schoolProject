/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

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
public class Zaposleni extends Osoba {
    public double Plata;
    public String radnoMesto;

    public Zaposleni(double Plata, String radnoMesto, String ime, String prezime) {//Boris
        super(ime, prezime);
        this.Plata = Plata;
        this.radnoMesto = radnoMesto;
    }

    public double getPlata() {
        return Plata;
    }

    public void setPlata(double Plata) {
        this.Plata = Plata;
    }

    public String getRadnoMesto() {
        return radnoMesto;
    }

    public void setRadnoMesto(String radnoMesto) {
        this.radnoMesto = radnoMesto;
    }

    @Override
    public String toString() {
        return super.toString() + " Plata : " + Plata + ", Radno Mesto : " + radnoMesto;
    }
    public static ArrayList<Zaposleni> UcitajZaposlene()
    {
        ArrayList<Zaposleni> zaposleni = new  ArrayList<Zaposleni>();
        try
        {
             JSONArray nizJ = (JSONArray)new JSONParser().parse(new FileReader("zaposleni.json"));
             for(Object obj : nizJ)
             {
                 JSONObject objJ = (JSONObject)obj;
                String imeJ = objJ.get("ime").toString();
                String prezimeJ = objJ.get("prezime").toString();
                double plataJ = Double.parseDouble(objJ.get("plata").toString());
                 String radnoMestoJ = objJ.get("radnoMesto").toString();
                 Zaposleni zaposlen = new Zaposleni(plataJ,radnoMestoJ,imeJ,prezimeJ);
                 zaposleni.add(zaposlen);
             }
        }
        catch(Exception ex)
        {
            System.out.println("Greska : " + ex.getMessage());
        }
        
        return zaposleni;        
    }
 static void IspisiListu(ArrayList<Zaposleni> ZaposleniL)
    { 
        System.out.println("Zaposleni : \n");
        int i=0;
        for(Zaposleni k : ZaposleniL)
        {
            System.out.println(i+" : "+ k);
            i++;
        }
    }
 static void DodajZaposlenog(ArrayList<Zaposleni> sviZaposleni)
    {
        Boolean dodata = false;
        while(true)
        {
            try
            {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Unesite Ime zaposlenog ");
        String ime;
        ime =  scanner.nextLine();
         System.out.println("Unesite Prezime zaposlenog ");
        String prezime;
        prezime =  scanner.nextLine();      
        System.out.println("Unesite Radno Mesto zaposlenog ");
        String radnoMesto;
        radnoMesto = scanner.nextLine();
        System.out.println("Unesite platu ");
        double plata=0;
        plata = scanner.nextDouble();
        if(ime.trim().length() <1 || prezime.trim().length()<1 || radnoMesto.trim().length()<1)
            throw new ExceptionPraznoPolje();
         Zaposleni z = new Zaposleni(plata,radnoMesto,ime,prezime);    
          int pom = -1;
          do{
                System.out.println("Da li  zelite da dodate novog zaposlenog \n 1-Da  2-Ne");
                pom = Unos.UnesiInt();
          }while(pom!=1 && pom!=2);
               
                if(pom == 1)
                {
                   
                    sviZaposleni.add(z);
                    System.out.println("Novi zaposleni je uspesno dodat");
                    UpisiUJson(sviZaposleni);
                    return;
                }
                else if(pom == 2)
                {
                    System.out.println("Novi zaposleni NIJE dodat");
                    return;
                }
            }
         catch(Exception ex)
         {
             System.out.println("Greska : "+ ex.getMessage() );
         }
        }
    }
    static void UpisiUJson(ArrayList<Zaposleni> sviZaposleni)//Aleksa
    {
        JSONArray listaJ = new JSONArray();
        try
        {
        for(Zaposleni z : sviZaposleni)
        {
            JSONObject objJ = new JSONObject();
            objJ.put("ime",z.getIme());
            objJ.put("prezime",z.getPrezime());
            objJ.put("plata",z.getPlata());
            objJ.put("radnoMesto",z.getRadnoMesto());
            listaJ.add(objJ);
        }
        
        PrintWriter pw = new PrintWriter("zaposleni.json");
        pw.write(listaJ.toJSONString());
        pw.close();
            System.out.println("Uspesno upisani podaci");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage() + " POdaci nisu upisani");
        }
    }        
    
}
