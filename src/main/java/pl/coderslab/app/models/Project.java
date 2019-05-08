package pl.coderslab.app.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;
    private String name;

    private String describes;

    private String www;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int ident;

    private boolean activity;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "project")
    private List<Task> tasks;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public String getDescribes() {
        return describes;
    }

    public String getWww() {
        return www;
    }

    public User getUser() {
        return user;
    }

    public int getIdent() {
        return ident;
    }

    public boolean isActivity() {
        return activity;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
