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
}
