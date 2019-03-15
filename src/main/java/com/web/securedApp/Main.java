package com.web.securedApp;

import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.dao.impl.OrderDaoImpl;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.model.constant.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Connection connection = ConnectionFactory.getConnection();

//        UserDao userDao = new UserDao();
//        List<User> users = userDao.getAll();
//        User user2 = new User(1,"u2", "123");
//        System.out.println(users.get(0));
//        User user1 = new User("u1", "123");
//        userDao.insert(user1);
//        userDao.delete(1);
//        users = userDao.getAll();
//        System.out.println(users.get(0));
//        Product product1 = new Product("product1", 10, 4, "tovar klass!");
//        ProductDao productDao = new ProductDao();
//        productDao.insert(product1);
//        Product newProduct1 = productDao.getById(1);
//        System.out.println(newProduct1);
//        CustomerDao customerDao = new CustomerDao();
//        List<Customer> customers = customerDao.getAll();
//        System.out.println(customers.get(0));
//        AddressDao addressDao = new AddressDao();
//        List<Address> addresses = addressDao.getAll();
//        System.out.println(addresses.get(0).getId());
//        System.out.println(addresses.get(0));
//        CustomerDao customerDao = new CustomerDao();
//        customerDao.insert(new Customer("Andrey", "Yasik", "man", LocalDate.of(1998, Month.MAY, 16), "hylioz", "375295830729"));
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setId(1);
        product2.setId(2);
        products.add(product1);
        products.add(product2);
        Customer customer = new Customer();
        customer.setId(2);
        Order order = new Order(LocalDate.of(2018, Month.MAY, 26), OrderStatus.DELIVERED, 14,
                customer, products);
        OrderDaoImpl orderDaoImpl =new OrderDaoImpl();
        orderDaoImpl.insert(order);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
