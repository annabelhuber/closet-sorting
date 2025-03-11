/// Article.java

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