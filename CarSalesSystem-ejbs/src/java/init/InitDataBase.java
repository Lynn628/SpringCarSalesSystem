/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import entities.Car;
import entities.Users;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Lynn
 */
public class InitDataBase {


    public static void main(String[] args) throws  Exception{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarSalesSystem_ejbPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
       Users user = new Users("Potter","Lynn","455975343@qq.com","1234","C","St. Min","1234567890124");
       // Car car1 = new Car("WBAFB335111814864", "520i", "5 Series", "BMW", "Sedan","C:/","8 speed Automatic, rear wheel drive,4 doors 5 passengers","Accessible",new Date(2015-9-4));
     
        em.persist(user);
       // Car car2 = new Car("LSVHJ133022221716", "MY16", "Beetle", "VM", "Sendan", "C:/", "Automatic,front wheel drive ","Accessible",new Date(2015-9-4));
        //Car car2 = new Car("4","2","2","2","2");
       // em.persist(car2);
           
        System.out.println("**********************Test init JPABean"  );
        et.commit();
                
    }
   
}
