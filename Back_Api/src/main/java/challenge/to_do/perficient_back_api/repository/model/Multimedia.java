package challenge.to_do.perficient_back_api.repository.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Byte [] contenido;

    @ManyToMany(mappedBy = "multimedia")
    private Set<Task> tasks;

    public Multimedia(Multimedia multimedia){
        this.id = multimedia.getId();
        this.contenido = multimedia.getContenido();
        this.tasks = multimedia.getTasks();
    }
    public Multimedia(Long id, Byte[] contenido, Set<Task> tasks) {
        this.id = id;
        this.contenido = contenido;
        this.tasks = tasks;
    }

    public Multimedia() {
    }

    public Multimedia(Byte[] contenido, Set<Task> tasks){
        this.contenido = contenido;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getContenido() {
        return contenido;
    }

    public void setContenido(Byte[] contenido) {
        this.contenido = contenido;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
