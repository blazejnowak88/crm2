package pl.coderslab.app.models;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created = LocalDateTime.now();


    private String topic;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String describes;

    @OneToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

//     @OneToOne
//    private User user;


    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getTopic() {
        return topic;
    }

    public Project getProject() {
        return project;
    }

    public String getDescribes() {
        return describes;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}