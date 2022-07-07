package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(CustomerDTO c, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `customer`", connection);
        while (rst.next()) {
            if (rst.getString(1).equals(c.getId())) {
                return false;
            }
        }
        return CrudUtil.executeUpdate("INSERT INTO `customer` VALUES(?,?,?,?)", connection, c.getId(), c.getName(),
                c.getAddress(), c.getContact());
    }

    @Override
    public boolean delete(String s,Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("Delete from customer where CustID=?",connection,s);
    }

    @Override
    public boolean update(CustomerDTO cDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("Update customer set CustName=?,CustAddress=?,Contact=? where CustId=?", connection, cDTO.getName(), cDTO.getAddress(), cDTO.getContact(), cDTO.getId());
    }

    @Override
    public CustomerDTO search(String s,Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `customer` WHERE CustID=?", connection, s);
        rst.next();
        return new CustomerDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
    }

    @Override
    public ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `customer`", connection);
        ArrayList<CustomerDTO> customerList = new ArrayList<>();
        while (rst.next()) {
            customerList.add(new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return customerList;
    }

}
