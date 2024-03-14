/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteka;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funkcionalnost.help();
        Scanner scan= new Scanner(System.in);
        
        while(true){
            System.out.println("\nUnesi broj ili naziv funkcije (help za pomoc)");
            Funkcionalnost.PozoviFunkciju(scan.next());
        }
        
    }

}
