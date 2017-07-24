/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appliclientGui;

import CarSearch.NetworkUtils;
import entities.Car;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.Label.CENTER;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Lynn
 */
public class SystemGuiImpl  extends JFrame implements SystemGuiInterface{
    private static final String[] TABLE_COLUMNS = {"VIN", "Manufacture","Model Name"};
    //Set label
    private JLabel manufactureLabel;
    private JLabel modelNameLabel;
    private JLabel modelNumberLabel;
    private JLabel typeLabel;
    
    private JTextField manufactureText;
    private JTextField modelNameText;
    private JTextField modelNumberText;
    private JTextField typeText;
    
    //Set button
    private JButton searchButton;
    private JButton closeButton;
    private JLabel pictureLabel;
    //Set text area
    private JTextArea carDetail;
    
    //Set table
    private JTable carTable;
    
    //Set panel
    private JPanel panelOne;
    private JPanel buttonPanel;
    private JPanel panelTwo;
   
    private JPanel panelThree;
    Car car;
    public SystemGuiImpl(ActionListener actionListener, ListSelectionListener listSelectionListener){
        super("Car Sales System");
        //Create labels
        this.manufactureLabel = new JLabel("Manufacturer:");
        this.modelNameLabel = new JLabel("Model Name:");
        this.modelNumberLabel = new JLabel("Model No.:");
        this.typeLabel = new JLabel("Type:");
        
        this.manufactureText = new JTextField();
        this.modelNameText = new JTextField();
        this.modelNumberText = new JTextField();
        this.typeText = new JTextField();
         // create container
        Container container = this.getContentPane();
        //Create button
        this.searchButton = new JButton("Search");
        this.closeButton = new JButton("Close");
        
          // create table
        this.carTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.carTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.carTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel carTableColumnModel = this.carTable.getColumnModel();
        carTableColumnModel.getColumn(0).setPreferredWidth(300);
        carTableColumnModel.getColumn(1).setPreferredWidth(200);
        carTableColumnModel.getColumn(2).setPreferredWidth(200);
        
        //Create text area 
        this.carDetail = new JTextArea();
        this.carDetail.setEditable(false);
        
        // create panels
        this.panelOne = new JPanel();
        this.buttonPanel = new JPanel();
        this.panelTwo = new JPanel();
        this.pictureLabel = new JLabel();
        this.panelThree = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout(10,10));
        this.panelOne.setLayout(new GridLayout(4,2));
        this.buttonPanel.setLayout(new GridLayout(1,2));
        this.panelTwo.setLayout(new GridLayout(1,2,10,10));
        this.panelThree.setLayout(new GridLayout(2,1,10,10));
        panelThree.setSize(500, 300);
        
        panelOne.add(manufactureLabel);
        panelOne.add(manufactureText);
        panelOne.add(modelNameLabel);
        panelOne.add(modelNameText);
        panelOne.add(modelNumberLabel);
        panelOne.add(modelNumberText);
        panelOne.add(typeLabel);
        panelOne.add(typeText);
       
       
        panelTwo.add(pictureLabel);
        panelTwo.add(carDetail);
        
         panelThree.add(new JScrollPane(this.carTable),BorderLayout.NORTH);
         panelThree.add(panelTwo);
        
        buttonPanel.add(searchButton);
        buttonPanel.add(closeButton);
        searchButton.addActionListener(actionListener);
        closeButton.addActionListener(actionListener);
        container.add(panelOne,BorderLayout.NORTH);
        container.add(panelThree,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
        
        this.pack();
        this.setSize(700,600);
        this.setVisible(true);
    }
      
//    public static void main(String[] args){
//       new SystemGuiImpl().show();
//    }
    @Override
    public void clearTextFields() {
          this.manufactureText.setText("");
          this.modelNameText.setText("");
          this.modelNumberText.setText("");
          this.typeText.setText("");
    }
    
    @Override
    public void clearCarTable(){
        int numberOfRow = this.carTable.getModel().getRowCount();
        
        if(numberOfRow > 0){
            DefaultTableModel tableModel = (DefaultTableModel)this.carTable.getModel();
            for(int index = (numberOfRow - 1); index >= 0; index--){
                tableModel.removeRow(index);
            }
        }
    }

      @Override
    public void displayMessageInDialog(String message) {
      JOptionPane.showMessageDialog(this, message);
    }
    //show car infromation in the table
    @Override
    public void displayCarDetails(Car car) {
       this.clearCarTable();
       ((DefaultTableModel)this.carTable.getModel()).addRow(new Object[]{
           car.getManufacturer(), car.getModelName()});
       
       }
    //Show selected item information in the textarea
    @Override
    public void displaySelectedCarDetails(Car car) throws Exception{
        clearTextFields();
        String imagePath = "http://localhost:8080/CarSalesSystem-war/"+car.getThumbnail();
        BufferedImage image1= ImageIO.read(NetworkUtils.getImageStream(imagePath));
        
        ImageIcon image = new ImageIcon(image1);
        image.setImage(image.getImage().getScaledInstance(300,280, Image.SCALE_DEFAULT));
        this.pictureLabel.setIcon(image);
        System.out.println("Before text area");
       displayInTextArea(car);
        
    }
    //show searched cars information in the table
    @Override
    public void displayCarDetails(List<Car> car) {
     this.clearCarTable();
     for(Car itemCar: car){
          ((DefaultTableModel)this.carTable.getModel()).addRow(new Object[]{itemCar.getVIN(),
           itemCar.getManufacturer(), itemCar.getModelName()});
            }
    }
     public boolean isCarSelected() {
        return (this.carTable.getSelectedRow() >= 0);
    }
    public JTable getCarTable() {
        return carTable;
    }

    
    
    @Override
    public String getSelectedCarVIN() throws Exception {
      int SelectedRowIndex = this.carTable.getSelectedRow();
      String carVIN = this.carTable.getValueAt(SelectedRowIndex,0).toString();
      return carVIN;
    }

     
    @Override
    public JButton getSearchButton() {
          return searchButton;
    }

    @Override
    public JButton getCloseButoon() {
             return closeButton;
    }

   
    public JTextArea getCarDetail() {
        return carDetail;
    }

    /**
     * Collect searching requirment
     * @return 
     * @throws Exception 
     */
    @Override
    public Car searchCar() throws Exception {
           Car searchModel = new Car(this.manufactureText.getText(), this.modelNameText.getText(),
          this.modelNumberText.getText(), this.typeText.getText());
           System.out.println("GUI Input display*****************"+this.modelNumberText.getText()+ this.modelNameText.getText()+
           this.manufactureText.getText()+ this.typeText.getText());
           return searchModel;
    }

    
    public void displayInTextArea(Car car){

       String carDate ="null"; 
       Date producedDate = car.getProducedDate();
       if(producedDate != null){
         carDate = producedDate.toString();
       }
      
//       String description = car.getDescription();
//       if(description == null){
//           description = "";
//       }
       String out = "VIN: "+ car.getVIN() +"\nModel Name: "+ car.getModelName()+"\n Model Number: "+
               car.getModelNo()+ "\n Manufacturer "+car.getManufacturer()
                +"\n Type: "+ car.getType()+"\n Date Produced:" + carDate+"\n Description: "+car.getDescription();   
        carDetail.setText(out);
    }
    
  }
