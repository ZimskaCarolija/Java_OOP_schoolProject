/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aleksa
 */
public abstract class Unos  {
    
    public static int UnesiInt() {//ako karakter za unos nije broj vraca -999
        int vrati  =-999;
        Scanner scanner = new Scanner(System.in);
        try
        {
            vrati = scanner.nextInt();
        }
        catch(InputMismatchException ex){
                System.out.println("Morate uneti broj");
                vrati  =-999;
        }
        catch(Exception ex){
                System.out.println(ex.getMessage());
                vrati  =-999;
        }
        finally{
                if(scanner.hasNextLine())
                    scanner.nextLine();
        }
        return vrati;
    }
    
}
