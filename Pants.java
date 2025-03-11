///Pants.java

class Pants extends Article{

    String rise;
    String length;
    String style;
    String material;

    public Pants ( String color, String brand, String size, String material, String rise, 
    String length, String style, String misc, String location) {

        super(color, brand, size, misc, location);

        this.rise = rise;
        this.length = length;
        this.style = style;
        this.material = material;

    }

    public void display(){
        //display pant data
        System.out.println( "PANTS: ");
        System.out.println("Color: " + color + ", Brand: " + brand  + ", Size: " + size  + ", Misc Details: "
        + misc + ", Location: " + location + ", Material: " + material + ", Rise: " + rise + ", Length: "
        + length + ", Style: " + style);

    }

}