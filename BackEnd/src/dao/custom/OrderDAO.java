package dao.custom;

import dao.CrudDAO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO, String> {
    String getOrderId(Connection connection) throws SQLException, ClassNotFoundException;

}
