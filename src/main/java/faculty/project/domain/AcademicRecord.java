package faculty.project.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float grade;
    private int year;

    @OneToOne
    private Subject subject;
    @OneToOne
    private Student student;
    @OneToOne
    private Teacher signedBy;

    public AcademicRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicRecord(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
}
