//GUI.java
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import java.awt.GridLayout; 
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
//import javax.swing.plaf.basic.ButtonActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;


public class GUI implements ActionListener{

    //setup
    private JFrame frame;
    private CardLayout cl;
    private CSVReader myCSV;

    //panels
    private JPanel homePanel;
    private JPanel addNewPanel;
    private JPanel addNewSPanel;
    private JPanel addNewPPanel;
    private JPanel seeAllPanel;
    private JPanel homeContainer;
    private JPanel entryPanel;
    private JPanel success;
    private JPanel searchPanel;

    //buttons
    private JButton addNew;
    private JButton addNewS;
    private JButton addNewP;
    private JButton confirmNew;
    private JButton seeAll;
    private JButton back;
    private JButton nameButton;
    private JButton search;
    private JButton searchArticles;

    //text fields
    private JTextField name;
    private JTextField color;
    private JTextField brand;
    private JTextField size;
    private JTextField misc;
    private JTextField location;
    private JTextField sleeveLength;
    private JTextField shirtLength;

    private JTextField giveAttribute;
    private JComboBox<String> artOpt;
    private JComboBox<String> catOpt;


        //**** hard coding this FOR NOW**** */
        String nameSet = "testing";

    public GUI(){

        //set up the CSVReader to hold the data
        myCSV = new CSVReader(nameSet);

        //setting a frame and a card layout
        frame = new JFrame();
        cl = new CardLayout(10,10);

        //making buttons to switch cards
        addNew = new JButton("Add New");
        seeAll = new JButton("See All");
        back = new JButton("Back to Home");

        addNewS = new JButton("Add New Shirt");
        addNewP = new JButton("Add New Pants");
        confirmNew = new JButton("Confirm New Entry");

        nameButton = new JButton("Continue");
        search = new JButton("Search");

        //setting up textboxes
        name = new JTextField();

        //Main Menu panel (homeContainer)
        homeContainer = new JPanel(cl);


        // ***this will be the intro page LATER***

        // entry panel (enter name)
        // entryPanel = new JPanel(new GridBagLayout());
        // entryPanel.add(new JLabel("Enter Your Name"));
        // entryPanel.add(name);
        // entryPanel.add(nameButton);

        // homeContainer.add(entryPanel, "Enter Name");

        //Main panel
        homePanel = new JPanel();
        homePanel.setPreferredSize(new Dimension(500, 400));
        homePanel.add(addNew);
        homePanel.add(seeAll);

        homeContainer.add(homePanel, "Main Menu");

        //set up see all panel
        seeAllPanel = new JPanel(new GridBagLayout());
        seeAllPanel.setPreferredSize(new Dimension(300, 300));
        seeAllPanel.setLayout( new GridBagLayout());

        homeContainer.add(seeAllPanel, "See All");
        


        //set up add new panel
        addNewPanel = new JPanel(new GridLayout(4, 1));
        addNewPanel.setPreferredSize(new Dimension(100,400));
        addNewPanel.add(new JLabel("Add New"));
        
        //add it to the container
        //back button is added in actionPerformed
        homeContainer.add(addNewPanel, "Add New");

        //set up add newShirt panel
        addNewSPanel = new JPanel();
        addNewSPanel.setLayout(new GridBagLayout());
        addNewSPanel.setPreferredSize(new Dimension(300, 300));
        homeContainer.add(addNewSPanel, "Add New Shirt");


        //set up new Pants panel
        addNewPPanel = new JPanel(new GridBagLayout());
        homeContainer.add(addNewPPanel, "Add New Pants");

        //set up the successfully added panel
        success = new JPanel();
        success.setLayout(new GridBagLayout());
        success.setPreferredSize(new Dimension(300, 300));
        homeContainer.add(success, "Successfully Added");

        //set up search panel
        searchPanel = new JPanel();
        searchPanel.setLayout( new GridLayout(4, 2));
        homeContainer.add(searchPanel, "Search");



        //set up the buttons
        addNew.addActionListener(this);
        seeAll.addActionListener(this);
        back.addActionListener(this);
        addNewS.addActionListener(this);
        addNewP.addActionListener(this);
        confirmNew.addActionListener(this);
        search.addActionListener(this);


        frame.add(homeContainer);
        cl.show(homeContainer, "Main Menu");

        frame.setSize(500,600);
        frame.setTitle("First Try");
        frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String[] args){
        new GUI();
    }



