//GUI.java
import java.io.*;
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


public class GUI implements ActionListener{

    private JFrame frame;
    private JPanel homePanel;
    private JPanel addNewPanel;
    private JPanel addNewSPanel;
    private JPanel seeAllPanel;
    private JPanel homeContainer;
    private JButton addNew;
    private JButton addNewS;
    private JButton addNewP;
    private JButton seeAll;
    private JButton back;
    private CardLayout cl;

    public GUI(){

        //setting a frame and a card layout
        frame = new JFrame();
        cl = new CardLayout(200,200);

        //making buttons to switch cards
        addNew = new JButton("Add New");
        seeAll = new JButton("See All");
        back = new JButton("Back to Home");

        addNewS = new JButton("Add New Shirt");
        addNewP = new JButton("Add New Pants");

        //Main Menu panel (homeContainer)
        homeContainer = new JPanel(cl);

        //Main panel
        homePanel = new JPanel();
        homePanel.add(addNew);
        homePanel.add(seeAll);

        homeContainer.add(homePanel, "Main Menu");

        //set up see all panel
        seeAllPanel = new JPanel();
        seeAllPanel.add(new JLabel("See All"));

        //add it to the container
        //back button is added in actionPerformed
        homeContainer.add(seeAllPanel, "See All");
        


        //set up add new panel
        addNewPanel = new JPanel(new GridLayout(4, 1));
        addNewPanel.add(new JLabel("Add New"));
        
        //add it to the container
        //back button is added in actionPerformed
        homeContainer.add(addNewPanel, "Add New");

        //set up add newShirt panel
        addNewSPanel = new JPanel(new GridLayout(5,1));
        homeContainer.add(addNewSPanel, "Add New Shirt");



        //set up the buttons
        addNew.addActionListener(this);
        seeAll.addActionListener(this);
        back.addActionListener(this);
        addNewS.addActionListener(this);
        addNewP.addActionListener(this);


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
        if (e.getSource() == addNew){
            
                cl.show(homeContainer, "Add New");
                addNewPanel.add(back, BorderLayout.NORTH);
                addNewPanel.add(addNewS, BorderLayout.CENTER);
                addNewPanel.add(addNewP, BorderLayout.SOUTH);
                // addNewItem();
            }
        else if (e.getSource() == seeAll){
            
                cl.show(homeContainer, "See All");
                seeAllPanel.add(back);
            }
        else if (e.getSource() == back){
            
                cl.show(homeContainer, "Main Menu");
            }
        else if (e.getSource() == addNewS){
            addNewItem();
        }
    }

    public void addNewItem(){

        //set up the layout
        addNewSPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.ipady = 20;

        c.gridx = 0;
        c.gridy = 0;

        addNewSPanel.add(back, c);

        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 20;
        addNewSPanel.add(new JLabel("Brand: "), c);

        c.gridx = 2;
        c.gridy = 1;
        //c.ipady = 20;
        c.ipady = 20;
        addNewSPanel.add(new JTextField("ex: Zara"), c);

        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 20;
        addNewSPanel.add(new JLabel("Size: "), c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipady = 20;
        addNewSPanel.add(new JTextField(), c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 20;
        addNewSPanel.add(new JLabel("Color: "), c);

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 20;
        addNewSPanel.add(new JTextField(), c);

        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 20;
        addNewSPanel.add(new JLabel("Location: "), c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 2;
        c.ipady = 20;
        addNewSPanel.add(new JTextField(), c);
        cl.show(homeContainer, "Add New Shirt");

    }

}