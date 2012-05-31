/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

/**
 *
 * @author Gourab
 */
public class mil {
    private int c;
    private int k;
    private int s;
    private String stamp;

    public mil() {
        stamp = "algo300312";
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setS(int s) {
        this.s = s;
    }

    public Integer[] run(Double[] a) //replace this by the actual I-MIL Implementation
    {
        Integer[] dummy= new Integer[a.length]; int i=0;
        for(Double d:a){
            dummy[i]=d.intValue();
            System.out.println(dummy[i]); i++;
        }
        return dummy;
    }
}
