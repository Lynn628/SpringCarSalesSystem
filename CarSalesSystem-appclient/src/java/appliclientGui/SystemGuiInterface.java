/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appliclientGui;

import entities.Car;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Lynn
 */
public interface SystemGuiInterface {
    //Clear all the textFiled 
    void clearTextFields();
     //Clear the table
    void clearCarTable();
 
    /**
     * Display a message in a dialog box
     *
     * @param message - the message to display
     */
    void displayMessageInDialog(String message);

    /**
     * Display the details of the car
     *
     * @param car - the details of the car to display
     */
    void displayCarDetails(Car car);
    
    /**
     * Display the details of the selected car
     *
     * @param car - the details of the selected car to display
     */
    void displaySelectedCarDetails (Car car) throws Exception;
    
    /**
     * Display the details of the cars
     *
     * @param car - the details of the car to display
     */
    void displayCarDetails(List<Car> car);
    /**    
     * Collect the model related information used for search cars
     */
    Car searchCar() throws Exception;
    
    String getSelectedCarVIN() throws Exception;
    /**
     * Return the search button
     */
    public JButton getSearchButton();
    
    public JButton getCloseButoon();
    
    public boolean isCarSelected();
}
