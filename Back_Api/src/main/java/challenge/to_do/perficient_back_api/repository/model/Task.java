package challenge.to_do.perficient_back_api.repository.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date recurrenceStartDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek recurrenceDayOfWeek;

    @Column(length = 20000)
    private String title;

    @Temporal(TemporalType.DATE)
    private Date beginTask;

    @Temporal(TemporalType.DATE)
    private Date endtask;

    private String information;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id") // Specify the foreign key column

    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id") // Specify the foreign key column

    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Set<Multimedia> multimedia;

    public Task() {
    }

    public Task(Task task){
        this.id = task.getId();
        this.user = task.getUser();
        this.recurrenceStartDate = task.getRecurrenceStartDate();
        this.recurrenceDayOfWeek = task.recurrenceDayOfWeek;
        this.title = task.getTitle();
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

    public Task(Long id, String name, Date beginTask, Date endtask, String information, Category category, Status status, Set<Multimedia> multimedia) {
        this.id = id;
        this.title = name;
        this.beginTask = beginTask;
        this.endtask = endtask;
        this.information = information;
        this.category = category;
        this.status = status;
        this.multimedia =  multimedia;
    }

    public Task(Date beginTask, Date endtask, String information, Category category, Status status){
        this.beginTask = beginTask;
        this.endtask = endtask;
        this.information = information;
        this.category = category;
        this.status = status;
    }

    public Task(String title, Date beginTask, Date endtask, String information, Category category, Status status, Set<Multimedia> multimedia){
        this.title = title;
        this.beginTask = beginTask;
        this.endtask = endtask;
        this.information = information;
        this.category = category;
        this.status = status;
        this.multimedia = multimedia;
    }

    public Date getRecurrenceStartDate() {
        return recurrenceStartDate;
    }

    public void setRecurrenceStartDate(Date recurrenceStartDate) {
        this.recurrenceStartDate = recurrenceStartDate;
    }

    public DayOfWeek getRecurrenceDayOfWeek() {
        return recurrenceDayOfWeek;
    }

    public void setRecurrenceDayOfWeek(DayOfWeek recurrenceDayOfWeek) {
        this.recurrenceDayOfWeek = recurrenceDayOfWeek;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Set<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}
