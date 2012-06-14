/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fleximil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gourab
 */
public class result {
    private int c,k;
    private int no_of_rows; int no_of_columns;
    private String datasetName;
    private ArrayList<ArrayList<Object>> data;
    private String resultroot = "."+File.separator+"results";

    public void setC(int c) {
        this.c = c;
    }

    public void setK(int k) {
        this.k = k;
    }

    
    public result(String datasetname,int no_of_rows, int no_of_columns) {
        this.no_of_rows = no_of_rows;
        this.no_of_columns = no_of_columns;
        this.datasetName = datasetname;
        System.out.println("Creating result with name "+datasetname+" r-"+no_of_rows+" c-"+no_of_columns);
        data = new ArrayList<ArrayList<Object>>();
    }
    public void setColumn(int index,Integer arr[]) throws Exception// 0 to no_of_cols-2 indices
    {
        if(index==data.size()-1) { //class column
            throw new Exception("Use the setClassColumn to store the Class Column value");
        }
        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(arr));
        if(index >= 0 && index < data.size()-1) data.remove(index);
        data.add(temp);
    }
    public void setColumn(int index,Double arr[]) throws Exception
    {
        if(index==data.size()-1) { //class column
            throw new Exception("Use the setClassColumn to store the Class Column value");
        }
        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(arr));
        if(index >= 0 && index < data.size()-1) data.remove(index);
        data.add(temp);
    }
    public void setColumn(int index,String arr[]) throws Exception
    {
        if(index==data.size()-1) { //class column
            throw new Exception("Use the setClassColumn to store the Class Column value");
        }
        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(arr));
        if(index >= 0 && index < data.size()-1) data.remove(index);
        data.add(temp);
    }
    public void setClassColumn(String arr[]) //for class column of index no_of_cols-1
    {
        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(arr));
        data.add(data.size(),temp);
    }
    public void write()
    {
        try {
            File outFile;
            BufferedWriter writer;
            new File(resultroot).mkdirs();
            outFile = new File(resultroot + File.separator+datasetName + "_" + c + "_" + k + ".csv");
            
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outFile));
            ArrayList<String> dataRow = new ArrayList<String>();
            String writeLine="";
            /*
             * fetch data from the arraylists and write to files here
             */
            System.out.println("Writing results to ./results PLEASE WAIT!");
            for(int i=0;i<no_of_rows;i++)
            {
                writeLine="";
                dataRow.clear();
                for(ArrayList<Object> a : data)
                {
                    //System.err.println("Trying to cast "+a.get(i));
                    dataRow.add(""+a.get(i));
//                    dataRow.add(Integer.toString((Integer)a.get(i)));
                }
                for(int j=0;j<dataRow.size()-1;j++)
                    writeLine+=(dataRow.get(j)+",");
                writeLine+=dataRow.get(dataRow.size()-1);
                writer.write(writeLine);
                System.err.println("Writing to file : "+writeLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ioe) {
            System.err.println("Inside Result.write() - " + ioe.getLocalizedMessage());
            ioe.printStackTrace();
        }

    }
    
    
}
