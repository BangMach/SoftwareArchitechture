package com.Restaurant.RestaurantTableService.controller;

import com.Restaurant.RestaurantTableService.model.RestaurantTable;
import com.Restaurant.RestaurantTableService.service.TableServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tables/")
public class TableController {

    private TableServiceInterface tableService;

    @Autowired
    public TableController(TableServiceInterface tableService){
        this.tableService = tableService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RestaurantTable addTable(@RequestBody RestaurantTable table) {
        return (tableService.createTable(table));
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<RestaurantTable> findTables(@RequestBody RestaurantTable table) {
        return tableService.findTables(table);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RestaurantTable> findAll() {
        return tableService.getAllTables();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RestaurantTable updateTable(@RequestBody RestaurantTable table) {
        return tableService.updateTable(table);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteTable(@RequestParam Integer id){
        return tableService.deleteTableById(id);
    }

}
