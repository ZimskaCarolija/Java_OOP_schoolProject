/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Funkcionalnost {
    static ArrayList<Knjiga> knjige = new ArrayList<>(Knjiga.UcitajKnjige());
    static ArrayList<Zaposleni> zaposleni = new ArrayList<>(Zaposleni.UcitajZaposlene());
    static ArrayList<Clan> clanovi = new ArrayList<>(Clan.UcitajClanove());
    
    public static void help(){
        System.out.println("Unesite broj ili ime komande kako bi je izvrsili. Lista komandi: \n"
                + "1 : DodajKnjigu  (Dodaje se nova knjiga u bazu)\n"
                + "2 : PovecajKolicinu  (Povecava se kolicina odredjene knjige)\n"
                + "3 : SmanjiKolicinu  (Smanjuje se kolicina odredjene knjige)\n"
                + "4 : IspisiKnjige (Ispisuje se lista svih knjiga koje postoje u bazi)\n"
                + "5 : DodajZaposlenog  (Dodaje se novi zaposleni u bazu)\n"
                + "6 : IspisiZaposlene  (Ispisuje se lista svih zaposlenih koji postoje u bazi)\n"
                + "7 : DodajClana  (Dodaje se novi clan biblioteke)\n"
                + "8 : IspisiClanove  (Ispisuje se lista svih clanova koji postoje u bazi)\n"
                + "9 : IspisiClanoveNevraceneListe  (Ispisuje se lista svih članova i knjige koje su uzeli a nisu vratili)\n"
                + "10 : DodajKnjiguZaClana  (Kada clan uzima novu knjigu dodeljuje mu se knjiga)\n"
                + "11 : VratiKnjigu  (Kada clan vraca knjigu koja je bila kod njega)\n"
                + "12 : IspisiNeVraceneKnjige  (Na osnovu odabranog clana ispisuje listu knjiga koje je taj clan uzeo a nije vratio\n"
                + "13 : help  (Pokazuje sve komande i opise komandi)\n"
                + "14 : exitProgram  (izlazak i zatvaranje programa)\n\n");
        
    }
    
    public static void PozoviFunkciju(String nazivFunkcije){
        
        if(nazivFunkcije.equals("DodajKnjigu") || nazivFunkcije.equals("1")){
            Knjiga.DodajKnjigu(knjige);
        }
        else if(nazivFunkcije.equals("PovecajKolicinu") || nazivFunkcije.equals("2")){
            Knjiga.PovecajKolicinu(knjige);
        }
        else if(nazivFunkcije.equals("SmanjiKolicinu") || nazivFunkcije.equals("3") ){
            Knjiga.SmanjiKolicinu(knjige);
        }
        else if(nazivFunkcije.equals("IspisiKnjige") || nazivFunkcije.equals("4") ){
            Knjiga.IspisiListu(knjige);
        }
        else if(nazivFunkcije.equals("DodajZaposlenog") || nazivFunkcije.equals("5") ){
            Zaposleni.DodajZaposlenog(zaposleni);
        }
        else if(nazivFunkcije.equals("IspisiZaposlene") || nazivFunkcije.equals("6") ){
            Zaposleni.IspisiListu(zaposleni);
        }
        else if(nazivFunkcije.equals("DodajClana") || nazivFunkcije.equals("7") ){
            Clan.DodajClana(clanovi);
        }
        else if(nazivFunkcije.equals("IspisiClanove") || nazivFunkcije.equals("8")){
            Clan.IspisiListu(clanovi);
        }
        else if(nazivFunkcije.equals("IspisiClanoveNeVraceneKnjige") || nazivFunkcije.equals("9")){
            Clan.ispisiClanoveNevraceneKnjige(clanovi);
        }
        else if(nazivFunkcije.equals("DodajKnjiguZaClana") || nazivFunkcije.equals("10")){
            Clan.DodajKnjiguZaClana(clanovi,knjige);
        }
         else if(nazivFunkcije.equals("VratiKnjigu") || nazivFunkcije.equals("11")){
            Clan.vratiKnjigu(clanovi,knjige);
        }
        else if(nazivFunkcije.equals("IspisiNeVraceneKnjige") || nazivFunkcije.equals("12")){
            
            Clan.IspisiListu(clanovi);
            
            Scanner scan= new Scanner(System.in);
            int indeks=-1;
            
            while(indeks<0 || indeks>=clanovi.size()){    
            try{
             
                System.out.println("Odaberite za kog clana zelite da vidite nevracene knjige");
                indeks = scan.nextInt();
                
                if(indeks<0 || indeks>=clanovi.size()){
                    System.out.println("Niste selektovali clana");
                }
                   
                
            }
            catch(InputMismatchException ex){
                System.out.println("Niste selektovali clana\nMorate uneti broj");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            finally{
                if(scan.hasNextLine())
                    scan.nextLine();
            }
        }
          
            Clan.ispisiNeVraceneKnjige(clanovi, indeks);
        }
        else if(nazivFunkcije.equals("help") || nazivFunkcije.equals("13")){
            help();
        }
        else if(nazivFunkcije.equals("exitProgram") || nazivFunkcije.equals("14")){
            System.exit(0);
        }
        else{
            System.out.println("Greska, ova komanda ne postoji");
        }
        
        
        
        
        
        
    }
}
