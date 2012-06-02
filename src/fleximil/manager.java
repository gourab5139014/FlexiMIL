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

    private void readCK()
    {
        try{
            File inFile = new File(ckdataFile);
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            String datarow=br.readLine(); String dataArray[];
            while(datarow!=null)
            {
                dataArray = datarow.split(",");
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
        
        return count;
    }
    private void run()
    {
        int row=0,col=0;
        try {
            FlexiInstance fi = new FlexiInstance(dataSetFile, permissionsFile);
            this.s = calculateS(fi);
            mil m = new mil();
            row=fi.getNumberOfRows(); col=fi.getNumberofColumns();
            result res = new result(dataSetFile, row, col);
            readColumnPermission();
            readCK();
            m.setS(this.s);

            for(int i=0;i<c.size();i++) //for all c,k values
            {
                m.setC(c.get(i)); m.setK(k.get(i));
                for(int j=0;j<col-1;j++) //not the class column
                {
                    if(toProcess.get(j)==1) { //run mil
                        res.setColumn(j,m.run(fi.getByIndex(j)));
                    }else{
                          res.setColumn(j,fi.getByIndex(i));
                    }
                }
                res.setClassColumn(fi.getClassColumn());
                res.write();
            }
        }catch(Exception ioe) {System.err.println("Inside manager.run() :"+ioe.getLocalizedMessage());}
       
    }
}
