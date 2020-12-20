package com.Restaurant.RestaurantTableService.service;

import com.Restaurant.RestaurantTableService.DAO.TableDAOInterface;
import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
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
        String status = "available";
        if (table.getStatus() != null) {
            if (Arrays.asList("available", "unavailable").contains(table.getStatus().trim().toLowerCase())) {
                status = table.getStatus().trim().toLowerCase();
            }
        }
        table.setStatus(status);
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
            String status = table.getStatus();
            if (status != null) {
                if (Arrays.asList("available", "unavailable").contains(table.getStatus().trim().toLowerCase())) {
                    currentTable.setStatus(status.trim().toLowerCase());
                }
            }
            return tableDAO.saveTable(currentTable);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public RestaurantTable findTableById(int id) {
        return tableDAO.findTableById(id);
    }

}
