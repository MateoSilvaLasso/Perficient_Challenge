package challenge.to_do.perficient_back_api.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private byte [] contenido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")

    private Task task;

    public Multimedia(Multimedia multimedia){
        this.id = multimedia.getId();
        this.contenido = multimedia.getContenido();
        this.task = multimedia.getTasks();
    }
    public Multimedia(Long id, byte[] contenido, Task tasks) {
        this.id = id;
        this.contenido = contenido;
        this.task = tasks;
    }

    public Multimedia() {
    }

    public Multimedia(byte[] contenido, Task tasks){
        this.contenido = contenido;
        this.task = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Task getTasks() {
        return task;
    }

    public void setTasks(Task tasks) {
        this.task = tasks;
    }
}
