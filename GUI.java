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

    //panels
    private JPanel homePanel;
    private JPanel addNewPanel;
    private JPanel addNewSPanel;
    private JPanel addNewPPanel;
    private JPanel seeAllPanel;
    private JPanel homeContainer;
    private JPanel entryPanel;

    //buttons
    private JButton addNew;
    private JButton addNewS;
    private JButton addNewP;
    private JButton confirmNew;
    private JButton seeAll;
    private JButton back;
    private JButton nameButton;

    //text fields
    private JTextField name;
    private JTextField color;
    private JTextField brand;
    private JTextField size;
    private JTextField misc;
    private JTextField location;
    private JTextField sleeveLength;
    private JTextField shirtLength;


        //**** hard coding this FOR NOW**** */
        String nameSet = "testcloset";

    public GUI(){

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
        seeAllPanel.add(new JLabel("See All"));

        //add it to the container
        //back button is added in actionPerformed
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


        addNewPPanel = new JPanel(new GridBagLayout());
        homeContainer.add(addNewPPanel, "Add New Pants");



        //set up the buttons
        addNew.addActionListener(this);
        seeAll.addActionListener(this);
        back.addActionListener(this);
        addNewS.addActionListener(this);
        addNewP.addActionListener(this);
        confirmNew.addActionListener(this);


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




    public void actionPerformed(ActionEvent e){
        // @override

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
                // cl.show(homeContainer, "See All");
                //seeAllPanel.add(back);
            }
        else if (e.getSource() == back){
            
                cl.show(homeContainer, "Main Menu");
            }
        else if (e.getSource() == addNewS){
            addNewItem();
        } 
        else if (e.getSource() == addNewP){
            addNewPants();
        }
        else if (e.getSource() == confirmNew){
            //enter a new article
            confirmEntry();
        }
    }


    public void showAll(){

        seeAllPanel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        


  //c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        seeAllPanel.add(back, c);

        //set up as a gridbaglayout
        CSVReader myCSV = new CSVReader(nameSet);
        
        
        ArrayList<ArrayList> myData = myCSV.displayArticles(nameSet + ".csv");

        System.out.println("post arraylist");

        System.out.println("mydata: " + myData);
        for (int i = 0; i < myData.size(); i++){
            String tempString = myData.get(i).get(0) + ": \n";
            System.out.println("i: " + i);
            for (int j = 0; j < myData.get(0).size(); j++){
                System.out.println("j: " + j);
                tempString = tempString + myData.get(i).get(j) + "\n";
                }
            System.out.println("new article: " + (i % 3));
            c.gridx = i % 3;
            c.gridy = (myData.size() / 3) + 1;
            c.gridwidth = 1;
            c.weightx = 0.3;
            c.weighty = 0.5;
            JTextArea temp = new JTextArea(tempString);
            seeAllPanel.add(temp, c);
            //System.out.println(tempString);
            }
        //scroll?
        //search button

        cl.show(homeContainer, "See All");

    }


    public void confirmEntry(){
        //takes the text boxes and adds the info to csv
        System.out.println("Button Pushed");

        CSVReader csv = new CSVReader(nameSet);

        String filename = name.getText();

        if (!csv.fileExists(filename)){
            csv.createNewCSV(filename);
        } 

        System.out.println("file created");

        ArrayList<JTextField> paramsBase = new ArrayList<>(Arrays.asList(brand, size, misc, location, sleeveLength, shirtLength));
        String[] params = {"Color", "Brand", "Size", "Misc", "Location", "Sleeve Length", "Shirt Length"};

        for (JTextField s : paramsBase){
            if (s.getText().isEmpty()){
                String temp = "X ";
                params[paramsBase.indexOf(s)] = temp;
            } else{
                params[paramsBase.indexOf(s)] = s.getText();
            }

        }

        // String[] params = {color.getText(), brand.getText(), size.getText(), 
        //     misc.getText(), location.getText(), sleeveLength.getText(), shirtLength.getText()};


        csv.addShirtCSV(params, filename);

        System.out.println("Shirt added sucessfully");

        JPanel success = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.weighty = 0.5;

        c.gridx = 0;
        c.gridy = 0;
        success.add(new JLabel("Add Another?"), c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        success.add(addNew, c);

        c.gridx = 0;
        c.gridy = 1;
        success.add(new JLabel("Back to Home"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        success.add(back, c);

        c.gridx = 0;
        c.gridy = 2;
        success.add(new JLabel("See All Entries"), c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        success.add(seeAll, c);

        homeContainer.add(success, "Item Added");
        cl.show(homeContainer, "Item Added");
    }


    public void addNewPants(){
        //GridBagConstraints c = new GridBagConstraints();
        addNewPPanel.add(back);
        cl.show(homeContainer, "Add New Pants");
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
        name = new JTextField("e.g. Annabel");
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


        cl.show(homeContainer, "Add New Shirt");

    }

}