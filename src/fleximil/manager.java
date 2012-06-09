/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

import flexidatasetinstance.FlexiInstance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Gourab
 */
public class manager {
    private String dataSetFile;
    private String datasetName;
    private String permissionsFile;
    private String ckdataFile;
    private ArrayList<Integer> c;
    private ArrayList<Integer> k;
    private Integer s;
    private ArrayList<Integer> toProcess; //decide whether to process a column or not
    private ArrayList<ArrayList<Object>> data;

    public manager(String dataSetFile,String ckdataFile,String permissionsFile) {
        this.dataSetFile = dataSetFile;
        this.ckdataFile = ckdataFile;
        this.permissionsFile = permissionsFile;
        c = new ArrayList<Integer>();
        k = new ArrayList<Integer>();
        toProcess = new ArrayList<Integer>();
        data = new ArrayList<ArrayList<Object>>();
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    private void readCK()
    {
        try{
            File inFile = new File(ckdataFile);
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            String datarow=br.readLine(); String dataArray[];
            while(datarow!=null)
            {
                dataArray = datarow.split(","); System.out.println("Read c,k - "+dataArray[0]+","+dataArray[1]);
                c.add(Integer.parseInt(dataArray[0]));
                k.add(Integer.parseInt(dataArray[1]));
                datarow = br.readLine();
            }
            br.close();
        }catch(FileNotFoundException fnfe) {System.err.println("Inside readCK() "+fnfe.getLocalizedMessage());}
        catch(IOException ioe) {System.err.println(" Inside readCK() "+ioe.getLocalizedMessage());}

    }

    private void readColumnPermission()
    {
        if(!toProcess.isEmpty()) return;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.permissionsFile));
            String bitrow = reader.readLine();
//            System.err.println("Read permission : " + bitrow + " from " + bitSetFilename);
            for (int i = 0; i < bitrow.length(); i++) {
                toProcess.add(i, Integer.parseInt(bitrow.substring(i, i + 1)));
//                System.out.println("Added permission "+Integer.parseInt(bitrow.substring(i, i + 1))+" for bitset# "+i);
            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Permissions preferences file <dataSetName>.bitSet not Found!");

        } catch (IOException ioe) {
            System.err.println("Inside readColumnPermission() " + ioe.getLocalizedMessage());
        }
    }
    private int calculateS(FlexiInstance fi)
    {
        String classes[]=fi.getClassColumn();
        int count=0;
        //count=2; //<--for adult , INSERT CODE HERE
        ArrayList<String> strs = new ArrayList<String>();
        for(String s:classes){
            if(!strs.contains(s)) strs.add(s);
        }
        count=strs.size();
        System.out.println("No. of Classes - "+count);
        return count;
    }
    public void run()
    {
        int row=0,col=0;
        try {
            FlexiInstance fi = new FlexiInstance(dataSetFile, permissionsFile);
            fi.run();
            this.s = calculateS(fi);
            mil m = new mil();
            row=fi.getNumberOfRows(); col=fi.getNumberofColumns();
            result res = new result(this.datasetName, row, col);
            readColumnPermission();
            readCK();
            m.setS(this.s);
            
            for(int i=0;i<c.size();i++) //for all c,k values
            {
                System.err.println("Calculating for C,K -"+c.get(i)+","+k.get(i));
                m.setC(c.get(i)); m.setK(k.get(i));
                res.setC(c.get(i)); res.setK(k.get(i));
                try{
                for(int j=0;j<col-1;j++) //not the class column
                {
                    try{
                    if(toProcess.get(j)==1) { //run mil
                        Double d[]=fi.getByIndex(j);
                        Integer in[] = m.run(d);
                        res.setColumn(j,in);
                    }else{
                        String d2[] = fi.getByIndexString(j);
                        res.setColumn(j,d2);
                    }
                    }catch(Exception e) {System.err.println("Inside innermost loop of manager.run()- "); e.printStackTrace();}
                }
                } catch(Exception e) {System.err.println("Inside manager.run() innerloop "+e.getLocalizedMessage());}
                res.setClassColumn(fi.getClassColumn());
                res.write();
            }
        }catch(Exception ioe) {System.err.println("Inside manager.run() :"+ioe.getLocalizedMessage());
        ioe.printStackTrace();}
       
    }
}
