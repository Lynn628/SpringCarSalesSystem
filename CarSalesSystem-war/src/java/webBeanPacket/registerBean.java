/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import CarSearch.CarSalesInterface;
import entities.Users;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lynn
 */
@Named(value = "registerBean")
@SessionScoped
public class registerBean implements Serializable {

     private String lastName;
     private String firstName;
     private String email;
     private String password;
     private String type;
     private String address;
     private String phoneNum;
     private Users userRegiste;
     @EJB 
     private CarSalesInterface manipulateUser;
    /**
     * Creates a new instance of registerBean
     * 
     */
     
     
    public registerBean() {
        userRegiste = new Users();
    }

    public void addUser() throws Exception{
       
        if(this.userRegiste!= null){
        String passwordEnc = PasswordEncryption.Encrypt(userRegiste.getPassword(),"SHA-256");
        userRegiste.setPassword(passwordEnc);
        System.out.println(this.userRegiste.getLastName());
        manipulateUser.addUser(this.userRegiste);
         this.logout();
//        }else{
//            return "error";
//        }
        }
    }
    
    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);//务必设置为false
        session.invalidate();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        System.out.println("log out!");
        response.sendRedirect("index.xhtml");
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Users getUserRegiste() {
        return userRegiste;
    }

    public void setUserRegiste(Users userRegiste) {
        this.userRegiste = userRegiste;
    }
    
    
}

  
   