package fa.training.dao;

import java.util.List;
import fa.training.entities.Order;

public interface OrderDao {

    List<Order> getAllOrdersByCustomerId(int customerId);

    Double computeOrderTotal(int orderId);

}
