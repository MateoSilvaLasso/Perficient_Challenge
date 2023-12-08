package challenge.to_do.perficient_back_api.repository.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date beginTask;
    private Date endtask;
    private String information;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id") // Specify the foreign key column
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id") // Specify the foreign key column
    private Status status;

    public Task() {
    }

    public Task(Task task){
        this.id = task.getId();
        this.beginTask = task.getBeginTask();
        this.endtask = task.getEndtask();
        this.information = task.getInformation();
        this.category = task.getCategory();
        this.status =  task.getStatus();
    }

    public Task(Long id, Date beginTask, Date endtask, String information, Category category, Status status) {
        this.id = id;
        this.beginTask = beginTask;
        this.endtask = endtask;
        this.information = information;
        this.category = category;
        this.status = status;
    }

    public Task(Date beginTask, Date endtask, String information, Category category, Status status){
        this.beginTask = beginTask;
        this.endtask = endtask;
        this.information = information;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginTask() {
        return beginTask;
    }

    public void setBeginTask(Date beginTask) {
        this.beginTask = beginTask;
    }

    public Date getEndtask() {
        return endtask;
    }

    public void setEndtask(Date endtask) {
        this.endtask = endtask;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
