/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

import flexidatasetinstance.FlexiInstance;
import java.io.File;

/**
 *
 * @author Gourab
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
//        File fr = new File("abc");
//        fr.createNewFile();

        FlexiInstance fi = new FlexiInstance("."+File.separator+"datasets"+File.separator+"adult.data");
        fi.run();
//        String c[] = fi.getClassColumn();
//        for(String s : c) System.out.println(s);
        Double a[] = fi.getByIndex(0); int i=0;
        for(Double f:a) System.out.println(f+" count = "+(++i));
        
    }

}