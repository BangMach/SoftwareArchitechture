package com.BangMach.RestaurantMenuService.repository;


import com.BangMach.RestaurantMenuService.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {}
