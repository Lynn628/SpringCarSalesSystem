/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclicentMain;

import appliclientGui.SystemGuiImpl;
import entities.Car;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ejb.EJB;
import CarSearch.CarSalesInterface;

/**
 *
 * @author Lynn
 */
public class CarSalesSystemMain  implements ActionListener, ListSelectionListener{
    @EJB(name ="carsalesBean")
    private  static CarSalesInterface carRepository ;
    private SystemGuiImpl gui;
    public CarSalesSystemMain(){
     
    }
    
    public void initView(){
        this.gui = new SystemGuiImpl(this,this);
    }
    
    public static void main(String[] args) {
        try{
       new CarSalesSystemMain().initView();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == gui.getSearchButton()){
        this.SearchCar();
        System.out.println("**********this is to test search button");
    }else if(e.getSource() == gui.getCloseButoon()){
        this.carRepository.close();
        System.out.println("****************Test close");
        System.exit(0);
      }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
         if ((e.getSource() == this.gui.getCarTable().getSelectionModel())
            && (! e.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isCarSelected()) {
                    String carVIN = this.gui.getSelectedCarVIN();
                    Car carSelected = this.carRepository.searchCarByVIN(carVIN);
                    //System.out.println("**************select car****");
                    this.gui.displaySelectedCarDetails(carSelected);
                }
             }catch(Exception ex){
                        ex.printStackTrace();
                        }
                
       }
    }
    
    public void SearchCar(){  
        try{
         Car carSearched = gui.searchCar();
         String manufacturer = carSearched.getManufacturer();
         String modelName = carSearched.getModelName();
         String modelNumber = carSearched.getModelNo();
         String type = carSearched.getType();
       System.out.println("*****************Test searchCar method's car retrived in main method before"+ manufacturer+ modelName + modelNumber+ type);
         List<Car> carRetrieved = carRepository.SearchCar(manufacturer, modelName, modelNumber, type);
         if(!carRetrieved.isEmpty()){
              System.out.println("*****************Test searchCar method's car retrived in main method"+ manufacturer+ modelName + modelNumber+ type);
             displayCarInTable(carRetrieved);
         }else{
                 this.gui.displayMessageInDialog("Car Not Found ");
         }
    }catch(Exception e){
        this.gui.displayMessageInDialog("Failed to find the car" + e.getMessage());
    } 
 }
    
    public void displayCarInTable(List<Car> carList){
        gui.displayCarDetails(carList);
    }
    
}
