package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveProduct(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException;
    List<ItemDTO> getAllProducts(Connection connection) throws SQLException, ClassNotFoundException;
    boolean updateProduct(ItemDTO itemDTO , Connection connection) throws SQLException, ClassNotFoundException;
    boolean deleteProduct(String id , Connection connection) throws SQLException, ClassNotFoundException;
    ItemDTO searchForProduct(String id , Connection connection) throws SQLException, ClassNotFoundException;
}
