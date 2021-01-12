package com.BangMach.RestaurantMenuService.DAO;

import com.BangMach.RestaurantMenuService.model.Student;
import com.BangMach.RestaurantMenuService.repository.StudentRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class StudentDAO {
    private final EntityManager entityManager;

    private StudentRepository studentRepository = new StudentRepository() {
        @Override
        public <S extends Student> S save(S s) {
            return null;
        }

        @Override
        public <S extends Student> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
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
            return null;
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
        public void deleteById(String s) {

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
    };

}
