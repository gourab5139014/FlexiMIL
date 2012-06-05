/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

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

        manager m = new manager("."+File.separator+"datasets"+File.separator+"adult.data", "."+File.separator+"datasets"+File.separator+"ckadult.data", "."+File.separator+"datasets"+File.separator+"adult.bitset");
        m.setDatasetName("Adult");
        m.run();
        
    }

}
