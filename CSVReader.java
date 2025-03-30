
//CSVReader.java

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Desktop;

public class CSVReader{

    //String filename;


    public CSVReader(String filename){

        String filepath = filename + ".csv";

        if(!fileExists(filepath)){
            this.createNewCSV(filepath);
        }
    }


    public boolean fileExists(String filename){
        //checks if a csvfile already exists
        String filepath = filename + ".csv";
        File csvFile = new File(filepath);

        if (csvFile.exists()){
            return true;
        } else{
            return false;
        }
    }


    public void createNewCSV(String filepath){

        String file = filepath + ".csv";

        try {

            FileWriter writer = new FileWriter(file);

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


    public static String csvName(String filepath){

        return filepath + ".csv";
    }


    public void addShirtCSV(String[] atts, String filename){
        //add a shirt using addArticleCSV from info from GUI fields
        String filepath = csvName(filename);

        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA",};
        s[0] = "SHIRT";

        for (int i = 0; i < atts.length; i++){
            s[i+1] = atts[i];
        }

        addArticleCSV(s, filepath);
    }



    public void addPantsCSV(String[] atts, String filename){
        //add pants using addArticleCSV from info from GUI fields
        String filepath = csvName(filename);
        
        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA",};
        s[0] = "PANTS";

        for (int i = 0; i < 6; i++){
            s[i+1] = atts[i];
        } for (int j = 6; j < atts.length; j++){
            s[j+3] = atts[j];
        }

        addArticleCSV(s, filepath);
    }


    public static void addArticleCSV(String[] attributes, String filename){

        String[] att = parseStrings(attributes);
        File csvfile = new File(filename);

        try (FileWriter writer = new FileWriter(filename, true)){

            writer.write("\n");

            for (String s : att){
                writer.write(s);
            }
            System.out.println("added data successfully: ");

            writer.close();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(csvfile);
            } else {
                System.out.println("Desktop operations are not supported.");
            }

        } catch (IOException e) {
            System.out.println("ERR: Could not add data to file: " + filename);
        }

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

    public ArrayList displayArticles(String filename){

        System.out.println("begin displayArticles");

        Closet c = new Closet();
        System.out.println("made closet");
        c.readCSV(filename);

        System.out.println("read CSV");
  
        ArrayList list = c.getAll();
        System.out.println("got all");

        System.out.println("begin displayArticles");

        return list;

    }


    public void openCSV(String filename){

        File file = new File(filename + ".csv");

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


    public void deleteLine(String file, int index){
        //deletes a line of a CSV
        // file = file;

        File input = new File(file);
        File temp = new File("temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(input))){

            FileWriter writer = new FileWriter(temp);

            String currentLine;
            int lineCount = 1;

            while ((currentLine = br.readLine()) != null) {

                if (lineCount != index){
                    writer.write(currentLine);
                    writer.write("\n");
                } 
                lineCount++;
                
                }

            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            input.delete();
            temp.renameTo(new File(file));

            openCSV("testcloset");


            // if (Desktop.isDesktopSupported()) {
            //     Desktop.getDesktop().open(input);
            // } else {
            //     System.out.println("Desktop operations are not supported.");
            // }



        }
    





    public static void main(String[] args){

        // createNewCSV("testcloset");
        // // System.out.println(csvName("testcloset"));
        // String[] shirt1 = { "SHIRT", "blue", "aritzia", "M", "tie front", "drawer", "cropped", "tee"};
        // String[] pant1 = {"PANTS", "blue", "zara", "6", "ripped", "drawer", "high", "long", "baggy", "denim"};
        // // System.out.println( parseStrings( shirt1 ));
        // // System.out.println( parseStrings( pant1 ));

        // addArticleCSV(shirt1, "testcloset.csv");
        // addArticleCSV(pant1, "testcloset.csv");
        CSVReader myCSV = new CSVReader("testcloset");

        myCSV.openCSV("testcloset");

        // myCSV.deleteLine("testcloset", 0);
        // File a = new File("testcloset.csv");
        // a.delete();


        //System.out.println(myCSV.displayArticles("testcloset.csv"));




            //writeNew("closetCSVTest.csv");
        
    }
}