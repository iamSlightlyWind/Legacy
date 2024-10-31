package fa.training.dao;

import java.util.List;
import fa.training.entities.LineItem;

public interface LineItemDao {
    List<LineItem> getAllItemsByOrderId(int orderId);
}
