/// closet.java

import java.util.Stack;

class Article{

    //create a superclass for all types of clothing
}


class Shirt extends Article{

    String clothingType;
    String color;
    String brand;
    String size;
    String shirtLength;
    String sleeveLength;
    String misc;
    String location;

    

    public Shirt(String clothingType, String color, String brand, String size, String shirtLength, 
    String sleeveLength, String misc, String location){

        //constructor to initialize Shirt

        this.clothingType = clothingType;
        this.color = color;
        this.brand = brand;
        this.size = size;
        this.shirtLength = shirtLength;
        this.sleeveLength = sleeveLength;
        this.misc = misc;
        this.location = location;

        //this.counter = counter;

    }

    void display(){
        //display the shirt data
        System.out.println( clothingType + ", " + color + ", " + brand  + ", " + size  + ", " +
        shirtLength + ", " + sleeveLength  + ", " + misc + ", " + location + ", " );
    }
}

public class Closet{


    public static void main(String[] args) {

        int counter = 0;

        //main
        Stack<Shirt> myCloset = new Stack<>();
            //insert demo entry
            myCloset.push (new Shirt ("clothingType", "color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location"));
            myCloset.push( new Shirt ("clothingType1", "color1", "brand", "size", "shirtLength", "sleeveLength", "misc", "location"));
             

      
        // show everything in the closet
        for (Shirt obj : myCloset){
            obj.display();
            System.out.println( counter + '\n');
            counter++;
        }
    }


}

