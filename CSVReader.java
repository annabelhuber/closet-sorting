
//CSVReader.java

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Desktop;

public class CSVReader{

    String filename;
    String filepath;


    public CSVReader(String filename){

        this.filename = filename;

        this.filepath = this.filename + ".csv";

        if(!fileExists()){
            this.createNewCSV(this.filepath);
        }
    }


    public boolean fileExists(){
        //checks if a csvfile already exists
        //String filepath = filename + ".csv";
        File csvFile = new File(filepath);

        if (csvFile.exists()){
            return true;
        } else{
            return false;
        }
    }


    public void createNewCSV(String filepath){

        //String file = filepath + ".csv";

        try {

            FileWriter writer = new FileWriter(filepath);

            String[] header = { "Article Type", "Color", "Brand", "Size", "Misc", 
                "Location", "Shirt Length", "Sleeve Length", "Rise", "Length", "Style", "Material"};

            for (String i : header){
            
                writer.write(i);
                writer.write(",");
            }

            //writer.newLine();

            writer.close();


        }
        catch (IOException e) {
            e.printStackTrace(); 
        }


    }


    public String csvName(){

        return filepath;
    }


    public void addShirtCSV(String[] atts){
        //add a shirt using addArticleCSV from info from GUI fields
        //String filepath = csvName(filename);

        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA",};
        s[0] = "SHIRT";

        for (int i = 0; i < atts.length; i++){
            s[i+1] = atts[i];
        }

        this.addArticleCSV(s);
    }



    public void addPantsCSV(String[] atts){
        //add pants using addArticleCSV from info from GUI fields
        //String filepath = csvName(filename);
        
        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA",};
        s[0] = "PANTS";

        for (int i = 0; i < 6; i++){
            s[i+1] = atts[i];
        } for (int j = 6; j < atts.length; j++){
            s[j+3] = atts[j];
        }

        this.addArticleCSV(s);
    }


    public void addArticleCSV(String[] attributes){

        
        //File csvfile = new File(filepath);

        if (!this.checkDuplicates(attributes)){
            System.out.println("Article already exists in " + filename);
        } else {

            String[] att = parseStrings(attributes);

        try (FileWriter writer = new FileWriter(filepath, true)){

            writer.write("\n");

            for (String s : att){
                writer.write(s);
            }
            System.out.println("added data successfully: ");

            writer.close();

            // if (Desktop.isDesktopSupported()) {
            //     Desktop.getDesktop().open(csvfile);
            // } else {
            //     System.out.println("Desktop operations are not supported.");
            // }

        } catch (IOException e) {
            System.out.println("ERR: Could not add data to file: " + filename);
        }
        }

    }


