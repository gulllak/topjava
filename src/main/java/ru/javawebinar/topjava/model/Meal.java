package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m  WHERE m.id =?1 AND m.user.id =?2"),
        @NamedQuery(name = Meal.GET_All, query = "SELECT m from Meal m left join fetch m.user where m.user.id = ?1"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id = ?1 AND m.user.id = ?2"),
})

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {
    public static final String GET = "Meal.get";
    public static final String GET_All = "Meal.get_all";
    public static final String DELETE = "Meal.delete";

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "calories")
    @NotNull
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
