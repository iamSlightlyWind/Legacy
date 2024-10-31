package fa.training.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fa.training.common.Database;
import fa.training.dao.OrderDao;
import fa.training.entities.Order;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) {
        List<Order> list = new ArrayList<Order>();

        try {
            String query = "select order_id, order_date, customer_id, employee_id, total from orders where customer_id = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(new Order(
                        result.getInt("order_id"),
                        result.getString("order_date"),
                        result.getInt("customer_id"),
                        result.getInt("employee_id"),
                        result.getDouble("total")));
            }

            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public Double computeOrderTotal(int orderId) {
        try {
            String query = "{? = call computeOrderTotal(?)}";
            CallableStatement statement = Database.getConnection().prepareCall(query);
            statement.registerOutParameter(1, java.sql.Types.DOUBLE);
            statement.setInt(2, orderId);
            statement.execute();
            return statement.getDouble(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}