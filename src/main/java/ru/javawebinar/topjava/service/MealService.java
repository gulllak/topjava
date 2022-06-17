package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public Meal get(int id, int userId) {
        return repository.get(id,userId);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

}