package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public Meal get(int id) {
        return repository.get(id);
    }

    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

}