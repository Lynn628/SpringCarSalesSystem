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
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Lynn
 */
@Named(value = "loginManagedBean")
@SessionScoped
//@RequestScoped
public class LoginManagedBean implements Serializable {
     private String email;
     private String password;
     private Users currentUser;
     @EJB(name="carsalesBean")
     private  CarSalesInterface searchUser;
    
    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean()  {
       
    }
   
    public String submit( ) throws Exception{
     Users userSearched = searchUser.searchUser(email, password);
     // this.getUserSearched(userSearched);
       if(userSearched == null){
           return "index";
       }else {
           this.setCurrentUser(userSearched);
           if(userSearched.getType().equals("C")){
           return "customerMainPage";
       }else if(userSearched.getType().equals("S")){
           return "salesPersonMainPage";
        }
       }
            return "index";
    }        
    public Users showCurrentUser( ) throws Exception{
     Users userSearched = searchUser.searchUser(email, password);
        setCurrentUser(userSearched);
     return getCurrentUser();
    }
    public Users getUserSearched(Users user){
        return user;
    }

public void login() {
        try {
            //message = "";
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.email,this.password);
          this.currentUser = searchUser.searchUser(email, PasswordEncryption.Encrypt(password,"SHA-256"));
       //    System.out.println("Current Person " + currentUser.toString());
            this.setCurrentUser(this.searchUser.searchUser(email,"03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            if (request.isUserInRole("S")) {
                response.sendRedirect("salesPerson/salesPersonMainPage.xhtml");
                facesContext.responseComplete();
            } else if (request.isUserInRole("C")) {
                response.sendRedirect("customer/customerMainPage.xhtml");
                facesContext.responseComplete();
            } else {
                //message = "Either Login or Password is wrong";
                FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
                System.out.println("**************"+this.email + this.password);
            }
        } catch (Exception e) {
            
            System.out.println("*****************Exception");
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

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public Users getCurrentUser() {
        return currentUser;
    }
    
    
}
