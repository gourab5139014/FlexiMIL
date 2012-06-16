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
        final int maxChoice=9; //Declare as constant

        do{
            System.out.println("\n**\tEnter Choice of DataSet\t**");
            System.out.println("1. Adult");
            System.out.println("2. Glass");
            System.out.println("3. Iris");
            System.out.println("4. Transfusion");
            System.out.println("5. Haberman");
            System.out.println("6. Vertebral");
            System.out.println("7. Ecoli");
            System.out.println("8. Letter Recognition");
            System.out.println("9. TA Evaluation");
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
            case 3:
                m = new manager("." + File.separator + "datasets" + File.separator + "Iris.data", "." + File.separator + "datasets" + File.separator + "ckIris.data", "." + File.separator + "datasets" + File.separator + "Iris.bitset");
                m.setDatasetName("Iris");
                m.run();
                break;
            case 4:
                m = new manager("." + File.separator + "datasets" + File.separator + "Transfusion.csv", "." + File.separator + "datasets" + File.separator + "ckTransfusion.data", "." + File.separator + "datasets" + File.separator + "Transfusion.bitset");
                m.setDatasetName("Transfusion");
                m.run();
                break;
            case 5:
                m = new manager("." + File.separator + "datasets" + File.separator + "Haberman.csv", "." + File.separator + "datasets" + File.separator + "ckHaberman.data", "." + File.separator + "datasets" + File.separator + "Haberman.bitset");
                m.setDatasetName("Haberman");
                m.run();
                break;
            case 6:
                m = new manager("." + File.separator + "datasets" + File.separator + "Vertebral.csv", "." + File.separator + "datasets" + File.separator + "ckVertebral.data", "." + File.separator + "datasets" + File.separator + "Vertebral.bitset");
                m.setDatasetName("Vertebral");
                m.run();
                break;
            case 7:
                m = new manager("." + File.separator + "datasets" + File.separator + "Ecoli.csv", "." + File.separator + "datasets" + File.separator + "ckEcoli.data", "." + File.separator + "datasets" + File.separator + "Ecoli.bitset");
                m.setDatasetName("Ecoli");
                m.run();
                break;
            case 8:
                m = new manager("." + File.separator + "datasets" + File.separator + "Letter.csv", "." + File.separator + "datasets" + File.separator + "ckLetter.data", "." + File.separator + "datasets" + File.separator + "Letter.bitset");
                m.setDatasetName("Letter");
                m.run();
                break;
            case 9:
                m = new manager("." + File.separator + "datasets" + File.separator + "Tae.csv", "." + File.separator + "datasets" + File.separator + "ckTae.data", "." + File.separator + "datasets" + File.separator + "Tae.bitset");
                m.setDatasetName("Tae");
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
