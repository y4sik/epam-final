import com.web.securedApp.model.constant.OrderStatus;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.dao.impl.CustomerWithEntitiesDaoImpl;
import com.web.securedApp.model.dao.impl.OrderDaoImpl;
import com.web.securedApp.model.dao.impl.ProductWithEntitiesDaoImpl;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.service.UserService;
import com.web.securedApp.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DaoTests {

    private Connection connection;

    @BeforeMethod
    public void getConnection() {
        connection = ConnectionFactory.getConnection();
    }

    @AfterMethod
    public void closeConnection() {
        ConnectionFactory.closeConnection(connection);
    }

    @Test
    public void getCustomerWithOrderFeedBackAddressTest() {
        CustomerWithEntitiesDaoImpl customerWithEntitiesDaoImpl = new CustomerWithEntitiesDaoImpl();
        Customer customer = customerWithEntitiesDaoImpl.getCustomerWithOrderFeedbackById(3);
        Assert.assertEquals(3, customer.getId());
        Assert.assertEquals(1, customer.getAddresses().get(0).getId());
        Assert.assertEquals(2, customer.getAddresses().get(1).getId());
        Assert.assertEquals(1, customer.getOrders().get(0).getId());
        Assert.assertEquals(2, customer.getOrders().get(1).getId());
        Assert.assertEquals(1, customer.getFeedbacks().get(0).getId());

        int i = 0;
    }

    @Test
    public void getProductWithPictureFeedbackOrderByIdTest() {
        ProductWithEntitiesDaoImpl productWithEntitiesDao = new ProductWithEntitiesDaoImpl();
        Product product = productWithEntitiesDao.getProductWithPictureFeedbackOrderById(1);
        Assert.assertEquals(1, product.getId());
        Assert.assertEquals(1, product.getPictures().get(0).getId());
        Assert.assertEquals(1, product.getOrders().get(0).getId());
        Assert.assertEquals(2, product.getOrders().get(1).getId());
        Assert.assertEquals(1, product.getOrders().get(0).getId());
        int i = 0;
    }

    @Test
    public void getAllProductWithPictureFeedbackOrderByIdTest() {
        ProductWithEntitiesDaoImpl productWithEntitiesDao = new ProductWithEntitiesDaoImpl();
        List<Product> products = productWithEntitiesDao.getAllProductsWithPictureFeedbackOrder();
        int i = 0;
    }

    @Test
    public void getAllProductWithPictureFeedbackOrderByCategoryTest() {
        ProductWithEntitiesDaoImpl productWithEntitiesDao = new ProductWithEntitiesDaoImpl();
        List<Product> products = productWithEntitiesDao.getAllProductWithPictureFeedbackOrderByCategory(1);
        int i = 0;
    }

    @Test
    public void getOrderByIdTest() {
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        Order order = orderDaoImpl.getById(2);
//        Assert.assertEquals(3, order.getCustomerOwner().getId());
//        Assert.assertEquals(1, order.getProducts().get(0).getId());
//        Assert.assertEquals(2, order.getProducts().get(1).getId());

        int i = 0;
    }

    @Test
    public void getAllOrderTest() {
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        List<Order> orders = orderDaoImpl.getAll();

        int i = 0;
    }

    @Test
    public void insertOrderTest() {
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
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        orderDaoImpl.insert(order);

        int i = 0;
    }

    @Test
    public void registrationTest() {
        UserService userService = new UserServiceImpl();
        Customer customer = new Customer("Andrey", "Yasik", "man",
                LocalDate.of(1998, 5, 16), "hylioz", "+37529");
        int id = userService.registration(customer, "123");
        Assert.assertEquals(-1, id);
    }
}