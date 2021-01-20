package com.Restaurant.RestaurantTableService.controller;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import com.Restaurant.RestaurantTableService.service.TableServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/tables")
public class TableController {

    private final TableServiceInterface tableService;

    @Autowired
    public TableController(TableServiceInterface tableService){
        this.tableService = tableService;
    }

    @PostMapping(value = "")
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
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(value = "")
    public List<RestaurantTable> findAll(@RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return tableService.getAllTables(startAt, maxResults);
    }

    @PutMapping(value = "")
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
                HttpStatus.OK
            );
        }
    }

    @GetMapping(value = "/{id}")
    public RestaurantTable findTableById(@PathVariable int id) {
        return tableService.findTableById(id);
    }

    @PostMapping(value = "/attributes")
    public List<RestaurantTable> findTables(@RequestBody RestaurantTable table, @RequestParam(value= "startAt", defaultValue = "0") Integer startAt, @RequestParam(value= "maxResults", defaultValue = "50") Integer maxResults) {
        return tableService.findTables(table, startAt, maxResults);
    }

}
