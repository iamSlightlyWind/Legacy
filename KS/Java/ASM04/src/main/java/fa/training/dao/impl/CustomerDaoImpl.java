package fa.training.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import fa.training.common.Database;
import fa.training.dao.CustomerDao;
import fa.training.entities.Customer;

public class CustomerDaoImpl implements CustomerDao {

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<Customer>();

        try {
            String query = "select customer_id, customer_name from customer";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(new Customer(result.getInt("customer_id"), result.getString("customer_name")));
            }

            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean addCustomer(Customer customer) {
        try {
            String query = "{call addCustomer(?, ?)}";
            CallableStatement statement = Database.getConnection().prepareCall(query);
            statement.setString(1, customer.getCustomerName());
            statement.registerOutParameter(2, java.sql.Types.BIT);
            statement.execute();
            return statement.getBoolean(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        try {
            String query = "{call deleteCustomer(?, ?)}";
            CallableStatement statement = Database.getConnection().prepareCall(query);
            statement.setInt(1, customerId);
            statement.registerOutParameter(2, java.sql.Types.BIT);
            statement.execute();
            return statement.getBoolean(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
