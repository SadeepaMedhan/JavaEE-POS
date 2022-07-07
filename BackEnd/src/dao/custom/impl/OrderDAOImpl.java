package dao.custom.impl;

import dao.CrudUtil;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDAOImpl implements OrderDAO {
    final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    public String getOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `order` ORDER BY orderId DESC LIMIT 1",connection);
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<=9){return "O-00"+tempId;}
            else if(tempId<=99){return "O-0"+tempId;}
            else {return "O-"+tempId;}
        }else{return "O-001";}
    }



    @Override
    public boolean add(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
        int check=0;
        connection.setAutoCommit(false);
        try{
            System.out.println(orderDTO.getOrderId());
            if (CrudUtil.executeUpdate("INSERT INTO `Order` VALUES (?,?,?,?,?,?)",connection, orderDTO.getOrderId(), orderDTO.getCustomerId()
                    , orderDTO.getOrderDate(), orderDTO.getOrderTime(), orderDTO.getCost(),orderDTO.getDiscount()) && orderDetailDAO.add(orderDTO.getItems(),connection)){
                connection.commit();
                check=1;
            }else {
                connection.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
        return check == 1;
    }



    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDTO order, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDTO> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
