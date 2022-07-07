package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import dto.ItemDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(ArrayList<ItemDetailsDTO> items, Connection connection) throws SQLException, ClassNotFoundException {
        for (ItemDetailsDTO dto:
                items) {
            if (!CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES (?,?,?,?,?)",connection, dto.getItemCode(), dto.getOrderId(), dto.getQtyForSell(), dto.getUnitPrice(),dto.getCost())){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ArrayList arrayList, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ArrayList<ItemDetailsDTO>> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
