
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lynn
 */
public class carSearched {
   private String VIN;
   private String modelNo;
   private String modelName;
   private String manufacturer;
   private String type;
   private String thumbnail;
   private String description;
   private String carStatus;
   private Date producedDate;

    public carSearched(String VIN, String modelNo, String modelName, String manufacturer, String type, String thumbnail, String description, String carStatus, Date producedDate) {
        this.VIN = VIN;
        this.modelNo = modelNo;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
        this.carStatus = carStatus;
        this.producedDate = producedDate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public Date getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }
   
}
// <h:column>
//                       <h:commandButton   value="Put Into Cart" action="#{carDetailBean.putCarIntoCartAction()}"/>
//                       <f:setPropertyActionListener target="#{carDetailBean.carChecked}" value="#{car}"/>
//             </h:column>
//                <h:column>
//                       <h:commandButton  value="Buy It" action="#{carDetailBean.buyCarAction()}"/>
//                        <f:setPropertyActionListener target="#{carDetailBean.carChecked}" value="#{car}"/>
//            </h:column>