    public boolean checkDuplicates(String[] attributes){
        //read the file and check to see if the article already exists in file
        //returns FALSE if exists, return TRUE if not a duplicate

        // System.out.println("Checking Duplicates");

        //converting given array to one comparable to the ones in the csv
        attributes = parseArrays(attributes);


        // System.out.println("atts: ");
        // System.out.println(Arrays.toString(attributes));
        //System.out.println(attributes);

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;

            //skip first line
            br.readLine();

            while ((line = br.readLine()) != null){
                //read all the lines
                String[] data = line.split(",");
                // System.out.println("data: " + Arrays.toString(data));
                //System.out.println(br.readLine());
                

                if (Arrays.equals(data, attributes)){
                    return false;
                }
            } 
        } catch (IOException e){
            e.printStackTrace();
        } return true;
    }



    public String[] parseArrays(String[] attributes){
        //same as parseStrings but without adding extra commas
        String[] tempVar = new String[12];

        if (attributes[0].equals("SHIRT")){
            for (int i = 0; i < 8; i++){
                tempVar[i] = attributes[i];
            }

            for (int j = 8; j < 12; j++){
                tempVar[j] = "NA";
            }
            
        } else if (attributes[0].equals("PANTS")){
            for (int i = 0; i < 6; i++){
                tempVar[i] = attributes[i];
            }

            for (int j = 6; j < 8; j++){
                tempVar[j] = "NA";
            }

            for (int p = 8; p < 12; p++){
                tempVar[p] = attributes[p - 2];
            }
        }
        return tempVar;
    }



    public static String[] parseStrings(String[] attributes){
        //change a given string to one that fits for Shirts category

        String[] tempVar = new String[12];

        if (attributes[0].equals("SHIRT")){
            for (int i = 0; i < 8; i++){
                tempVar[i] = attributes[i] + ",";
            }

            for (int j = 8; j < 12; j++){
                tempVar[j] = "NA,";
            }
            
        } else if (attributes[0].equals("PANTS")){
            for (int i = 0; i < 6; i++){
                tempVar[i] = attributes[i] + ",";
            }

            for (int j = 6; j < 8; j++){
                tempVar[j] = "NA,";
            }

            for (int p = 8; p < 12; p++){
                tempVar[p] = attributes[p - 2] + ",";
            }
        }
        return tempVar;
    }

    //need a function to change articles
    //need a function to delete articles

    public ArrayList displayArticles(){

        //System.out.println("begin displayArticles");

        Closet c = new Closet();
        //System.out.println("made closet");
        c.readCSV(filename);

        //System.out.println("read CSV");
  
        ArrayList list = c.getAll();
        //System.out.println("got all");

        //System.out.println("begin displayArticles");

        return list;

    }


    public void openCSV(){

        File file = new File(filepath);

        try {
            if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("Desktop operations are not supported.");
            }
        } catch (IOException e) {
            System.out.println("ERR: Could not show file: " + filename);
        }

    }


    public void deleteLine(int index){
        //deletes a line of a CSV
        // file = file;

        File input = new File(filepath);
        File temp = new File("temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(input))){

            FileWriter writer = new FileWriter(temp);

            String currentLine;
            int lineCount = 0;

            //write the header
            writer.write(br.readLine());

            while ((currentLine = br.readLine()) != null) {

                if (lineCount != index){
                    if (!currentLine.isEmpty()){
                        writer.write("\n");
                        writer.write(currentLine);
                        
                        // System.out.println("line count pre: " + lineCount);
                        
                    } 
                    // System.out.println("line count post: " + lineCount);
                // } else {
                //     System.out.println("line count pre ELSE: " + lineCount);
                //     lineCount++;
                //     System.out.println("line count post ELSE: " + lineCount);
                // }
                
                
                } lineCount++;
            }

            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            input.delete();
            temp.renameTo(new File(filepath));

            //this.openCSV();


            // if (Desktop.isDesktopSupported()) {
            //     Desktop.getDesktop().open(input);
            // } else {
            //     System.out.println("Desktop operations are not supported.");
            // }



        }
    





    public static void main(String[] args){

        // createNewCSV("testcloset");
        // // System.out.println(csvName("testcloset"));
        String[] shirt1 = { "SHIRT", "blue", "aritzia", "M", "tie front", "drawer", "cropped", "tee"};
        String[] shirt2 = {"SHIRT", "gray", "artitzia", "M", "knit", "bag", "long", "long"};
        String[] pant2 = {"PANTS", "blue", "lucky", "28", "none", "Mike's", "low", "long", "flare", "denim"};
        String[] shirt3 = {"SHIRT", "black", "urban outfitters", "M", "lace trim", "drawer", "cropped", "tank"};
        String[] pant3 = {"PANTS", "black", "abercrombie", "28", "90's", "Mike's", "low", "long", "baggy", "denim"};
        String[] pant1 = {"PANTS", "blue", "zara", "6", "ripped", "drawer", "high", "long", "baggy", "denim"};


        CSVReader myCSV = new CSVReader("testing");

        // myCSV.addArticleCSV(pant1);
        // myCSV.addArticleCSV(pant2);
        // myCSV.addArticleCSV(pant3);
        myCSV.addArticleCSV(shirt1);
        // myCSV.addArticleCSV(shirt2);
        // myCSV.addArticleCSV(shirt3);

        //myCSV.deleteLine(3);

        myCSV.openCSV();


        

        //myCSV.deleteLine(0);
        
        // File a = new File("testing.csv");
        // a.delete();

        
    }
}