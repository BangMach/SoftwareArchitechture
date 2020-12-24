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

    private final TableDAOInterface tableDAO;
    private List<String> statuses = Arrays.asList("available", "unavailable");

    @Autowired
    public TableServiceImpl(@Qualifier("tableDAOImpl") TableDAOInterface tableDAO){
        this.tableDAO = tableDAO;
    }

    @Override
    @Transactional
    public RestaurantTable createTable(RestaurantTable table) {
        if (table.getSeats() != 0) {
            String status = "available";
            if (table.getStatus() != null) {
                if (statuses.contains(table.getStatus().trim().toLowerCase())) {
                    status = table.getStatus().trim().toLowerCase();
                }
            }
            table.setStatus(status);
            return tableDAO.saveTable(table);
        }
        return null;
    }

    @Override
    @Transactional
    public List<RestaurantTable> findTables(RestaurantTable table, int startAt, int maxResults) {
        return tableDAO.findTables(table, startAt, maxResults);
    }

    @Override
    @Transactional
    public List<RestaurantTable> getAllTables(int startAt, int maxResults) {
        return tableDAO.getAllTables(startAt, maxResults);
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
                if (statuses.contains(table.getStatus().trim().toLowerCase())) {
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
