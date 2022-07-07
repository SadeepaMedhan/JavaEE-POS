package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBo {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CustomerDTO searchForCustomer(String customerId,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.search(customerId,connection);
    }

    @Override
    public boolean saveCustomer(CustomerDTO c1, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.add(c1,connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.getAll(connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO,connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id,connection);
    }
}
