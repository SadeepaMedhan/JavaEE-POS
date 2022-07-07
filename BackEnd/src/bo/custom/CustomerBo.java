package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBO {
    List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    CustomerDTO searchForCustomer(String customerId,Connection connection) throws SQLException, ClassNotFoundException;
    
    boolean saveCustomer(CustomerDTO c1, Connection connection) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO,Connection connection) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException;
}
