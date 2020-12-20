package com.Restaurant.RestaurantTableService.controller;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import com.Restaurant.RestaurantTableService.service.TableServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RestaurantTable addTable(@RequestBody RestaurantTable table) {
        return (tableService.createTable(table));
    }

    @GetMapping(value = "/find")
    public List<RestaurantTable> findTables(@RequestBody RestaurantTable table) {
        return tableService.findTables(table);
    }

    @GetMapping(value = "/find/id")
    public RestaurantTable findTableById(@RequestParam int id) {
        return tableService.findTableById(id);
    }

    @GetMapping(value = "/all")
    public List<RestaurantTable> findAll() {
        return tableService.getAllTables();
    }

    @PutMapping(value = "/update")
    public RestaurantTable updateTable(@RequestBody RestaurantTable table) {
        return tableService.updateTable(table);
    }

    @DeleteMapping(value = "/delete")
    public String deleteTable(@RequestParam Integer id){
        return tableService.deleteTableById(id);
    }

}
