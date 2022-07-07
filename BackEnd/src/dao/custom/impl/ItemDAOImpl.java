package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(ItemDTO item, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `item`", connection);
        while (rst.next()) {
            if (rst.getString(1).equals(item.getId())) {
                return false;
            }
        }
        return CrudUtil.executeUpdate("INSERT INTO `item` VALUES(?,?,?,?)",connection,item.getId(),item.getName(),item.getQty(),item.getPrice());
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `item`", connection);
        while (rst.next()) {
            if (rst.getString(1).equals(id)) {
                return CrudUtil.executeUpdate("DELETE FROM `item` WHERE ItemCode=?",connection,id);
            }
        }
        return false;
    }

    @Override
    public boolean update(ItemDTO o, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `item`", connection);
        while (rst.next()) {
            if (rst.getString(1).equals(o.getId())) {
                return CrudUtil.executeUpdate("UPDATE `item` SET ItemDescription=?,qtyOnHand=?,unitPrice=? WHERE ItemCode=?",connection,o.getName(),o.getQty(),o.getPrice(),o.getId());
            }
        }
        return false;
    }

    @Override
    public ItemDTO search(String id,Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `item` WHERE ItemCode=?", connection, id);
        rst.next();
        return new ItemDTO(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
    }


    @Override
    public ArrayList<ItemDTO> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `item`", connection);
        ArrayList<ItemDTO> productList=new ArrayList<>();
        while (rst.next()){
            productList.add(new ItemDTO(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
        }
        return productList;
    }
}
