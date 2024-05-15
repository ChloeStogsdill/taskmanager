import javax.persistence.*;

@Entity
@Table(name="CLASSFOR")
public class ClassFor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private int id;

    @Column(name = "Name", nullable = false)
    private String Name;

    @Column(name = "CRN", nullable = false)
    private String CRN;

    @Column(name = "Professor", nullable = false)
    private String Professor;

    @Column(name = "MeetingDays", nullable = false)
    private String MeetingDays;

    @Column(name = "MeetingTimes")
    private String MeetingTimes;

    @Column(name = "Username")
    private String Username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCRN() {
        return CRN;
    }

    public void setCRN(String CRN) {
        this.CRN = CRN;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    public String getMeetingDays() {
        return MeetingDays;
    }

    public void setMeetingDays(String meetingDays) {
        MeetingDays = meetingDays;
    }

    public String getMeetingTimes() {
        return MeetingTimes;
    }

    public void setMeetingTimes(String meetingTimes) {
        MeetingTimes = meetingTimes;
    }

    public String getUserID() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public ClassFor(){}

    public ClassFor(String name, String crn, String professor, String meetingDays, String meetingTimes, String username) {
        this.Name = name;
        this.CRN = crn;
        this.Professor = professor;
        this.MeetingDays = meetingDays;
        this.MeetingTimes = meetingTimes;
        this.Username = username;
    }

}
