/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

import java.io.DataInputStream;
import java.io.File;

/**
 *
 * @author Gourab
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void displayMenu() throws Exception
    {
        int choice=0;
        DataInputStream din = new DataInputStream(System.in);
        final int maxChoice=2; //Declare as constant

        do{
            System.out.println("\n**\tEnter Choice of DataSet\t**");
            System.out.println("1. Adult");
            System.out.println("2. Glass");
            System.out.println("0. Exit");
            choice=Integer.parseInt(din.readLine());
            System.err.println("Choice is "+choice);
        }while(choice<0||choice>maxChoice);
        manager m;
        switch (choice) {
            case 1:
                m = new manager("." + File.separator + "datasets" + File.separator + "adult.data", "." + File.separator + "datasets" + File.separator + "ckadult.data", "." + File.separator + "datasets" + File.separator + "adult.bitset");
                m.setDatasetName("Adult");
                m.run();
                break;
            case 2:
                m = new manager("." + File.separator + "datasets" + File.separator + "glass.csv", "." + File.separator + "datasets" + File.separator + "ckglass.data", "." + File.separator + "datasets" + File.separator + "glass.bitset");
                m.setDatasetName("Glass");
                m.run();
                break;
            default:
                System.out.println("Exiting...");
        }
    }
    public static void main(String[] args)
    {
        try{
            displayMenu();
        }catch(Exception e) {e.printStackTrace();}
        
    }

}
