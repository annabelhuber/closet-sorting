// Shirt.java


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

    public void display(){
        //display shirt data
        System.out.println( "SHIRT: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location +  ", Shirt Length: " + shirtLength + ", Sleeve Length: " + sleeveLength);

    }


}