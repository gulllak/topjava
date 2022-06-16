package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

// TODO add userId
public interface MealRepository {
    int userId = SecurityUtil.authUserId();
    // null if updated meal does not belong to userId
    Meal save(Meal meal);

    // false if meal does not belong to userId
    boolean delete(int id);

    // null if meal does not belong to userId
    Meal get(int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll();
}
