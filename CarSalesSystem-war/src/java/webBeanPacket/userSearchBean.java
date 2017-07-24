/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webBeanPacket;

import CarSearch.CarSalesInterface;
import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lynn
 */
@Named(value = "userSearchBean")
@SessionScoped
public class userSearchBean implements Serializable {

     
     private Integer id;
     private String lastName;
     private String firstName;
     private String email;
     private String password;
     private String type;
     private String address;
     private String phoneNum;
     private Users user;
     private List<Users> userFoundList;
     @EJB
     private CarSalesInterface maniuplateUser;
    /**
     * Creates a new instance of userSearchBean
     */
    public userSearchBean() {
       user=new Users();
    }
    
    public void findUser() {
        System.out.println("8**************");
        //     
        //System.out.println("8**************"+ this.user.getFirstName());
//             if(this.user != null){
  System.out.println("8**************"+ this.id+this.firstName+ this.type);
//     List<Users> userList = maniuplateUser.findUser(this.user.getId(),this.user.getFirstName(),this.user.getLastName(),this.user.getType(),user.getEmail());
List<Users> userList = maniuplateUser.findUser(this.id,this.firstName,this.lastName,this.type,this.email);
if(userList!=null){
this.setUserFoundList(userList);

   

}
    
      //  System.out.println("!!!!");               
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Users> getUserFoundList() {
        return userFoundList;
    }

    public void setUserFoundList(List<Users> userFoundList) {
        this.userFoundList = userFoundList;
    }

   
 

  

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
