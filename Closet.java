/// closet.java

import java.util.Stack;
import java.util.ArrayList;
import java.lang.reflect.Parameter;
import java.util.Optional;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;


public class Closet{

    private ArrayList<Shirt> myShirts;
    private ArrayList<Pants> myPants;
    private ArrayList<Article> myCloset;

    public Closet(){

        //initialize by creating empty arraylists to store Pants and Shirts
        this.myPants = new ArrayList<>();
        this.myShirts = new ArrayList<>();
        //this.myCloset = new ArrayList<>();
    }


    public void readCSV(String filepath){
        //read the CSV file and add all items to the closet


        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;

            //skip first line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ArrayList<String> atts = new ArrayList<>();

                for (int i = 0; i< data.length; i++){
                    
                    if (!data[i].equals("NA")){
                        //if the entry is given
                        atts.add(data[i].toUpperCase());
                        }
                    }

                if (data[0].equals("SHIRT")){
                    //if the article is a shirt
                    atts.remove(0);
                    addShirt(atts);
                } else if (data[0].equals("PANTS")){
                    //if the article is pants
                    atts.remove(0);
                    addPants(atts);
                } else {
                    System.out.println("ERR: Invalid Article Type");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reReadCSV(String filepath, String filename){
        //rereads the csv to make sure nothing has been changed
        File csvFile = new File(filepath);

        if (!csvFile.exists()){
            //if the file does not exist, run readCSV instead
            this.readCSV(filepath);
        } else{
            //if the file does already exist, continue
            //make an instance of CSVReader to compare
            CSVReader myCSVReader = new CSVReader(filename);

            try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
                String line;
    
                //skip first line
                br.readLine();
    
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    //System.out.println("Reading data in Closet" + Arrays.toString(data));
                    ArrayList<String> atts = new ArrayList<>();

                    if (myCSVReader.checkDuplicates(data)){
                        //System.out.println("True");
                        //if line is not already in the CSV file, add it
                        for (int i = 0; i< data.length; i++){
                            
                            if (!data[i].equals("NA")){
                                //if the entry is given
                                atts.add(data[i].toUpperCase());
                                }
                            }
            
                            if (data[0].equals("SHIRT")){
                                //if the article is a shirt
                                atts.remove(0);
                                addShirt(atts);
                            } else if (data[0].equals("PANTS")){
                                //if the article is pants
                                atts.remove(0);
                                addPants(atts);
                            } 
                    } else {
                        //if the line already exists
                        //System.out.println("false");
                        continue;
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //need a set (change) function
    //need a combine function to show all closet items (addAll)

    public void addShirt(String color, String brand, String size, String shirtLength, 
        String sleeveLength, String misc, String location){
        //adds shirt to a sub-list of only shirts
        //(repeated for each child of Article class)
        Shirt tempShirt = new Shirt (color, brand, size, shirtLength, 
        sleeveLength, misc, location);

        this.myShirts.add(tempShirt);

    }


    public void addShirt(ArrayList<String> attributes){
        //duplicate of addShirt for an arraylist instead of many strings
        //adds shirt to a sub-list of only shirts
        //(repeated for each child of Article class)

        String color = attributes.get(0);
        String brand = attributes.get(1);
        String size = attributes.get(2);
        String sleeveLength = attributes.get(6);
        String shirtLength = attributes.get(5);
        String misc = attributes.get(3);
        String location = attributes.get(4);

        Shirt tempShirt = new Shirt (color, brand, size, shirtLength, 
        sleeveLength, misc, location);

        this.myShirts.add(tempShirt);

    }



    public void addPants(String color, String brand, String size, String material, String rise, 
    String length, String style, String misc, String location){
    //adds Pants to a sub-list of only Pants
    //(repeated for each child of Article class)
    Pants tempPants = new Pants (color, brand, size, material, rise, 
    length, style, misc, location);

    this.myPants.add(tempPants);

    }


    public void addPants(ArrayList<String> attributes){
        //duplicate of addShirt for an arraylist instead of many strings
        //adds shirt to a sub-list of only shirts
        //(repeated for each child of Article class)

        String color = attributes.get(0);
        String brand = attributes.get(1);
        String size = attributes.get(2);
        String misc = attributes.get(3);
        String location = attributes.get(4);
        String material= attributes.get(8);
        String rise = attributes.get(5);
        String length = attributes.get(6);
        String style = attributes.get(7);
        
        Pants tempPants = new Pants (color, brand, size, material, rise, 
        length, style, misc, location);

        this.myPants.add(tempPants);

    }

    public int findArticle(Object o){
        //finds specific article in specified list
        int idx = myCloset.indexOf(o);

        return idx;

    }

    public void combineLists(List l){
        //combines the sub-lists into one combined-type list
        //List myCloset = new ArrayList();

        myCloset.addAll(l);
        //cont. with all article types

    }

    //duplicate functions with different parameters
    //add a function that combines shirts+pants

    public ArrayList<Shirt> searchShirts(String attribute, String category){
        //duplicate searchShirt function with optional parameter "category" 
        String att = attribute.toUpperCase();
        System.out.println("attribute " + att);

        ArrayList<Shirt> l = myShirts;
        ArrayList<Shirt> subL = new ArrayList<>();

        String cat = category.toUpperCase();
        System.out.println("category " + cat);
            
            //using the given category
            //int idx = 0;
            //use the first shirt in the list to find the categories
            Shirt base = l.get(0);
            ArrayList<String> params = base.getCategories();
            for (String sNew : params){
                if (cat.equals(sNew)){
                    //once we find the given cateogry in the parameter list
                    category = cat;
                }
            }

            for (Shirt i : l){
                //loop through all shirts
                if (i.getOneDescriptor(category).equals(att)){
                    subL.add(i);
                }
            }

        return subL;
    }



    public ArrayList<Shirt> searchShirts(String attribute){
        //Search through shirts WITHOUT a given category
            //change the parameter attribute to all caps for comparison
            String att = attribute.toUpperCase();
            System.out.println("attribute " + att);

            //define the list we're looking at as myShirts
            ArrayList<Shirt> l = myShirts;
            //new arraylist subL to hold in qualifying Shirts
            ArrayList<Shirt> subL = new ArrayList<>();

            //loop through all shirts to see if any parameters given match attribute
                for (Shirt a : l){
                    //get the descriptors (parameters) for each article
                    ArrayList<String> descrip = a.getDescriptors();
                    
                        for(String s : descrip){
                            //loop through each article's parameters to see if it matches
                            //System.out.println(s + att);
                            if (s.equals(att)){
                                
                                subL.add(a);
                                break;
                            }
                        }
                    }
            

            return subL;
        }


    public ArrayList<Pants> searchPants(String attribute){
        //search Pants without a category

        //change the parameter attribute to all caps for comparison
        String att = attribute.toUpperCase();
    
        ArrayList<Pants> l = myPants;
        ArrayList<Pants> subL = new ArrayList<>();


            for (Pants a : l){
                //Shirt tempArt = l.get(i);
                ArrayList<String> descrip = a.getDescriptors();
    
                        for(String s : descrip){
                            //loop through each article to see if it matches
                            if (s.equals(att)){
                                subL.add(a);
                                break;
                            }
                        }
                    }
            

            return subL;
        }



        public ArrayList<Pants> searchPants(String attribute, String category){
            //search pants WITH a category

            String att = attribute.toUpperCase();
            String cat = category.toUpperCase();

            ArrayList<Pants> l = myPants;
            ArrayList<Pants> subL = new ArrayList<>();
    
                    //using the given category
                    Pants base = l.get(0);
                    ArrayList<String> params = base.getCategories();
                    for (String sNew : params){
                        if (cat.equals(sNew)){
                            //once we find the given cateogry in the parameter list
                            category = cat;
                        }
                    }
                    //loop through Articles in l looking only at the given category
                    for (Pants i : l){
                        if (i.getOneDescriptor(category).equals(att)){
                            //if the attribute matches in the given category
                            subL.add(i);
                        }
                    }
    
                return subL;
            }


    public ArrayList<Article> searchCloset(String attribute){
        //searches through all articles for given attribute
        ArrayList tempList = new ArrayList<>();

        ArrayList<Shirt> tempShirts = searchShirts(attribute);
        ArrayList<Pants> tempPants = searchPants(attribute);

        tempList.addAll(tempShirts);
        tempList.addAll(tempPants);

        return tempList;

    }


    public ArrayList<Article> searchCloset(String attribute, String category){
        //searches through all articles for given attribute WITH a category
        ArrayList tempList = new ArrayList<>();

        ArrayList<Shirt> tempShirts = searchShirts(attribute, category);
        ArrayList<Pants> tempPants = searchPants(attribute, category);

        tempList.addAll(tempShirts);
        tempList.addAll(tempPants);

        return tempList;

    }


    public ArrayList<Article> searchCloset(String attribute, String category, String articleType){
        //searches through all articles for given attribute WITH a category
        ArrayList tempList = new ArrayList<>();

        if (articleType.equals("Shirts") && !category.equals("None")){
            //if searching shirts with a category:
            tempList = searchShirts(attribute, category);
        } else if (articleType.equals("Shirts") && category.equals("None")){
            //if searching shirts without a category
            tempList = searchShirts(attribute);
        }

        else if (articleType.equals("Pants") && !category.equals("None")){
            //if searching pants with a category
            tempList = searchPants(attribute, category);
        } else if (articleType.equals("Pants") && category.equals("None")){
            //if searching pants without a category
            tempList = searchPants(attribute);
        }

        else{
            //if article type is undefined
            if (category.equals("None")){
                //if no given category
                ArrayList<Shirt> tempShirts = searchShirts(attribute);
                ArrayList<Pants> tempPants = searchPants(attribute);


                tempList.addAll(tempShirts);
                tempList.addAll(tempPants);

            } else {
                //with a given category
                ArrayList<Shirt> tempShirts = searchShirts(attribute, category);
                ArrayList<Pants> tempPants = searchPants(attribute, category);
                
                tempList.addAll(tempShirts);
                tempList.addAll(tempPants);
            }    
        }

        return tempList;

    }


    public ArrayList<Article> refineSearch(String[] attributes, String[] categories){
        //similar to search closet but uses lists as parameters
        //can run a search with multiple criteria
        //ArticleType MUST be first attribute/category (can be none)

        //create an empty arraylist to return at the end
        ArrayList<Article> tempList = new ArrayList<>();
        //create an arraylist to hold the initial search results
        ArrayList<Article> tempList1 = new ArrayList<>();

        //find the initial results first (assign tempList1)
        categories[0].toUpperCase();
        attributes[0].toUpperCase();

        if (categories[0].equals("ARTICLETYPE") && attributes[0].equals("SHIRT")){
            //if only looking for shirts, make tempList1 only shirts
            tempList1.addAll(myShirts);
        } else if (categories[0].equals("ARTICLETYPE") && attributes[0].equals("PANTS")){
            //same for pants
            tempList1.addAll(myPants);
        } else {
            //if not specified articleType, add both to tempList1
            tempList1.addAll(myShirts);
            tempList1.addAll(myPants);
        }
        if (attributes.length > 1){
            //if there are more parameters than just article type

            for (Article j : tempList1){
                //loop through every article in initial tempList
                int counter = 1;

                for (int i = 1; i < attributes.length; i++){
                    //loops through every set of attributes/categories, starting with the one after ArticleType
                    categories[i] = categories[i].toUpperCase();
                    attributes[i] = attributes[i].toUpperCase();
                   

                    if ( j.getOneDescriptor(categories[i]).equals(attributes[i])){
                        //if the attribute is a match
                        //add to counter
                        counter++;
                       
                        if (counter == (attributes.length)) {
                            //if the article is a match for all attributes
                            //add to tempList
                            tempList.add(j);
                        }
                        continue;
                    } else {
                        break;
                    }

                }
            }
        } else {
            //if articleType is the only parameter
            tempList.addAll(tempList1);
        }

        if (tempList.size() < 1){
            //if nothing was found
            System.out.println("No Matching Articles Found");
        }
        return tempList;

    }


    public ArrayList<Article> refineSearch(ArrayList<String> attributes, ArrayList<String> categories){
        //similar to search closet but uses lists as parameters
        //can run a search with multiple criteria
        //ArticleType MUST be first attribute/category (can be none)

        //create an empty arraylist to return at the end
        ArrayList<Article> tempList = new ArrayList<>();
        //create an arraylist to hold the initial search results
        ArrayList<Article> tempList1 = new ArrayList<>();

        //find the initial results first (assign tempList1)
        categories.get(0).toUpperCase();
        attributes.get(0).toUpperCase();

        if (categories.get(0).equals("ARTICLETYPE") && attributes.get(0).equals("SHIRT")){
            //if only looking for shirts, make tempList1 only shirts
            tempList1.addAll(myShirts);
        } else if (categories.get(0).equals("ARTICLETYPE") && attributes.get(0).equals("PANTS")){
            //same for pants
            tempList1.addAll(myPants);
        } else {
            //if not specified articleType, add both to tempList1
            tempList1.addAll(myShirts);
            tempList1.addAll(myPants);
        }
        if (attributes.size() > 1){
            //if there are more parameters than just article type

            for (Article j : tempList1){
                //loop through every article in initial tempList
                int counter = 1;

                for (int i = 1; i < attributes.size(); i++){
                    //loops through every set of attributes/categories, starting with the one after ArticleType
                    categories.set(i, categories.get(i).toUpperCase());
                    attributes.set(i, attributes.get(i).toUpperCase());
                

                    if ( j.getOneDescriptor(categories.get(i)).equals(attributes.get(i))){
                        //if the attribute is a match
                        //add to counter
                        counter++;
                     
                        if (counter == (attributes.size())) {
                            //if the article is a match for all attributes
                            //add to tempList
                            tempList.add(j);
                        }
                        continue;
                    } else {
                        break;
                    }

                }
            }
        } else {
            //if articleType is the only parameter
            tempList.addAll(tempList1);
        }

        if (tempList.size() < 1){
            //if nothing was found
            System.out.println("No Matching Articles Found");
        }
        return tempList;

    }


    public void showShirts(){
        //displays all the shirts in the closet and the total number

        for (Article s : myShirts){
            s.display();
        }

        System.out.println("Total Shirts: " + myShirts.size());
    }


    public void showPants(){
        //displays all the pants in the closet and the total number

        for (Article s : myPants){
            s.display();
        }

        System.out.println("Total Pants: " + myPants.size());
    }


    public void showAll(){
        //displays all the articles in the closet and the total number

        this.showShirts();
        this.showPants();

        int num = myShirts.size() + myPants.size();

        System.out.println("Total: " + num);
    }



    public ArrayList getShirts(){

        ArrayList list = new ArrayList<>();

        for (Shirt s : myShirts){
            ArrayList<String> newList = s.getDescriptors();
            list.add(newList);
        }

        return list;

    }


    public ArrayList getPants(){

        ArrayList list = new ArrayList<>();

        for (Pants p : myPants){
            ArrayList<String> newList = p.getDescriptors();
            list.add(newList);
        }

        return list;

    }

    public ArrayList getAll(){
    
        ArrayList list = new ArrayList<>();

        System.out.println(myShirts.size());

        for (Shirt s : myShirts){
            ArrayList<String> newList = s.getDescriptors();
            list.add(newList);
        }
        for (Pants p : myPants){
            ArrayList<String> newList = p.getDescriptors();
            list.add(newList);
        }

        return list;


    }


    public ArrayList getAllSearch(ArrayList<Article> givenList){
        //Returns an arrayList of Articles from givenList (search results)

        ArrayList list = new ArrayList<>();

        for (Article s : givenList){
            //add the arrayList of descriptors for each Article
            list.add(s.getDescriptors());
        }

        //return the ArrayList of ArrayLists [articles[descriptors]]
        return list;
    }
                
            


    public static void main(String[] args) {

        int counter = 0;
        // List<Article> myCloset = new ArrayList<>();

        Closet s = new Closet();
        Closet c = new Closet(); 

        //main
        
            // //insert demo entry
            // myCloset.add (new Shirt ("color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location"));
            // myCloset.add ( new Shirt ("black", "zara", "M", "cropped", "tank", "lacy", "drawer"));

        //s.addShirt("color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location");
        // s.addShirt("black", "zara", "M", "cropped", "tank", "lacy", "drawer");
        // s.addShirt("white", "princess polly", "M", "cropped", "tee", "tie front", "underBed");
        // s.addShirt("blue", "zara", "M", "mid", "tube", "denim", "hanging");
        // s.addShirt("orange", "urban outfitters", "M", "cropped", "tank", "ripped", "drawer");
        // s.addShirt("blue", "aritzia", "M", "cropped", "tee", "tie front", "drawer");

        // s.addPants("blue", "zara", "6", "denim", "high", "long", "baggy", "ripped", "drawer");


        // s.readCSV("testing.csv");

        // //s.showShirts();

        // ArrayList<Article> searching = s.searchCloset("black", "color");
        
        // for (Article a : searching){
        //     a.display();
        // }
        s.readCSV("testing.csv");

        // ArrayList<String> atts = new ArrayList<>(Arrays.asList("Shirt", "cropped"));
        // // String[] cats = {"articletype", "location"};
        // ArrayList<String> cats = new ArrayList<>(Arrays.asList("articletype", "shirtlength"));

        // ArrayList<Article> refineSearch = s.refineSearch(atts, cats);

        // System.out.println(refineSearch.size());

        // for (Article a : refineSearch){
        //     a.display();
        // }

        // for (Pants p : s.myPants){
        //     p.display();
        // }

        // c.combineLists(s.myShirts);
        // c.combineLists(s.myPants);

        // for (Article check : c){
        //     check.display();
        // }


        // ArrayList<Shirt> search = s.searchCloset("BLUE");
       
        // System.out.println("Search results for BLue: ");
        // for (Article b : search){
        //     b.display();
        // }
             
    }


}

