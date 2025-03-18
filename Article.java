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
        this.color = color;
        this.brand = brand;
        this.size = size;
        this.misc = misc;
        this.location = location;

        this.myList = myList;
    }

    void display(){
        //display the article data
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
         + misc + ", Location: " + location);
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


}