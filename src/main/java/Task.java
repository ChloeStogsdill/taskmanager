import javax.persistence.*;

@Entity
@Table(name="TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private int id;

    @Column(name = "Username", nullable = false)
    private String Username;

    @Column(name = "Title", nullable = false)
    private String Title;

    @Column(name = "Description")
    private String Description;

    @Column(name = "DueDate")
    private String DueDate;

    @Column(name = "ClassFor")
    private String ClassFor;

    @Column(name= "PriorityRating")
    private int PriorityRating;

    public Task(){}

    public Task(String username, String title, String description, int PriorityRating, String ClassFor) {
        this.Username = username;
        this.Title = title;
        this.Description = description;
        this.PriorityRating = PriorityRating;
        this.ClassFor = ClassFor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }


    public String getClassFor() {
        return ClassFor;
    }

    public void setClassFor(String classFor) {
        ClassFor = classFor;
    }

    public int getPriorityRating() {
        return PriorityRating;
    }

    public void setPriorityRating(int priorityRating) {
        PriorityRating = priorityRating;
    }

}
