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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lynn
 */
@Named(value = "carDetailBean")
@SessionScoped
public class carDetailBean implements Serializable {
 private Car carChecked;
 private String tradeCustomer;
       private String tradeServer;
       private String carVIN;
       private String tradeDate;
       private Sale salesRecord;
       private String saleStatus;
       private List<Sale> saleList;
       private Integer serverNumber;
         @EJB
       CarSalesInterface manipulateRecord;
    /**
     * Creates a new instance of carDetailBean
     */
         
   public Users getCurrentCustomer(){
      ELContext context = FacesContext.getCurrentInstance().getELContext();
      LoginManagedBean loginBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"loginManagedBean");
      this.setTradeCustomer(loginBean.getCurrentUser().getFirstName());
      return loginBean.getCurrentUser();
    }
  
    public Integer getCurrentServerNum(){
      ELContext context = FacesContext.getCurrentInstance().getELContext();
      mainPageBean mainPageBean = (mainPageBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"mainPageBean");
    //  this.setTradeCustomer(mainPageBean.getCurrentUser().getFirstName());
      return mainPageBean.getServerNum();
    }
    
   public String buyCarAction() throws Exception{
       this.setSaleStatus("Paid");
       Date date = new Date();
       DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       String time = format.format(date);
       //this.carChecked.setCarStatus("Sold");
       System.out.println("**************Server Number"+ this.getCurrentServerNum());
       manipulateRecord.addRecord(this.getCurrentCustomer().getId(), carChecked.getVIN(),1,time, this.getSaleStatus());
       String carVIN = carChecked.getVIN();  
       this.setSaleList(manipulateRecord.displayARecord(carVIN));
       return "aRecordPage";
   }
   
    public carDetailBean() {
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

    public Sale getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(Sale salesRecord) {
        this.salesRecord = salesRecord;
    }

    public CarSalesInterface getManipulateRecord() {
        return manipulateRecord;
    }

    public void setManipulateRecord(CarSalesInterface manipulateRecord) {
        this.manipulateRecord = manipulateRecord;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public Integer getServerNumber() {
        return serverNumber;
    }

    public void setServerNumber(Integer serverNumber) {
        this.serverNumber = serverNumber;
    }
    
  
}
