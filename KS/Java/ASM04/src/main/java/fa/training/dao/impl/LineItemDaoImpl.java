package fa.training.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fa.training.common.Database;
import fa.training.dao.LineItemDao;
import fa.training.entities.LineItem;

public class LineItemDaoImpl implements LineItemDao {

    public List<LineItem> getAllItemsByOrderId(int orderId) {
        List<LineItem> list = new ArrayList<LineItem>();

        try {
            String query = "select order_id, product_id, quantity, price from lineitem where order_id = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(new LineItem(
                        result.getInt("order_id"),
                        result.getInt("product_id"),
                        result.getInt("quantity"),
                        result.getDouble("price")));
            }

            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        throw new UnsupportedOperationException("Unimplemented method 'getAllItemsByOrderId'");
    }

}