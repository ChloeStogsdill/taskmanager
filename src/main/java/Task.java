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

    @Column(name = "Class")
    private String Class;

    @Column(name = "Recurring", nullable = false)
    private boolean Recurring;

    @Column(name = "RecurrencePeriod")
    private String RecurrencePeriod;

    public Task(){}

    public Task(String username, String title, String description, String dueDate, String className, String recurring, String recurrencePeriod) {
        this.Username = username;
        this.Title = title;
        this.Description = description;
        this.Recurring = Boolean.parseBoolean(recurring);
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

    @Override
    public String getClass() {
        return Class;
    }

    public void setClass(String aClass) {
        Class = aClass;
    }

    public boolean isRecurring() {
        return Recurring;
    }

    public void setRecurring(boolean recurring) {
        Recurring = recurring;
    }

    public String getRecurrencePeriod() {
        return RecurrencePeriod;
    }

    public void setRecurrencePeriod(String recurrencePeriod) {
        RecurrencePeriod = recurrencePeriod;
    }
}
