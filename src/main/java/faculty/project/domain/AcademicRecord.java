package faculty.project.domain;

import java.util.Calendar;


public class AcademicRecord {

    private Long id;

    private Float grade;
    private int year;

    private Subject subject;
    private Student student;
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
