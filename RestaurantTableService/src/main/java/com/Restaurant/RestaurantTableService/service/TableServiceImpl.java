package com.Restaurant.RestaurantTableService.service;

import com.Restaurant.RestaurantTableService.DAO.TableDAOInterface;
import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class TableServiceImpl implements TableServiceInterface {

    private TableDAOInterface tableDAO;

    @Autowired
    public TableServiceImpl(@Qualifier("tableDAOImpl") TableDAOInterface tableDAO){
        this.tableDAO = tableDAO;
    }

    @Override
    @Transactional
    public RestaurantTable createTable(RestaurantTable table) {
        return tableDAO.saveTable(table);
    }

    @Override
    @Transactional
    public List<RestaurantTable> findTables(RestaurantTable table) {
        return tableDAO.findTables(table);
    }

    @Override
    @Transactional
    public List<RestaurantTable> getAllTables() {
        return tableDAO.getAllTables();
    }

    @Override
    @Transactional
    public RestaurantTable updateTable(RestaurantTable table)  {
        RestaurantTable currentTable = findTableById(table.getId());
        if (currentTable != null) {
            if (table.getSeats() != 0) {
                currentTable.setSeats(table.getSeats());
            }
            return tableDAO.saveTable(currentTable);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public String deleteTableById(int id) {
        RestaurantTable currentTable = findTableById(id);
        if (currentTable != null) {
            tableDAO.deleteTableById(id);
            return "Deleted table id " + id;
        } else {
            return "Table id not found";
        }
    }

    @Override
    @Transactional
    public RestaurantTable findTableById(int id) {
        return tableDAO.findTableById(id);
    }

}
