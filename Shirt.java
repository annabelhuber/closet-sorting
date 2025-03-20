// Shirt.java
import java.util.ArrayList;


class Shirt extends Article{

    //attributes only applicable to shirts
    String shirtLength;
    String sleeveLength;
    ArrayList<String> myList = new ArrayList<>();
    

    public Shirt(String color, String brand, String size, String shirtLength, 
    String sleeveLength, String misc, String location){

        //define the parameters from Article parent class
        super(color, brand, size, misc, location);

        //constructor to initialize Shirt
        this.shirtLength = shirtLength.toUpperCase();
        this.sleeveLength = sleeveLength.toUpperCase();

        this.myList = myList;

    }

    public void display(){
        //display shirt data
        System.out.println( "SHIRT: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location +  ", Shirt Length: " + shirtLength + ", Sleeve Length: " + sleeveLength);

    }

    public ArrayList<String> getCategories(){
        //returns the categories for Shirt (override from Article)

        ArrayList<String> myCategories = new ArrayList<>();

        //add all the parameters as strings
        myCategories.add("COLOR");
        myCategories.add("BRAND");
        myCategories.add("SIZE");
        myCategories.add("MISC");
        myCategories.add("LOCATION");
        myCategories.add("SHIRTLENGTH");
        myCategories.add("SLEEVELENGTH");

        return myCategories;

    }


    public ArrayList<String> getDescriptors(){
        //return a list of the parameters used to make the article
        myList.add(color);
        myList.add(brand);
        myList.add(size);
        myList.add(misc);
        myList.add(location);
        myList.add(shirtLength);
        myList.add(sleeveLength);

        return myList;
    }


    public String getOneDescriptor(String parameter){
        //return the value for the given parameter (override from Article)
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
        }  else if (p.equals("SHIRTLENGTH")){
            return this.shirtLength;
        } else if (p.equals("SLEEVELENGTH")){
            return this.sleeveLength;
        }
        
        
        else {
            return "ERROR: Parameter not Found";
        }
    }


}