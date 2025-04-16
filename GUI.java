//GUI.java
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
    private JPanel searchResultPanel;
    private JPanel topButtons;
    private JPanel bottomButtons;

    //buttons
    private JButton addNew;
    private JButton addNewS;
    private JButton addNewP;
    private JButton confirmNewShirt;
    private JButton confirmNewPants;
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
    private JTextField rise;
    private JTextField length;
    private JTextField material;
    private JTextField style;

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
        confirmNewShirt = new JButton("Confirm New Entry (Shirt)");
        confirmNewPants = new JButton("Confirm New Entry (Pants)");

        nameButton = new JButton("Continue");
        search = new JButton("Search");
        searchArticles = new JButton("Search Articles");

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
        homePanel.setPreferredSize(new Dimension(600, 600));
        homePanelSetUp();

        homeContainer.add(homePanel, "Main Menu");

        //see/show All panel
        seeAllPanel = new JPanel();
        //sets up the buttons
        showAllsetUp();
        //add to the container
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
        addNewPPanel = new JPanel();
        //set up the panel
        addNewPants();
        homeContainer.add(addNewPPanel, "Add New Pants");

        //set up the successfully added panel
        success = new JPanel();
        success.setLayout(new GridBagLayout());
        success.setPreferredSize(new Dimension(300, 300));
        homeContainer.add(success, "Successfully Added");

        //set up search panel
        searchPanel = new JPanel();
        searchPanel.setLayout( new GridLayout(4, 2));
        searchPanelSetUp();
        homeContainer.add(searchPanel, "Search");


        //set up search results panel
        searchResultPanel = new JPanel();
        // searchResultPanel.setLayout ( new GridLayout(10, 4));
        homeContainer.add(searchResultPanel, "Search Results");



        //set up the buttons
        addNew.addActionListener(this);
        seeAll.addActionListener(this);
        back.addActionListener(this);
        addNewS.addActionListener(this);
        addNewP.addActionListener(this);
        confirmNewShirt.addActionListener(this);
        confirmNewPants.addActionListener(this);
        search.addActionListener(this);
        searchArticles.addActionListener(this);


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
            //set up & show panel to Add a new Article
                addNewPanelSetUp();
                cl.show(homeContainer, "Add New");
            }
        else if (e.getSource() == seeAll){
            //sets up & displays all Articles
                //showAll();
                //puts the back button at the top
                topButtons.add(back);
                cl.show(homeContainer, "See All");

            }
        else if (e.getSource() == back){
            //returns to home screen
                // System.out.println("Home button pressed");
                cl.show(homeContainer, "Main Menu");
            }
        else if (e.getSource() == addNewS){
            //sets up add New Shirt panel
            addNewShirt();
            cl.show(homeContainer, "Add New Shirt");
        } 
        else if (e.getSource() == addNewP){
            //sets up & displays add new Pants panel
            //NOT DONE YET
            bottomButtons.add(back);
            cl.show(homeContainer, "Add New Pants");
        }
        else if (e.getSource() == confirmNewShirt){
            //enter a new shirt
            confirmEntry("Shirt");
            cl.show(homeContainer, "Successfully Added");
        }
        else if (e.getSource() == confirmNewPants){
            //enter new pants
            confirmEntry("Pants");
            cl.show(homeContainer, "Successfully Added");
        }
        else if (e.getSource() == search){
            //displays the search panel
            searchPanel.add(back);
            cl.show(homeContainer, "Search");
        }
        else if (e.getSource() == searchArticles){
            //displays the search panel
            System.out.println("searching articles");
            searchClosetDisplay();
            cl.show(homeContainer, "Search Results");
        }
    }



    public void homePanelSetUp(){
        //setup the home panel
        //using a boxlayout
        homePanel.setLayout(new BoxLayout (homePanel, BoxLayout.PAGE_AXIS));
        homePanel.setBackground(Color.PINK);
        homePanel.setOpaque(true);

        //create a welcome banner with name of CSV file
        String welcome = "Welcome, " + nameSet;
        JLabel welcomeLabel = new JLabel(welcome);
        welcomeLabel.setFont( new Font("Serif", Font.PLAIN, 25));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add welcome to the layout
        homePanel.add(welcomeLabel, BorderLayout.PAGE_START);

        //center all the components
        welcomeLabel.setAlignmentX(homePanel.CENTER_ALIGNMENT);
        addNewS.setAlignmentX(homePanel.CENTER_ALIGNMENT);
        addNewP.setAlignmentX(homePanel.CENTER_ALIGNMENT);
        seeAll.setAlignmentX(homePanel.CENTER_ALIGNMENT);

        //add a space between the label and the buttons
        homePanel.add(Box.createRigidArea(new Dimension (10, 30)));

        //add buttons for add new shirt, pants, see all
        homePanel.add(addNewS);
        homePanel.add(addNewP);
        homePanel.add(seeAll);

    }


    public void addNewPanelSetUp(){
        //sets up the addNew panel with necessary buttons

        addNewPanel.add(back, BorderLayout.NORTH);
        addNewPanel.add(addNewS, BorderLayout.CENTER);
        addNewPanel.add(addNewP, BorderLayout.SOUTH);

    }

    public void showAllsetUp(){
        //sets up the panel to show all articles
        //back button gets added in action performed

        // get info from CSV
        ArrayList<ArrayList> myData = myCSV.displayArticles();

        int numArticles = myData.size();
        JLabel counter = new JLabel("Total Articles: " + Integer.toString(numArticles));

        //create container panel
        seeAllPanel.setLayout( new BorderLayout());

        //create a JPanel to hold back & search buttons (top)
        topButtons = new JPanel();
        topButtons.setLayout( new BoxLayout( topButtons, BoxLayout.X_AXIS));

        //add buttons (back button may need to be added in actionPerformed)
        topButtons.add(search);
        topButtons.add(Box.createRigidArea(new Dimension (10, 10)));
        //topButtons.add(back);

        //create a JPanel to hold the items with a scroll bar
        JPanel holdItems = new JPanel();
        holdItems.setLayout ( new GridLayout (0, 3, 10, 10));
        //holdItems.setPreferredSize ( new Dimension (seeAllPanel.getWidth() - 20, seeAllPanel.getHeight() - 40));
        

        //create a JPanel to show number of articles (bottom)
        JPanel bottom = new JPanel();
        bottom.setLayout( new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.add(counter);

        //add info to scroll panel
        for (int i = 0; i < myData.size(); i++){
            //loop through each article 
            //get the article type for the header
            String tempString = myData.get(i).get(0) + ": \n";

            for (int j = 1; j < myData.get(i).size(); j++){
                //loop through the attributes of each article
                tempString = tempString + myData.get(i).get(j) + "\n";
                }

            JTextArea tempArea = new JTextArea(tempString);
            //System.out.println("Row num" + myData.get(i).size());
            //tempArea.setRows(myData.get(i).size());

            holdItems.add(tempArea);
            //holdItems.setPreferredSize(new Dimension (seeAllPanel.getWidth() - 20, tempArea.getHeight() * 3));
            }
        
        //add scroll panel to holdItems panel

        JScrollPane scrollPane = new JScrollPane(holdItems);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize( new Dimension (seeAllPanel.getWidth() - 20, seeAllPanel.getHeight() - 40));

        //add all sub-panels to seeAllPanel
        seeAllPanel.add(topButtons, BorderLayout.PAGE_START);
        seeAllPanel.add(scrollPane, BorderLayout.CENTER);
        seeAllPanel.add(bottom, BorderLayout.PAGE_END);

    }


    public void confirmEntry(String articleType){
        //takes the text boxes and adds the info to csv
        //slightly changes based on the type of Article added

        String filename = name.getText();

        System.out.println("file found");

        //create an empty arraylist to hold the info later
        ArrayList<JTextField> paramsBase = new ArrayList<>();
        String[] params;

        if (articleType.equals("Shirt")){
        //create an arraylist & parameter array for shirts
            //create an arraylist of the attributes
            paramsBase = new ArrayList<>(Arrays.asList(color, brand, size, misc, location, sleeveLength, shirtLength));
            //create an array of the categories
            params = new String[] {"Color", "Brand", "Size", "Misc", "Location", "Sleeve Length", "Shirt Length"};
        } else {
            //if the articleType is Pants
            System.out.println("Article is pants");
            paramsBase = new ArrayList<>(Arrays.asList(color, brand, size, misc, location, rise, length, style, material));
            //create an array of the categories
            params = new String[] {"Color", "Brand", "Size", "Misc", "Location", "Rise", "Length", "Style", "Material"};
        }

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
        if (myCSV.addAnyArticleCSV(params, articleType)){
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
    }


    public void addNewPants(){
        //sets up the panel for adding new pants
        //back button is added in action performed
        addNewPPanel.setLayout( new BorderLayout());

        //make a subpanel for the top (name & title)
        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BoxLayout(topPanel, BoxLayout.X_AXIS));
        //add name and label to topPanel
        topPanel.add( new JLabel ("Name: " + nameSet));
        topPanel.add( Box.createRigidArea(new Dimension(100, 10)));
        JLabel title = new JLabel("ADD NEW PANTS:");
        title.setBackground(Color.PINK);
        title.setOpaque(true);
        topPanel.add( title);

        //center panel to hold the labels & text boxes
        JPanel center = new JPanel();
        center.setLayout( new GridLayout(0, 2, 10, 10));

        //make a sub panel for the add & back buttons
        //(back button is added in action performed)
        bottomButtons = new JPanel();
        bottomButtons.setLayout( new BoxLayout( bottomButtons, BoxLayout.X_AXIS));

        //add confirm search button
        bottomButtons.add(confirmNewPants);
        //add a space between search & back
        bottomButtons.add( Box.createRigidArea(new Dimension (10, 10)));


        //info setup:
        //make the array of labels
        String[] compLabels = {"Color: ", "Brand: ", "Size: ", "Misc: ", 
        "Location: ", "Rise: ", "Length: ", "Material: ", "Style: "};

        //set up the text fields
        color = new JTextField("e.g. Blue");
        brand = new JTextField("e.g. Zara");
        size = new JTextField("e.g. 28");
        misc = new JTextField("e.g. Ripped");
        location = new JTextField("e.g. Drawer");
        rise = new JTextField("e.g. Low");
        length = new JTextField("e.g. Long");
        material = new JTextField("e.g. Denim");
        style = new JTextField("e.g. Mom");

        //make an array of the text fields
        JTextField[] compText = {color, brand, size, misc, location, rise, length,
            material, style};

        //add the info to the center panel (each row is label, text box)
        for (int i = 0; i < compText.length; i++){
            //loop through length of the arrays
            //add the labels
            center.add( new JLabel(compLabels[i]));
            //set the text box color to gray
            compText[i].setForeground(Color.GRAY);
            //add the text boxes
            center.add(compText[i]);
        }

        //add the subPanels to the parent Panel (addNewPPanel)
        addNewPPanel.add(topPanel, BorderLayout.PAGE_START);
        addNewPPanel.add(center, BorderLayout.CENTER);
        addNewPPanel.add(bottomButtons, BorderLayout.PAGE_END);
    }


    public void searchPanelSetUp(){
        //sets up the JPanel for searching items
        //comes up if the search/filter button is pressed

        //Attribute label & text field
        JLabel attribute = new JLabel("Type Attribute: ");
        searchPanel.add(attribute);

        giveAttribute = new JTextField("black");
        giveAttribute.setForeground(Color.GRAY);
        searchPanel.add(giveAttribute);

        //article type label 
        JLabel articleCat = new JLabel("Select Article Type: ");
        searchPanel.add(articleCat);

        //Article drop down menu
        String[] articleOptions = {"All", "Shirts", "Pants"};
        artOpt = new JComboBox<String>(articleOptions);
        searchPanel.add(artOpt);

        //category label
        JLabel category = new JLabel("Select Category: ");
        searchPanel.add(category);

        //category drop down menu
        String[] categoryOptions = {"None", "Color", "Brand", "Size", "Location", "Misc", "Shirt Length",
            "Sleeve Length", "Pant Rise", "Pant Length", "Pant Style", "Pant Material"};
        catOpt = new JComboBox<String>(categoryOptions);
        searchPanel.add(catOpt);

        //add the back button
        searchPanel.add(back);

        //add the button to search 
        searchPanel.add(searchArticles);

    }

    public void searchClosetDisplay(){
        //sets up the display panel for searching
        //using nested JPanels for formatting & scrolling 

        //outer panel is generic BorderLayout
        searchResultPanel.setLayout(new BorderLayout());

        //get the given fields from the search page
        String attribute = giveAttribute.getText();
        String articleType = (String) artOpt.getSelectedItem();
        String categoryType = (String) catOpt.getSelectedItem();

        //get the arrayList of results from CSVReader
        ArrayList<ArrayList> results = myCSV.displaySearch(attribute, categoryType, articleType);

        //get the total number of results shown
        int numArticles = results.size();
        JLabel counter = new JLabel("Total Articles: " + Integer.toString(numArticles));

        //add the back button at the top
        searchResultPanel.add(back, BorderLayout.PAGE_START);
        
        //create new JPanel to be nested
        JPanel searchResNest = new JPanel();
        searchResNest.setLayout (new GridLayout(0, 3, 10, 10));
        searchResNest.setPreferredSize(new Dimension ( searchResultPanel.getWidth(), searchResultPanel.getHeight() - (searchResultPanel.getHeight()/10)));

        //****** in case I want to add a label that says results for: ***** */
        // String resultString = "Showing Results for: " + articleType + " , " + categoryType 
        // + " , " + attribute;

        // searchResNest.add(new JLabel("Showing Results for: " + attribute));
        // searchResNest.add(new JLabel("Article Type: " + articleType));
        // searchResNest.add(new JLabel(" Category: " + categoryType));

   
        for (int i = 0; i < results.size(); i++){
            //loop through each article 
            //get the article type for the header
            String tempString = results.get(i).get(0) + ": \n";

            for (int j = 0; j < results.get(0).size(); j++){
                //loop through the attributes of each article

                tempString = tempString + results.get(i).get(j) + "\n";

                }

            //display in a JTextArea
            JTextArea temp = new JTextArea(tempString);
            //add to the inner panel
            searchResNest.add(temp);
            
            }
        //add inner panel to a scrollPane
        JScrollPane scrollPane = new JScrollPane(searchResNest);
        //add scrollPane to the main panel
        searchResultPanel.add(scrollPane, BorderLayout.CENTER);
        //add a total # of search results at the bottom
        searchResultPanel.add(counter, BorderLayout.PAGE_END);

    }

    

    public void addNewShirt(){
        //adds a new Shirt

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
        addNewSPanel.add(confirmNewShirt, c);


        // cl.show(homeContainer, "Add New Shirt");

    }

}