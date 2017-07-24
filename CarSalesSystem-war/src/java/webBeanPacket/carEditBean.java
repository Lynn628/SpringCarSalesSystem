/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import CarSearch.CarSalesInterface;
import entities.Car;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lynn
 */
@Named(value = "carEditBean")
@SessionScoped
public class carEditBean implements Serializable {
      private String VIN;
       private String modelName;
       private String modelNo;
       private String manufacturer;
       private String type;
       private String status;
       private String producedDate;
       private String description;
       private String imageAddress;
    /**
     * Creates a new instance of carEditBean
     */
    public carEditBean() {
    }
       private Car car;
       private Car targetCar;
       private List<Car> carList;
       @EJB(name="carsalesBean")
       private CarSalesInterface manipulateCar;
    /**
     * Creates a new instance of displayCarDetailBean
     */
   
 
 public void editFunction() {
  
         this.setCar(targetCar);
    }
   
    
    public  void updateCar() throws Exception{
            if(car != null){
        manipulateCar.updateCar(car);
            }else{
                System.out.println("*******************car is empty");
            }
    }
    
   
    public void deleteCar() throws Exception{

    System.out.println("****************From delete car method");
    String VIN = targetCar.getVIN();
    manipulateCar.deleteCar(VIN);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

      
    
    public void addCar() throws Exception{

   manipulateCar.addCar(car.getVIN(),car.getModelName(),car.getModelNo(),car.getManufacturer(),car.getType(),
                car.getCarStatus(),car.getDescription(),car.getThumbnail());
    }
    
    public List<Car> getAllCar()throws Exception{
       List<Car> cars = manipulateCar.getAllCar();
       this.setCarList(cars);
       return this.getCarList();
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public CarSalesInterface getManipulateCar() {
        return manipulateCar;
    }

    public void setManipulateCar(CarSalesInterface manipulateCar) {
        this.manipulateCar = manipulateCar;
    }
    
     public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(String producedDate) {
        this.producedDate = producedDate;
    }

    public Car getTargetCar() {
        return targetCar;
    }

    public void setTargetCar(Car targetCar) {
        this.targetCar = targetCar;
    }
    
    
}
