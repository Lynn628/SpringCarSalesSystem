/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lynn
 */
@Named(value = "mainPageBean")
@SessionScoped
public class mainPageBean implements Serializable {
     private Users currentUser;
     private String firstName;
     private Integer serverNum;
    /**
     * Creates a new instance of mainPageBean
     */
    public mainPageBean() {
    }
  
  public Users getUserIdentity(){
      ELContext context = FacesContext.getCurrentInstance().getELContext();
      LoginManagedBean loginBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,null,"loginManagedBean");
      this.setCurrentUser(loginBean.getCurrentUser());
      return this.getCurrentUser();
      
  }
    
  public Users getCurrentUser(){
      return currentUser;
  }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getUserName(){
        
        return this.getCurrentUser().getFirstName();
    }

    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    public Integer getServerNum() {
        return serverNum;
    }
   
}
