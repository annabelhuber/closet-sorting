/// closet.java

import java.util.Stack;
import java.util.ArrayList;
import java.util.*;

class Article{
    //create a superclass for all types of clothing
    String color;
    String brand;
    String size;
    String misc;
    String location;

    public Article(String color, String brand, String size, String misc, String location){
        //initialize all variables
        this.color = color;
        this.brand = brand;
        this.size = size;
        this.misc = misc;
        this.location = location;
    }

    void display(){
        //display the article data
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
         + misc + ", Location: " + location);
    }


}


class Shirt extends Article{

    //attributes only applicable to shirts
    String shirtLength;
    String sleeveLength;
    

    public Shirt(String color, String brand, String size, String shirtLength, 
    String sleeveLength, String misc, String location){

        //define the parameters from Article parent class
        super(color, brand, size, misc, location);

        //constructor to initialize Shirt
        this.shirtLength = shirtLength;
        this.sleeveLength = sleeveLength;
    }

    public void displayShirt(){
        //display shirt data
        System.out.println( "SHIRT: ");
        display();
        System.out.println(", Shirt Length: " + shirtLength + ", Sleeve Length: " + sleeveLength);

    }


}

public class Closet{


    // public List articleList(Object art){
    //     //sort closet by article

    //     //create a temp stack of items in specified class
    //     Stack<art> tempStack = new Stack<>();

        
    //     for (Object obj : myCloset){
    //         //loop through closet
    //         if (obj.getClass() == art){
    //             //if obj is the correct class, push onto temp stack
    //             tempStack.push(obj);
    //         }
    //     }
    //     //return the new stack of items of a certain class
    //     return tempStack;
    // }


    // public Stack searchStack(Object item, String param, String att){
    //     //sorts articles by their attributes
    //     if (item != null){
    //         //if article type is specified
    //         Stack<item> tempCloset = new Stack<>();

    //     }
        
    // }


    public static void main(String[] args) {

        int counter = 0;
        List<Article> myCloset = new ArrayList<>();
        

        //main
        
            //insert demo entry
            myCloset.add (new Shirt ("color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location"));
            myCloset.add ( new Shirt ("black", "zara", "M", "cropped", "tank", "lacy", "drawer"));
             

      
        // show everything in the closet
       
        for (Article obj : myCloset){
            //display shirts stored in the closet
            obj.displayShirt();
            System.out.print( counter + "\n");
            counter++;
        }
    }


}

