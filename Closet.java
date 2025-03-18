/// closet.java

import java.util.Stack;
import java.util.ArrayList;
import java.lang.reflect.Parameter;
import java.util.*;


public class Closet{

    private ArrayList<Shirt> myShirts;
    private ArrayList<Pants> myPants;
    private ArrayList<Article> myCloset;

    public Closet(){

        //initialize by creating empty arraylists to store Pants and Shirts
        this.myPants = new ArrayList<>();
        this.myShirts = new ArrayList<>();
        //this.myCloset = new ArrayList<>();
    }

    //need a set (change) function
    //need a combine function to show all closet items (addAll)

    public void addShirt(String color, String brand, String size, String shirtLength, 
        String sleeveLength, String misc, String location){
        //adds shirt to a sub-list of only shirts
        //(repeated for each child of Article class)
        Shirt tempShirt = new Shirt (color, brand, size, shirtLength, 
        sleeveLength, misc, location);

        this.myShirts.add(tempShirt);

    }

    public int findArticle(Object o){
        //finds specific article in specified list
        int idx = myCloset.indexOf(o);

        return idx;

    }

    public void combineLists(List l){
        //combines the sub-lists into one combined-type list
        //List myCloset = new ArrayList();

        myCloset.addAll(l);
        //cont. with all article types

    }

    // public List iterator(){
    //     System.out.println("starting iter");
    //     System.out.println(c);
    //     List l = new ArrayList<>();
    //     ListIterator it = c.listIterator();

    //     while (it.hasNext()){
    //         System.out.println(it.next());
    //         l.add(it.next());
    //     }

    //     return l;
    // }

    public ArrayList searchCloset(Object obj, String cat, String att){
        // ArrayList l;
        ArrayList subL = new ArrayList<>();
        
        //determine which list to be searching through
        if (obj instanceof Shirt){
            ArrayList<Shirt> l = myShirts;
                    //if the category is undefined
            if (cat == null){

                for (Shirt a : l){
                    //Shirt tempArt = l.get(i);
                    ArrayList<String> descrip = a.getDescriptors();

                    for(String s : descrip){
                        //loop through each article to see if it matches
                        if (s == att){
                            subL.add(a);
                            break;
                        }
                    }
                }
            } else{
                //using the given category
                int idx = 0;
                Shirt base = l.get(0);
                ArrayList<String> params = base.getDescriptors();
                for (String sNew : params){
                    if (cat == sNew){
                        //once we find the given cateogry in the parameter list
                        idx = params.indexOf(sNew);
                    }
                }
                //loop through Articles in l looking only at the given category
                for (Shirt i : l){
                    //Shirt tempArt = l.get(i);
                    ArrayList descrip = i.getDescriptors();
                    if (att == descrip.get(idx)){
                        //if the attribute matches in the given category
                        subL.add(i);
                    }
                }

            }
            
        } else if (obj instanceof Pants){
            ArrayList<Pants> l = myPants;

                //if the category is undefined
                if (cat == null){
    
                    for (Pants a : l){
                        //Pants tempArt = l.get(i);
                        ArrayList<String> descrip = a.getDescriptors();
    
                        for(String s : descrip){
                            //loop through each article to see if it matches
                            if (s == att){
                                subL.add(a);
                                break;
                            }
                        }
                    }
                } else{
                    //using the given category
                    int idx = 0;
                    Pants base = l.get(0);
                    ArrayList<String> params = base.getDescriptors();
                    for (String sNew : params){
                        if (cat == sNew){
                            //once we find the given cateogry in the parameter list
                            idx = params.indexOf(sNew);
                        }
                    }
                    //loop through Articles in l looking only at the given category
                    for (Pants i : l){
                        ArrayList descrip = i.getDescriptors();
                        //Pants tempArt = l.get(i);
                        if (att == descrip.get(idx)){
                            //if the attribute matches in the given category
                            subL.add(i);
                        }
                    }
    
                }
        }
        // } else {
        //     //if class type is undetermined/not valid
        //     l.addAll(myShirts);
        //     l.addAll(myPants);
            
        // }

        // //if the category is undefined
        // if (cat == null){

        //     for (Article a : l){
        //         Article tempArt = l.get(i);
        //         ArrayList descrip = tempArt.getDescriptors();

        //         for(String s : descrip){
        //             //loop through each article to see if it matches
        //             if (s == att){
        //                 subL.add(tempArt);
        //                 break;
        //             }
        //         }
        //     }
        // } else{
        //     //using the given category
        //     int idx;
        //     ArrayList params = l.get(0);
        //     for (String sNew : params){
        //         if (cat == sNew){
        //             //once we find the given cateogry in the parameter list
        //             idx = params.indexOf(sNew);
        //         }
        //     }
        //     //loop through Articles in l looking only at the given category
        //     for (int i = 0; i < l.size(); i++){
        //         tempArt = l.get(i);
        //         ArrayList descrip = tempArt.getDescriptors();
        //         if (att == descrip.get(idx)){
        //             //if the attribute matches in the given category
        //             subL.add(tempArt);
        //         }
        //     }

        // }

        return subL;

        }



    // public List articleList(Object art){
    //     //sort closet by article

    //     //create a temp stack of items in specified class
    //     List<art> tempList = new ArrayList<>();

        
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
        // List<Article> myCloset = new ArrayList<>();

        Closet s = new Closet();
        Closet c = new Closet(); 

        //main
        
            // //insert demo entry
            // myCloset.add (new Shirt ("color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location"));
            // myCloset.add ( new Shirt ("black", "zara", "M", "cropped", "tank", "lacy", "drawer"));

        s.addShirt("color", "brand", "size", "shirtLength", "sleeveLength", "misc", "location");
        s.addShirt("black", "zara", "M", "cropped", "tank", "lacy", "drawer");

        for (Shirt a : s.myShirts){
            a.display();
        }
             
        // c.combineLists(s.myShirts);

        // //System.out.println("C:" + c);

        // for (Article b : c.myCloset){
        //     b.display();
        // }

      
        // show everything in the closet

        //List iter = s.iterator();
       
    //     for (Article obj : iter){
    //         //display shirts stored in the closet
    //         obj.display();
    //         System.out.print("Index: " + myCloset.findArticle(obj) + "\n");
    //         //counter++;
    //     }
    }


}

