package dao.custom;

import dao.CrudDAO;
import dto.ItemDetailsDTO;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<ArrayList<ItemDetailsDTO> ,String>  {
}
