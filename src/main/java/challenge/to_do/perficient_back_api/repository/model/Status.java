package challenge.to_do.perficient_back_api.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private Set<Task> tasks;

    public Status() {
    }

    public Status(Status status){
        this.id= status.getId();
        this.name= status.getName();
        this.tasks = status.getTasks();
    }

    public Status(Long id, String name, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }
    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status(String name, Set<Task> tasks){
        this.name= name;
        this.tasks=tasks;
    }

    public Long getId() {
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
