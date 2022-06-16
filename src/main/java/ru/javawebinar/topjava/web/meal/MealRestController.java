package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        return service.create(meal);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Collection<Meal> getAll() {
        return service.getAll();
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void update(Meal meal) {
        service.update(meal);
    }
}