import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="CLASSES")
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
    private Date MeetingDays;

    @Column(name = "MeetingTimes")
    private Time MeetingTimes;

    @Column(name = "UserID")
    private int UserID;

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

    public Date getMeetingDays() {
        return MeetingDays;
    }

    public void setMeetingDays(Date meetingDays) {
        MeetingDays = meetingDays;
    }

    public Time getMeetingTimes() {
        return MeetingTimes;
    }

    public void setMeetingTimes(Time meetingTimes) {
        MeetingTimes = meetingTimes;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public ClassFor(){}

    public ClassFor(String name, String crn, String professor, Date meetingDays, Time meetingTimes, int userID) {
        this.Name = name;
        this.CRN = crn;
        this.Professor = professor;
        this.MeetingDays = meetingDays;
        this.MeetingTimes = meetingTimes;
        this.UserID = userID;
    }

}
