package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO= (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public String getOrderId(Connection connection) throws SQLException, ClassNotFoundException {
      return   orderDAO.getOrderId(connection);
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.add(orderDTO,connection);
    }
}
