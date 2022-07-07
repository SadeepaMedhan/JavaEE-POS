package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveProduct(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
       return itemDAO.add(itemDTO,connection);
    }

    @Override
    public List<ItemDTO> getAllProducts(Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.getAll(connection);
    }

    @Override
    public boolean updateProduct(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.update(itemDTO,connection);
    }

    @Override
    public boolean deleteProduct(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id,connection);
    }

    @Override
    public ItemDTO searchForProduct(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id,connection);
    }

}
