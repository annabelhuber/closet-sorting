
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
    Closet c;


    public CSVReader(String filename){

        this.filename = filename;

        this.filepath = this.filename + ".csv";

        if(!fileExists()){
            this.createNewCSV(this.filepath);
        }

        this.c = new Closet();
        c.readCSV(filepath);

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


    public boolean addShirtCSV(String[] atts){
        //add a shirt using addArticleCSV from info from GUI fields
        //String filepath = csvName(filename);

        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA",};
        s[0] = "SHIRT";

        for (int i = 0; i < atts.length; i++){
            s[i+1] = atts[i];
        }

        if (this.addArticleCSV(s)){
            return true;
        }
        return false;
    }



    public boolean addPantsCSV(String[] atts){
        //add pants using addArticleCSV from info from GUI fields
        //String filepath = csvName(filename);
        System.out.println("ATT LENGTH " + atts.length);
        
        String[] s = {"NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA",
            "NA", "NA", "NA", "NA"};
        s[0] = "PANTS";

        for (int i = 0; i < 6; i++){
            s[i+1] = atts[i];
        } for (int j = 6; j < atts.length; j++){
            s[j+1] = atts[j];
        }

        if (this.addArticleCSV(s)){
            return true;
        } 
        return false;
    }

    public boolean addAnyArticleCSV(String[] attributes, String articleType){
        //runs addPantsCSV or addShirtCSV based on the article type given

        if (articleType.equals("Shirt")){
            //if its a shirt, run & return the boolean from addShirtCSV
            return this.addShirtCSV(attributes);
        } else {
            //if pants, run & return the boolean from addPantsCSV
            return this.addPantsCSV(attributes);
        }
    }


    public boolean addArticleCSV(String[] attributes){
        //adds article to the CSV file
        //returns FALSE if the article cannot be added
        //returns TRUE once added


        if (!this.checkDuplicates(attributes)){
            //if article already exists in CSV
            System.out.println("Article already exists in " + filename);
            return false;

        } else {

            String[] att = parseStrings(attributes);

        try (FileWriter writer = new FileWriter(filepath, true)){

            writer.write("\n");

            for (String s : att){
                writer.write(s);
            }
            System.out.println("added data successfully: ");

            writer.close();

        } catch (IOException e) {
            System.out.println("ERR: Could not add data to file: " + filename);
            return false;
        }
        }
    return true;

    }


    public boolean checkDuplicates(String[] attributes){
        //read the file and check to see if the article already exists in file
        //returns FALSE if exists, return TRUE if not a duplicate

        //converting given array to one comparable to the ones in the csv
        attributes = parseArrays(attributes);


        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;

            //skip first line
            br.readLine();

            while ((line = br.readLine()) != null){
                //read all the lines
                String[] data = line.split(",");
               
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
            int counter = 0;
            for (int i = 0; i < 6; i++){
                tempVar[i] = attributes[i];
                counter++;
            }

            for (int j = 6; j < 8; j++){
                tempVar[j] = "NA";
                counter++;
            }

            for (int p = 8; p < 12; p++){
                tempVar[p] = attributes[counter];
                counter++;
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



    public ArrayList displayArticles(){
        // function to send the data from CSV to a closet object
        //returns an arraylist of the Articles stored

        //reread the CSV in case articles were added/deleted
        c.reReadCSV(filepath, filename);
  
        ArrayList list = c.getAll();

        return list;

    }


    public ArrayList displaySearch(String attribute){
        //searches through the closet and returns the qualifying articles
        //input: attribute

        //reread the CSV in case articles were added/deleted
        c.readCSV(filepath);

        ArrayList<Article> searched = c.searchCloset(attribute);

        ArrayList results = c.getAllSearch(searched);

        return results;

    }


    public ArrayList displaySearch(String attribute, String category, String articleType){
        //searches through the closet and returns the qualifying articles
        //input: attribute, category, article type

        //reread the CSV in case articles were added/deleted
        c.readCSV(filepath);

        ArrayList<Article> searched = c.searchCloset(attribute, category, articleType);
        //searched.get(0).display();

        ArrayList results = c.getAllSearch(searched);

        return results;
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
                    } 

                } lineCount++;
            }

            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            input.delete();
            temp.renameTo(new File(filepath));

        }

    
    public void replaceLine(int index, String[] attributes){
        //takes the index of the line to be replaced and the data to fill instead
        //almost same process as deleteLine(), just adding new line instead of removing

        File input = new File(filepath);
        File temp = new File("temp.csv");

        String[] att = parseStrings(attributes);

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
                    } 
                    lineCount++;
                } else {
                    //once hit the desired line index
                    //write the new array

                    writer.write("\n");

                    for (String s : att){
                        writer.write(s);

                    }
                lineCount++;
            }

            writer.close();
            }
         } catch (IOException e) {
                e.printStackTrace();
            }

            input.delete();
            temp.renameTo(new File(filepath));
        
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
        String[] pant4 = {"PANTS", "black", "urban outfitters", "M", "ies frans", "mike's", "high", "long", "sweatpants", "cotton"};

        String[] shirt4 = {"SHIRT", "black", "X", "M", "Split back", "mike's", "long", "Tube"};
        String[] shirt5 = {"SHIRT", "white", "Zara","M","Tie Front", "Drawer", "Cropped", "tee"};
        String[] pant5 = {"PANTS", "blue", "lucky", "28", "ripped", "mike's", "low", "long", "flare", "denim"};
        CSVReader myCSV = new CSVReader("testing");

        // myCSV.addArticleCSV(pant1);
        // myCSV.addArticleCSV(pant2);
        // myCSV.addArticleCSV(shirt1);
        // myCSV.addArticleCSV(shirt2);
        // myCSV.addArticleCSV(shirt3);
        // myCSV.addArticleCSV(shirt4);
        // myCSV.addArticleCSV(shirt5);
        // myCSV.addArticleCSV(pant3);
        // myCSV.addArticleCSV(pant4);
        //myCSV.addAnyArticleCSV(pant5, "Pants");

        //myCSV.deleteLine(9);
        

        //ArrayList<ArrayList> trial = myCSV.displaySearch("black", "None", "All");
        
        //System.out.println(trial.get(1).size());
        ArrayList myList = myCSV.displayArticles();
        System.out.println(myList.size());
        //myCSV.openCSV();

        //ArrayList<ArrayList> trial2 = myCSV.displayArticles();
        


        

        //myCSV.deleteLine(0);
        
        // File a = new File("testing.csv");
        // a.delete();

        
    }
}