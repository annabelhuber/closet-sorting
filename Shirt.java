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
        this.shirtLength = shirtLength;
        this.sleeveLength = sleeveLength;

        this.myList = myList;

    }

    public void display(){
        //display shirt data
        System.out.println( "SHIRT: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location +  ", Shirt Length: " + shirtLength + ", Sleeve Length: " + sleeveLength);

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


}