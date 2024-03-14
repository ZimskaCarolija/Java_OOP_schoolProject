/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Aleksa
 */
public class Provera implements IProvera{

    @Override
    public boolean ProveriDatum(String datum) {
       String niz[] = datum.split(Pattern.quote("."));
       if(niz.length !=3)
       {
           System.out.println("Datum nije u udobrom formatu + " + niz.length);
           return false;
       }
       
       try
       {
           int dan = Integer.parseInt(niz[0]);
           int mesec = Integer.parseInt(niz[1]);
           int godina = Integer.parseInt(niz[2]);
           if(mesec > 12 ||  mesec<1)
           {
               System.out.println("Datum nije u dobrom formatu");
               return false;
           }
           if(godina <0)
           {
               System.out.println("Datum nije u udobrom formatu");
               return false;
           }
           // za dan
                Map<Integer, Integer> recnik = new HashMap<>();
                recnik.put(1, 31);
                
                if(godina %4 == 0)//za prestupnu godinu
                    recnik.put(2, 29);
                else
                 recnik.put(2, 28);   
                
                recnik.put(3, 31);
                recnik.put(4, 30);
                recnik.put(5, 31);
                 recnik.put(6, 30);
            recnik.put(7, 31);
            recnik.put(8, 31);
            recnik.put(9, 30);
            recnik.put(10, 31);
            recnik.put(11, 30);
            recnik.put(12, 31);
            
            if(recnik.get(mesec) < dan || dan<0)
            {
                System.out.println("Datum nije u dobrom foramtu ");
                return false;
            }
            return true;
       }
       catch(Exception ex)
       {
           System.out.println("Greska : "+ex.getMessage());   
       }
       
       return true;
    }
    
}
