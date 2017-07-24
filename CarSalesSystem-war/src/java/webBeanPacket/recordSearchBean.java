/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import CarSearch.CarSalesInterface;
import entities.Sale;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lynn
 */
@Named(value = "recordSearchBean")
@RequestScoped
public class recordSearchBean {

    private Integer userId;
    private Long salesId;
    private String carVIN;
    private List<Sale> recordFoundList;
    @EJB
    private CarSalesInterface manipulateRecord;

    /**
     * Creates a new instance of recordSearchBean
     */
    public recordSearchBean() {
    }

    public void findRecord() throws Exception {

        System.out.println("******************* before find Rcords method");
        recordFoundList = manipulateRecord.searchRecords(this.salesId, this.userId, this.carVIN);

        System.out.println("******************* after find Rcords method" + recordFoundList.toString());
//        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long saleId) {
        this.salesId = salesId;
    }

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public List<Sale> getRecordFoundList() {
        return recordFoundList;
    }

    public void setRecordFoundList(List<Sale> recordFoundList) {
        this.recordFoundList = recordFoundList;
    }

}