   @Override
    public void actionPerformed(ActionEvent e){
        

        // if (e.getSource() == nameButton){
        //     String nameFinal = name.getText();
        //     cl.show(homeContainer, "Main Menu");
        // }

        if (e.getSource() == addNew){
            
                cl.show(homeContainer, "Add New");
                addNewPanel.add(back, BorderLayout.NORTH);
                addNewPanel.add(addNewS, BorderLayout.CENTER);
                addNewPanel.add(addNewP, BorderLayout.SOUTH);
                // addNewItem();
            }
        else if (e.getSource() == seeAll){
                showAll();
                cl.show(homeContainer, "See All");

            }
        else if (e.getSource() == back){
                System.out.println("Home button pressed");
                cl.show(homeContainer, "Main Menu");
            }
        else if (e.getSource() == addNewS){
            addNewItem();
            cl.show(homeContainer, "Add New Shirt");
        } 
        else if (e.getSource() == addNewP){
            addNewPants();
        }
        else if (e.getSource() == confirmNew){
            //enter a new article
            confirmEntry();
            cl.show(homeContainer, "Successfully Added");
        }
        else if (e.getSource() == search){
            //displays the search panel
            searchPanelSetUp();
            cl.show(homeContainer, "Search");
        }
    }



    public void showAll(){
        //runs through all articles in CSV file and displays them
        //in a 3 column grid (using the closet class)

        //set up as a gridbaglayout
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.insets = new Insets(0, 10, 0, 10);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        seeAllPanel.add(back, c);

        //****** */
        //add a scroll button
        //****** */

        c.gridx = 2;
        c.gridy = 0;
        seeAllPanel.add(search, c);
        
        ArrayList<ArrayList> myData = myCSV.displayArticles();

        int numArticles = myData.size();
        JLabel counter = new JLabel("Total Articles: " + Integer.toString(numArticles));
        c.gridx = 0;
        c.gridy = 0;
        seeAllPanel.add(counter, c);

   
        for (int i = 0; i < myData.size(); i++){
            //loop through each article 
            //get the article type for the header
            String tempString = myData.get(i).get(0) + ": \n";

            for (int j = 0; j < myData.get(0).size(); j++){
                //loop through the attributes of each article
                tempString = tempString + myData.get(i).get(j) + "\n";
                }
            
            //set up the grid format

            c.insets = new Insets(10, 10, 10, 10);
            c.anchor = GridBagConstraints.CENTER;
            
            c.gridwidth = 1;
            c.ipady = 90;
            c.weightx = 1;
            c.weighty = 0.5;
            c.gridx = i % 3;
            c.gridy = (i / 3) + 1;

            //display in a JTextArea
            JTextArea temp = new JTextArea(tempString);
            seeAllPanel.add(temp, c);
            
            }

        //display this card

        //JScrollPane scrollPane = new JScrollPane(seeAllPanel);
        //homeContainer.add(scrollPane);

    }


    public void confirmEntry(){
        //takes the text boxes and adds the info to csv

        String filename = name.getText();


        System.out.println("file created");

        //create an arraylist of the attributes
        ArrayList<JTextField> paramsBase = new ArrayList<>(Arrays.asList(brand, size, misc, location, sleeveLength, shirtLength));
        //create an array of the categories
        String[] params = {"Color", "Brand", "Size", "Misc", "Location", "Sleeve Length", "Shirt Length"};

        for (JTextField s : paramsBase){
            //for each of the attributes
            if (s.getText().isEmpty()){
                //replace with X if not entered
                String temp = "X ";
                params[paramsBase.indexOf(s)] = temp;
            } else{
                //if entered
                //add the given string to the list in the appropriate place
                params[paramsBase.indexOf(s)] = s.getText();
            }

        }

        //take the info from the text fields and add to CSV
        if (myCSV.addShirtCSV(params)){
            //if added successfully (returns true)
            successPanel(true);
            //runs the panel for added correctly
        } else{
            //if could not be added
            //runs the panel with error message
            successPanel(false);
        }
     
    }


