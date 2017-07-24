/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSalesBean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ejb.Stateless;
import CarSearch.CarSalesInterface;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import entities.Car;
import entities.Sale;
import entities.Users;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lynn
 */
@Stateless(name = "carsalesBean")
public class CarSalesImplBean implements CarSalesInterface {

    @PersistenceContext(unitName = "CarSalesSystem_ejbPU")
    private EntityManager entityManager;

    @Override
    public void addCar(String VIN, String modelName, String modelNumber, String manufacturer, String type,
            String status, String description, String picAddress) throws Exception {

        Car car = new Car(VIN, modelNumber, modelName, manufacturer, type, picAddress, description, status);
        entityManager.persist(car);

    }

    @Override
    public List<Car> getAllCar() throws Exception {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Car.class);
        Root<Car> car = cq.from(Car.class);
        cq.select(car);
        TypedQuery<Car> q = entityManager.createQuery(cq);
        List<Car> allCars = q.getResultList();

        return allCars;
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }

    }

    @Override
    public Car searchCarByVIN(String VIN) throws Exception {
        return entityManager.find(Car.class, VIN);
    }

    /**
     * Search car in DB based on manufacturer, modelName, modelNo,type
     * information
     */
    @Override
    public List<Car> SearchCar(String manufacturer, String modelName, String modelNo, String type) throws Exception {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT c FROM Car c WHERE 1 = 1 ");
        if (modelName != null && !modelName.isEmpty()) {
            queryStr.append("AND c.modelName = :modelName ");
        }
        if (modelNo != null && !modelNo.isEmpty()) {
            queryStr.append("AND c.modelNo = :modelNo ");
        }
        // System.out.println("*******************manufacture is empty" + manufacturer.isEmpty());
        if (manufacturer != null && !manufacturer.isEmpty()) {
            queryStr.append("AND c.manufacturer = :manufacturer ");
        }
        if (type != null && !type.isEmpty()) {
            queryStr.append("AND c.type = :type");
        }

        Query query = entityManager.createQuery(queryStr.toString());

        if (modelName != null && !modelName.isEmpty()) {
            query.setParameter("modelName", modelName);
        }
        if (modelNo != null && !modelNo.isEmpty()) {
            query.setParameter("modelNo", modelNo);
        }
        if (manufacturer != null && !manufacturer.isEmpty()) {
            query.setParameter("manufacturer", manufacturer);
        }
        if (type != null && !type.isEmpty()) {
            query.setParameter("type", type);
        }
        System.out.println("*******************manufacture is empty" + queryStr.toString());
        System.out.println("***************The size of retrived car list" + query.getResultList().size());
        return query.getResultList();
    }

    @Override
    public Users searchUser(String email, String password) {

        if (!email.isEmpty() && !password.isEmpty()) {
            String queryStr = "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password";
            Query query = entityManager.createQuery(queryStr);
            //Query query = entityManager.createQuery("SELECT u FROM Users WHERE u.email = :email AND u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);

            if (query.getSingleResult() == null) {
                return null;
            } else {
                Users userFound = (Users) query.getSingleResult();
                return userFound;
            }
        } else {
            return null;
        }
    }

    @Override
    public void deleteCar(String VIN) throws Exception {

        Car carObject = entityManager.find(Car.class, VIN);
        entityManager.remove(carObject);

    }

    @Override
    public void updateCar(Car car) throws Exception {

        entityManager.merge(car);

    }

    @Override
    public List<Car> customerCarSearch(String manufacturer, String modelName, String modelNo, String type) throws Exception {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT c FROM Car c WHERE 1 = 1 ");
        if (modelName != null && !modelName.isEmpty()) {
            queryStr.append("AND c.modelName = :modelName ");
        }
        if (modelNo != null && !modelNo.isEmpty()) {
            queryStr.append("AND c.modelNo = :modelNo ");
        }
        // System.out.println("*******************manufacture is empty" + manufacturer.isEmpty());
        if (manufacturer != null && !manufacturer.isEmpty()) {
            queryStr.append("AND c.manufacturer = :manufacturer ");
        }
        if (type != null && !type.isEmpty()) {
            queryStr.append("AND c.type = :type ");
        }
        queryStr.append("AND c.carStatus = :carStatus");
        Query query = entityManager.createQuery(queryStr.toString());

        if (modelName != null && !modelName.isEmpty()) {
            query.setParameter("modelName", modelName);
        }
        if (modelNo != null && !modelNo.isEmpty()) {
            query.setParameter("modelNo", modelNo);
        }
        if (manufacturer != null && !manufacturer.isEmpty()) {
            query.setParameter("manufacturer", manufacturer);
        }
        if (type != null && !type.isEmpty()) {
            query.setParameter("type", type);
        }
        query.setParameter("carStatus", "Accessible");
        System.out.println("*******************manufacture is empty" + queryStr.toString());
        System.out.println("***************The size of retrived car list" + query.getResultList().size());
        return query.getResultList();
    }

    @Override
    public void addUser(Users user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public List<Users> findUser(Integer userId, String firstName, String lastName, String type, String email) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT u FROM Users u WHERE 1 = 1 ");
        if (userId != null && !Integer.toString(userId).isEmpty()) {
            queryStr.append("AND u.id = :userId ");
        }
        if (firstName != null && !firstName.isEmpty()) {
            queryStr.append("AND u.firstName = :firstName ");
        }
        // System.out.println("*******************manufacture is empty" + manufacturer.isEmpty());
        if (lastName != null && !lastName.isEmpty()) {
            queryStr.append("AND u.lastName = :lastName ");
        }
        if (type != null && !type.isEmpty()) {
            queryStr.append("AND u.type = :type ");
        }
        if (email != null && !email.isEmpty()) {
            queryStr.append("AND u.email = :email ");
        }

        Query query = entityManager.createQuery(queryStr.toString());

        if (userId != null && !Integer.toString(userId).isEmpty()) {
            query.setParameter("userId", userId);
        }
        if (firstName != null && !firstName.isEmpty()) {
            query.setParameter("firstName", firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            query.setParameter("lastName", lastName);
        }
        if (type != null && !type.isEmpty()) {
            query.setParameter("type", type);
        }
        if (email != null && !email.isEmpty()) {
            query.setParameter("email", email);
        }
        System.out.println("*******************manufacture is empty" + queryStr.toString());
        System.out.println("***************The size of retrived car list" + query.getResultList().size());
        return query.getResultList();
    }

    @Override
    public void addRecord(Integer customerId, String carVIN, Integer salesPersonId, String date, String statues) {
        java.sql.Date tradeDate = java.sql.Date.valueOf(date);
        Users customer = entityManager.find(Users.class, customerId);
        Users salesPerson = entityManager.find(Users.class, salesPersonId);
        Car carBought = entityManager.find(Car.class, carVIN);
        if (statues.equals("Paid")) {
            carBought.setCarStatus("Sold");
        } else if (statues.equals("Unpaid")) {
            carBought.setCarStatus("In Process");
        }
        Sale saleRecord = new Sale(customer, tradeDate, salesPerson, carBought, statues);
        entityManager.persist(saleRecord);
    }

    @Override
    public List<Sale> displayARecord(String VIN) throws Exception {
        Car car = entityManager.find(Car.class, VIN);
        Query query = entityManager.createQuery("SELECT s FROM Sale s WHERE s.car.VIN = :VIN");
        query.setParameter("VIN", VIN);
        List<Sale> aSale = query.getResultList();
        return aSale;
    }

    @Override
    public List<Sale> searchRecords(Long saleId, Integer customerId, String VIN) throws Exception {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT s FROM Sale s WHERE 1 = 1 ");
        if (saleId != null && !Long.toString(saleId).isEmpty()) {
            queryStr.append("AND s.saleId = :saleId ");
        }
        if (customerId != null && !Integer.toString(customerId).isEmpty()) {
            queryStr.append("AND s.customer.id = :customerId ");
        }
        // System.out.println("*******************manufacture is empty" + manufacturer.isEmpty());
        if (VIN != null && !VIN.isEmpty()) {
            queryStr.append("AND s.car.VIN = :VIN ");
        }

        Query query = entityManager.createQuery(queryStr.toString());

        if (saleId != null && !Long.toString(saleId).isEmpty()) {
            query.setParameter("saleId", saleId);
        }
        if (customerId != null && !Integer.toString(customerId).isEmpty()) {
            query.setParameter("customerId", customerId);
        }
        if (VIN != null && !VIN.isEmpty()) {
            query.setParameter("VIN", VIN);
        }
        return query.getResultList();
    }
}
