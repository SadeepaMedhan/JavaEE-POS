package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    String getOrderId(Connection connection) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException;
}

