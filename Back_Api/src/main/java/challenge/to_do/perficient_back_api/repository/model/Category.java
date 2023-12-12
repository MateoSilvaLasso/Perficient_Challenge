package challenge.to_do.perficient_back_api.repository.model;

import javax.persistence.*;
import java.awt.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Color color;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Set<Task> tasks;

    public Category(Long id, String name, Color color, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.tasks = tasks;
    }
    public Category(Long id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Category(String name, Color color, Set<Task> tasks){
        this.name = name;
        this.color = color;
        this.tasks = tasks;
    }

    public Category() {
    }

    public Category(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.color = category.getColor();
        this.tasks = category.getTasks();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
