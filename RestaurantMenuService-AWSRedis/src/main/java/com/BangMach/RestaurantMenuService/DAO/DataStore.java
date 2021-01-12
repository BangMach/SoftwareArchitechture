package com.BangMach.RestaurantMenuService.DAO;

import com.BangMach.RestaurantMenuService.model.Student;
import com.BangMach.RestaurantMenuService.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStore implements StudentRepository {
    private StudentRepository studentRepository;
    @Override
    public <S extends Student> S save(S s) {
        Student student = new Student("Eng20150011", "John Doe", Student.Gender.MALE, 1);
        studentRepository.save(student);
        return null;
    }

    @Override
    public <S extends Student> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;

        studentRepository.deleteById();
    }

    @Override
    public Optional<Student> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Student> findAll() {
        List<Student> students  = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
    }

    @Override
    public Iterable<Student> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Student s) {
        studentRepository.deleteById(s.getId());


    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public void deleteAll(Iterable<? extends Student> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