    public void successPanel(boolean added){
        //sets up the JPanel "success"
        //shows up after adding a new article (true)
        //or failing to add an article (false)
        GridBagConstraints c = new GridBagConstraints();

        if (added){
            //if added, show screen with next options
            c.weightx = 0.5;
            c.weighty = 0.5;

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 3;
            JLabel done = new JLabel("Item Added Successfully!");
            done.setBackground(Color.GREEN);
            done.setOpaque(true);

            success.add(done, c);

            c.gridx = 0;
            c.gridy = 1;
            success.add(new JLabel("Add Another?"), c);

            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 2;
            success.add(addNew, c);

            c.gridx = 0;
            c.gridy = 2;
            success.add(new JLabel("Back to Home"), c);

            c.gridx = 1;
            c.gridy = 2;
            c.gridwidth = 2;
            success.add(back, c);

            c.gridx = 0;
            c.gridy = 3;
            success.add(new JLabel("See All Entries"), c);

            c.gridx = 1;
            c.gridy = 3;
            c.gridwidth = 2;
            success.add(seeAll, c);
        }
        else {
            //if item could not be added, run this panel
            c.weightx = 0.5;
            c.weighty = 0.5;

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 3;

            JLabel notDone = new JLabel("Item Could not be Added");
            notDone.setBackground(Color.RED);
            notDone.setOpaque(true);
            success.add(notDone, c);

            c.gridx = 0;
            c.gridy = 1;
            success.add(new JLabel("Try Again?"), c);

            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 2;
            success.add(addNew, c);

            c.gridx = 0;
            c.gridy = 2;
            success.add(new JLabel("Back to Home"), c);

            c.gridx = 1;
            c.gridy = 2;
            c.gridwidth = 2;
            success.add(back, c);

            c.gridx = 0;
            c.gridy = 3;
            success.add(new JLabel("See All Entries"), c);

            c.gridx = 1;
            c.gridy = 3;
            c.gridwidth = 2;
            success.add(seeAll, c);

        }

        // cl.show(homeContainer, "Successfully Added");
    }


    public void addNewPants(){
        //GridBagConstraints c = new GridBagConstraints();
        addNewPPanel.add(back);
        cl.show(homeContainer, "Add New Pants");
    }


    public void searchPanelSetUp(){
        //sets up the JPanel for searching items
        //comes up if the search/filter button is pressed

        //set up formatting here

        JLabel attribute = new JLabel("Type Attribute: ");
        searchPanel.add(attribute);

        giveAttribute = new JTextField("e.g. Blue");
        giveAttribute.setForeground(Color.GRAY);
        searchPanel.add(giveAttribute);

        JLabel articleCat = new JLabel("Select Article Type: ");
        searchPanel.add(articleCat);

        String[] articleOptions = {"All", "Shirts", "Pants"};
        artOpt = new JComboBox<String>(articleOptions);
        searchPanel.add(artOpt);

        JLabel category = new JLabel("Select Category: ");
        searchPanel.add(category);

        String[] categoryOptions = {"None", "Color", "Brand", "Size", "Location", "Misc", "Shirt Length",
            "Sleeve Length", "Pant Rise", "Pant Length", "Pant Style", "Pant Material"};
        catOpt = new JComboBox<String>(categoryOptions);
        searchPanel.add(catOpt);

        searchPanel.add(back);

        searchArticles = new JButton("Search Articles");
        searchPanel.add(searchArticles);


    }

    public void searchClosetDisplay(){
        //sets up the display panel for searching
        String attribute = giveAttribute.getText();
        String articleType = (String) artOpt.getSelectedItem();
        String categoryType = (String) catOpt.getSelectedItem();

        ArrayList results = myCSV.displaySearch(attribute, categoryType, articleType);

        
    }

    

    public void addNewItem(){

        //set up the layout
        
        GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.BOTH;

        String[] compLabels = {"Color: ", "Brand: ", "Size: ", "Misc: ", 
            "Location: ", "Sleeve Length: ", "Shirt Length: "};

        color = new JTextField("e.g. Blue");
        brand = new JTextField("e.g. Zara");
        size = new JTextField("e.g. M");
        misc = new JTextField("e.g. Tie Front");
        location = new JTextField("e.g. Drawer");
        sleeveLength = new JTextField("e.g. Tee");
        shirtLength = new JTextField("e.g. Cropped");

        JTextField[] compText = {color, brand, size, misc, location, sleeveLength, shirtLength};

        
        c.fill = GridBagConstraints.HORIZONTAL;
        // c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        addNewSPanel.add(back, c);

        //c.fill = GridBagConstraints.BOTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        addNewSPanel.add(new JLabel("Name: "), c);

        //c.fill = GridBagConstraints.BOTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 0;
        
        c.ipadx = 50;
        name = new JTextField("testcloset");
        name.setForeground(Color.GRAY);
        addNewSPanel.add(name, c);

        for (int i = 0; i < compLabels.length; i++){

            //c.anchor = GridBagConstraints.CENTER;

            c.insets = new Insets(0, 10, 0, 50);

            c.gridx = 0;
            c.gridy = i + 1;
            c.weightx = 0.25;
            c.gridwidth = 1;
            addNewSPanel.add(new JLabel(compLabels[i]), c);

            c.gridx = 2;
            c.gridy = i + 1;
            c.weightx = 0.75;
            //c.gridwidth = 3;
            compText[i].setForeground(Color.GRAY);
            addNewSPanel.add(compText[i], c);
        }

        //confirmNew = new JButton("Confirm New Entry");


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = compLabels.length + 1;
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        addNewSPanel.add(confirmNew, c);


        // cl.show(homeContainer, "Add New Shirt");

    }

}