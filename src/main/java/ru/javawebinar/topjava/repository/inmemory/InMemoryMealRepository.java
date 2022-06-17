package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository.USER_ID;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if(repository.containsKey(userId)){
                repository.get(userId).put(meal.getId(), meal);
                return meal;
            }
            repository.put(userId, new ConcurrentHashMap<>());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);

        return CollectionUtils.isEmpty(userMeals) ? Collections.emptyList() :
                userMeals.values().stream()
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}

