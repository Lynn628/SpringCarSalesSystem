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
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lynn
 */
@Named(value = "buyCarBean")
@SessionScoped
public class buyCarBean implements Serializable {
       private String tradeCustomer;
       private String tradeServer;
       private String carVIN;
       private String tradeDate;
       private Sale salesRecord;
       @EJB
       CarSalesInterface manipulateRecord;
    /**
     * Creates a new instance of buyCarBean
     */
    public buyCarBean() {
       this.tradeCustomer = this.getCurrentCustomer().getFirstName();
       this.carVIN = this.getCurrentSelectCar().getVIN();
       this.tradeServer = "1";
       this.tradeDate = "2016-08-09";
        
    }

    
    public Users getCurrentCustomer(){
      ELContext context = FacesContext.getCurrentInstance().getELContext();
      LoginManagedBean loginBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"loginManagedBean");
      this.setTradeCustomer(loginBean.getCurrentUser().getFirstName());
      return loginBean.getCurrentUser();
    }
    
    
    public Car getCurrentSelectCar(){
       ELContext context = FacesContext.getCurrentInstance().getELContext();
      carSearchBean carBean = (carSearchBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"carSearchBean");
      this.setCarVIN(carBean.getCarChecked().getVIN());
      return carBean.getCarChecked();
    }
    
    public void getCurrentTime(){
        this.setTradeDate("2016/8/9");
        
    }
    
    public void payCar(){
         String userName = this.getTradeCustomer();
         String carVIN = this.getCarVIN();
         System.out.println("**********************"+userName+carVIN);
        manipulateRecord.addRecord(2, carVIN,1,"2016", carVIN);
    }
    
    public void waitToPayCar(){
        
    }
            
    public void getCurretnSalesPerson(){
        this.setTradeServer("1");
    }
    
    public String getTradeCustomer() {
        return tradeCustomer;
    }

    public void setTradeCustomer(String tradeCustomer) {
        this.tradeCustomer = tradeCustomer;
    }

    public String getTradeServer() {
        return tradeServer;
    }

    public void setTradeServer(String tradeServer) {
        this.tradeServer = tradeServer;
    }

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public void setSalesRecord(Sale salesRecord) {
        this.salesRecord = salesRecord;
    }

    public Sale getSalesRecord() {
        return salesRecord;
    }
    
    
    
}
