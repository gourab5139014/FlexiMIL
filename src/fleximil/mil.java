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

    public int getC() {
        return c;
    }

    public int getK() {
        return k;
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

//    public Integer[] run(Double[] a) //replace this by the actual I-MIL Implementation
//    {
//        Integer[] dummy= new Integer[a.length]; int i=0;
//        for(Double d:a){
//            dummy[i]=d.intValue();
//            //System.out.println(dummy[i]);
//            i++;
//        }
//        return dummy;
//    }
    public Integer[] run(Double[] a) { //I-MIL Implementation , Commment out this function and uncomment the MIL Implementation when required
        Integer[] result = new Integer[a.length];
        Double DESC = (Double) 0.2;
        int n;
        n = c * s;
        int[] CTS = new int[n];     //n = no of sub intervals
        int[] merge = new int[n + 1];
        int m;
        m = a.length;
        int TS = m / n;
        Double max = a[0], min = a[0];
        for (int i = 1; i < m; i++) {
            if (a[i] > max) {
                max = a[i];           //max contains max of the array a[],min contains min of the array a[]
            }
            if (a[i] < min) {
                min = a[i];
            }

        }
        //int CTS[] = new int[n];     //n = no of sub intervals
        //System.out.println("The minimum is  " + Double.toString(min) + "  and the maximum is " + Double.toString(max));
        for (int i = 0; i < n; i++) {
            CTS[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            Double z = a[i];
            int j = 0;
            for (j = 0; j < n; j++) {
                Double t = (max - min) / n;
                if (min + t * j <= z) {
                    if (z <= min + t * (j + 1)) {
                        CTS[j]++;
                        break;
                    }         //now CTS contains calculated TS for every interval n
                }
            }
        }
        System.out.println("width = " + Double.toString((max - min) / n));
        System.out.println("CTS Data  ");
        for (int i = 0; i < n; i++) {
            System.out.println(CTS[i]);
        }
        System.out.println("OK till here");
        int i = 0;
        for (i = 0; i <= n; i++) {
            merge[i] = 1;
        }
        int t = 0;
        i = 0;
        int TOT_CTS = 0;
        boolean last_small_merge = false;
        while (i < n) {
            TOT_CTS += CTS[i];
            if (TOT_CTS < TS / k) {
                TS = TS + m / n;
                i++;
                if (i < n) {
                    merge[t]++;             //no of intervals merged + 1
                }
                last_small_merge = true;
            } else {
                if (i < (n - 1) && !last_small_merge && CTS[i + 1] <= (1 + DESC) * CTS[i] && CTS[i + 1] >= (1 - DESC) * CTS[i]) {
                    i++;
                    merge[t]++;             //no of intervals merged + 1
                } else {
                    t++;
                    i++;
                    TOT_CTS = 0;
                    TS = m / n;
                    last_small_merge = false;
                }
            }            //t contains no of final merged intervals
        }

        Double cumm[] = new Double[t + 1];
        Double r = (max - min) / n;
        cumm[0] = min;
        //System.out.println(merge[t]);
        for (i = 1; i <= t; i++) {
            cumm[i] = cumm[i - 1] + r * merge[i - 1];

        }
        for (i = 0; i < m; i++) {
            for (int j = 0; j < t; j++) {
                if (a[i] <= cumm[j + 1]) {

                    if (a[i] >= cumm[j]) {
                        result[i] = j + 1;
                    }
                }
                if (j == (t - 1) && result[i] == null) {
                    result[i] = t;
                }
            }

        }
        return result;
    }
    /*
     * public Integer[] mil(Double[] a)
{
        Integer[] result=new Integer[a.length];
        int n;
        n=c*s;
        int CTS[] = new int[n];     //n = no of sub intervals
        int merge[]=new int[n+1];
        int m;
        m=a.length;
        int TS=m/n;
        Double max=a[0],min=a[0];
        for(int i=1;i<m;i++)
        {
            if(a[i]>max)
                max=a[i];           //max contains max of the array a[],min contains min of the array a[]
            if(a[i]<min)
                min=a[i];
        }
        //int CTS[] = new int[n];     //n = no of sub intervals
        for(int i=0;i<n;i++)
        {
            CTS[i]=0;
        }
        for(int i=0;i<m;i++)
        {
            Double z = a[i];
            int j=0;
            for(j=0;j<n;j++)
            {
                Double t = (max-min)/n;
                if(min+t*j<=z)
                {
                    if(z<=min+t*(j+1))
                        {CTS[j]++;break;}         //now CTS contains calculated TS for every interval n
                }
            }
        }
        int i =0;
    for(i=0;i<=n;i++)
    {
       merge[i]=1;
    }
    int t=0;
    i=0;
    int TOT_CTS=0;
    while(i<n)
    {
        TOT_CTS+=CTS[i];
        if(TOT_CTS<TS/k)
        {
            TS=TS+m/n;
            i++;
            if(i<n)
            merge[t]++;             //no of intervals merged + 1
         }
        else
        {t++;i++;TOT_CTS=0;TS=m/n;}            //t contains no of final merged intervals
    }
    Double cumm[]=new Double[t+1];
    Double r = (max-min)/n;
    cumm[0]=min;
    //System.out.println(merge[t]);
    for(i=1;i<=t;i++)
    {
        cumm[i]=cumm[i-1]+r*merge[i-1];

    }

   for(i=0;i<m;i++)
    {
        for(int j=0;j<t;j++)
        {
            if(a[i]<=cumm[j+1])
                if(a[i]>=cumm[j])
                {
                    result[i]=j + 1;
                }
            if(j==(t-1)&&result[i]==null)
                result[i]=t;
        }
    }
    return result;
    }
     */
}
