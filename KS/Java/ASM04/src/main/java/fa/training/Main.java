package fa.training;

import java.util.List;
import fa.training.dao.impl.CustomerDaoImpl;
import fa.training.dao.impl.LineItemDaoImpl;
import fa.training.dao.impl.OrderDaoImpl;
import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;

public class Main {
    public static void main(String[] args) {
        CustomerDaoImpl cDao = new CustomerDaoImpl();
        OrderDaoImpl oDao = new OrderDaoImpl();
        LineItemDaoImpl lDao = new LineItemDaoImpl();

        List<Customer> cList = cDao.getAllCustomers();
        List<Order> oList = oDao.getAllOrdersByCustomerId(1);
        List<LineItem> lList = lDao.getAllItemsByOrderId(1);
        double orderTotal = oDao.computeOrderTotal(1);

        /* Customer c = new Customer(10, "Smitty");
        boolean isAdded = cDao.addCustomer(c); */

        cDao.deleteCustomer(1);
        System.out.println();
    }
}