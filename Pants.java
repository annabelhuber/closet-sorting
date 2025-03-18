///Pants.java
import java.util.ArrayList;

class Pants extends Article{

    String rise;
    String length;
    String style;
    String material;
    ArrayList<String> myList = new ArrayList<>();

    

    public Pants ( String color, String brand, String size, String material, String rise, 
    String length, String style, String misc, String location) {

        super(color, brand, size, misc, location);

        this.rise = rise;
        this.length = length;
        this.style = style;
        this.material = material;

        this.myList = myList;

    }

    public void display(){
        //display pant data
        System.out.println( "PANTS: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location + ", Material: " + material + ", Rise: " + rise + ", Length: "
        + length + ", Style: " + style);

    }


    public ArrayList<String> getDescriptors(){
        //return a list of the parameters used to make the article
        myList.add(color);
        myList.add(brand);
        myList.add(size);
        myList.add(misc);
        myList.add(location);
        myList.add(material);
        myList.add(rise);
        myList.add(style);
        myList.add(length);

        return myList;
    }

}