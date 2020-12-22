package com.Restaurant.RestaurantTableService.controller;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import com.Restaurant.RestaurantTableService.service.TableServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tables/")
public class TableController {

    private final TableServiceInterface tableService;

    @Autowired
    public TableController(TableServiceInterface tableService){
        this.tableService = tableService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity addTable(@RequestBody RestaurantTable table) {
        RestaurantTable newTable = tableService.createTable(table);
        if (newTable == null) {
            return new ResponseEntity<>(
                    "Failed to create new table",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newTable,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/filter")
    public List<RestaurantTable> findTables(@RequestBody RestaurantTable table) {
        return tableService.findTables(table);
    }

    @GetMapping(value = "/find")
    public RestaurantTable findTableById(@RequestParam int id) {
        return tableService.findTableById(id);
    }

    @GetMapping(value = "/all")
    public List<RestaurantTable> findAll() {
        return tableService.getAllTables();
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateTable(@RequestBody RestaurantTable table) {
        RestaurantTable updatedTable = tableService.updateTable(table);
        if (updatedTable == null) {
            return new ResponseEntity<>(
                "Failed to create new table",
                HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                updatedTable,
                HttpStatus.BAD_REQUEST
            );
        }
    }

}
