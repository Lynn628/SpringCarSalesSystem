/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import CarSearch.CarSalesInterface;
import entities.Car;
import entities.Sale;
import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lynn
 */
@Named(value = "carSearchBean")
@SessionScoped
//@RequestScoped
public class carSearchBean implements Serializable {
           
   //private String VIN;
   private String modelNo;
   private String modelName;
   private String manufacturer;
   private String type;
   private Car carChecked;
   private Users currentCarSearchUser;
   private String tradeCustomer;
   private  List<Car> carList;  
  
//   private String tradeServer;
//   private String carVIN;
//   private String tradeDate;
//   private Sale salesRecord;
//   private String saleStatus;
//    @EJB
//  private  CarSalesInterface manipulateRecord;

    @EJB
   private  CarSalesInterface  carSearch;

    /**
     * Creates a new instance of carDetailBean
     */
         
   public Users getCurrentCustomer(){
      ELContext context = FacesContext.getCurrentInstance().getELContext();
      LoginManagedBean loginBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"loginManagedBean");
      this.setTradeCustomer(loginBean.getCurrentUser().getFirstName());
      return loginBean.getCurrentUser();
    }
      
   
      public void salesPersonRetrieveCar( ) throws Exception{
        this.setCurrentCarSearchUser(this.getUserIdentity());
        List<Car> cars = carSearch.SearchCar(this.getManufacturer(),this.getModelName(), this.getModelNo(), this.type);
        
        if(!cars.isEmpty()){
           this.setCarList(cars);
              System.out.println("**********************dispalyaPAgeSize is " + cars.size());
         //return "carTablePage";
           
       }
        else{  
            this.setCarList(cars);
           // System.out.println("**********************dispalyaPAgeNull");
         // return "carSearchPage";
        }
    }
    
    public void customerRetrieveCar( ) throws Exception{
        this.setCurrentCarSearchUser(this.getUserIdentity());
        //System.out.println("********************"+ currentCarSearchUser.getEmail());
        List<Car> cars = carSearch.customerCarSearch(this.getManufacturer(),this.getModelName(), this.getModelNo(), this.type);
       
        if(!cars.isEmpty()){
           this.setCarList(cars);
              System.out.println("**********************dispalyaPAgeSize is " + cars.size());         
       }
        else{  
            this.setCarList(cars);
           // System.out.println("**********************dispalyaPAgeNull");
         // return "carSearchPage";
        }
    }

   public String returnToSearchPage() throws Exception{
       this.customerRetrieveCar();
       return "carSearchPage";
   }
    
    public Users getUserIdentity(){
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginPerson = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
       
        return loginPerson.getCurrentUser();
    }
  

    public Car getCarChecked() {
        return carChecked;
    }

    public void setCarChecked(Car carChecked) {
        this.carChecked = carChecked;
    }

    public String getTradeCustomer() {
        return tradeCustomer;
    }

    public void setTradeCustomer(String tradeCustomer) {
        this.tradeCustomer = tradeCustomer;
    }

  


    public carSearchBean() {
    }
    
 
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Users getCurrentCarSearchUser() {
        return currentCarSearchUser;
    }

    public void setCurrentCarSearchUser(Users currentCarSearchUser) {
        this.currentCarSearchUser = currentCarSearchUser;
    }
 
    
  
}
