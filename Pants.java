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

        this.rise = rise.toUpperCase();
        this.length = length.toUpperCase();
        this.style = style.toUpperCase();
        this.material = material.toUpperCase();

        this.myList = myList;

    }

    public void display(){
        //display pant data
        System.out.println( "PANTS: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location + ", Material: " + material + ", Rise: " + rise + ", Length: "
        + length + ", Style: " + style);

    }


    public ArrayList<String> getCategories(){
        //returns the categories for Pants (override from Article)

        ArrayList<String> myCategories = new ArrayList<>();

        myCategories.add("COLOR");
        myCategories.add("BRAND");
        myCategories.add("SIZE");
        myCategories.add("MISC");
        myCategories.add("LOCATION");
        myCategories.add("RISE");
        myCategories.add("LENGTH");
        myCategories.add("STYLE");
        myCategories.add("MATERIAL");

        return myCategories;

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
        }  else if (p.equals("MATERIAL")){
            return this.material;
        } else if (p.equals("RISE")){
            return this.rise;
        } else if (p.equals("STYLE")){
            return this.style;
        } else if (p.equals("LENGTH")){
            return this.length;
        }
        
        
        else {
            return "ERROR: Parameter not Found";
        }
    }

}