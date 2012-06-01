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

        FlexiInstance fi = new FlexiInstance("."+File.separator+"datasets"+File.separator+"Iris.data");
        fi.run();
//        String c[] = fi.getClassColumn();
//        for(String s : c) System.out.println(s);
        Double a[] = fi.getByIndex(0); int i=0;
//        for(Double f:a) System.out.println(f+" count = "+(++i));
        mil m = new mil();
        Integer res[] = m.run(a);
        i=0;
        for(Integer in :res) System.out.println(in+" count = "+(++i));
        
    }

}
