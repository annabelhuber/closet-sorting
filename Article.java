/// Article.java
import java.util.ArrayList;

class Article{
    //create a superclass for all types of clothing
    String color;
    String brand;
    String size;
    String misc;
    String location;
    ArrayList<String> myList = new ArrayList<>();

    public Article(String color, String brand, String size, String misc, String location){
        //initialize all variables
        this.color = color.toUpperCase();
        this.brand = brand.toUpperCase();
        this.size = size.toUpperCase();
        this.misc = misc.toUpperCase();
        this.location = location.toUpperCase();

        this.myList = myList;
    }

    void display(){
        //display the article data
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
         + misc + ", Location: " + location);
    }


    public ArrayList<String> getCategories(){
        //returns the categories for Article

        ArrayList<String> myCategories = new ArrayList<>();

        myCategories.add("COLOR");
        myCategories.add("BRAND");
        myCategories.add("SIZE");
        myCategories.add("MISC");
        myCategories.add("LOCATION");

        return myCategories;

    }

    public ArrayList<String> getDescriptors(){
        //return a list of the parameters used to make the article
        myList.add(color);
        myList.add(brand);
        myList.add(size);
        myList.add(misc);
        myList.add(location);

        return myList;
    }


    public String getOneDescriptor(String parameter){
        //return the value for the given parameter
        String p = parameter.toUpperCase();

        if (p.equals("COLOR")){
            return this.color;
        } else if (p.equals("BRAND")){
            return this.brand;
        } else if (p.equals("SIZE")){
            return this.size;
        } else if (p.equals("MISC")){
            return this.misc;
        } else if (p.equals("LOCATION")){
            return this.location;
        } else {
            return "ERROR: Parameter not Found";
        }
    }


    // public String getOneDescriptor(String parameter){
    //     //Returns the descriptor for a given parameter

    //     //change parameter to all caps
    //     String param = parameter.toUpperCase();

    //     ArrayList<String> myDescriptors = this.getDescriptors();

    //     for (String i : myDescriptors){
    //         //loop through the descriptors 
    //         if (i == param){
    //             //once found the given parameter
    //             String myString = param;
    //             return this.param;
    //         } else{
    //             return "ERROR: Parameter not found";
    //         }
    //     }
    // }


}