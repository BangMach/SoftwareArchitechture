package com.BangMach.RestaurantService.repository;

import com.BangMach.RestaurantService.model.Food;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FoodDatabaseRepositoryImp implements FoodDatabaseRepository{

    @Override
    public <S extends Food> S save(S s) {
        return null;
    }

    @Override
    public <S extends Food> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Food> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Food> findAll() {
        return null;
    }

    @Override
    public Iterable<Food> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Food food) {

    }

    @Override
    public void deleteAll(Iterable<? extends Food> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
