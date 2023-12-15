package challenge.to_do.perficient_back_api.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    private Date creationTime;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    public Notify(Notify notify){
        this.creationTime = notify.getCreationTime();
        this.message = notify.getMessage();
        this.task = notify.getTask();
    }
}
